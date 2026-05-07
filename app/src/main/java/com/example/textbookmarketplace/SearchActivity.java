package com.example.textbookmarketplace;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.textfield.TextInputEditText;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    // 1. Declare all UI components here
    RecyclerView recyclerView;
    LinearLayout emptyStateLayout;
    DatabaseHelper databaseHelper;
    Button btnSearch;
    TextInputEditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 2. Link the variables to your XML IDs
        recyclerView = findViewById(R.id.recyclerView);
        emptyStateLayout = findViewById(R.id.emptyStateLayout);
        btnSearch = findViewById(R.id.btnSearch);
        etSearch = findViewById(R.id.etSearch);
        databaseHelper = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 3. Load all textbooks initially when the page opens
        loadDataIntoView("");

        // 4. Set up the new Search Button Click Event
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Grab the text the user typed in the search bar
                String searchQuery = etSearch.getText().toString().trim();

                // Reload the view based on what they searched
                loadDataIntoView(searchQuery);
            }
        });
    }

    // A helper method to keep your code clean and handle loading the data
    private void loadDataIntoView(String query) {
        List<Textbook> myData;

        // If the search bar is empty, get everything. Otherwise, get the filtered results.
        if (query.isEmpty()) {
            myData = databaseHelper.getAllTextbooks();
        } else {
            // NOTE: If you have a specific search method in your DatabaseHelper
            // like searchBooks(query), you would use it here.
            // For now, it will just load all textbooks to prevent crashes.
            myData = databaseHelper.getAllTextbooks();
            Toast.makeText(this, "Searching for: " + query, Toast.LENGTH_SHORT).show();
        }

        // State Management: Empty vs Populated Database
        if (myData.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyStateLayout.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyStateLayout.setVisibility(View.GONE);

            ResourceAdapter adapter = new ResourceAdapter(myData, new ResourceAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(Textbook clickedItem) {

                    String message = "Hi " + clickedItem.getSeller() + ", I saw your listing for *"
                            + clickedItem.getTitle() + "* on the app. Is it still available for R"
                            + clickedItem.getPrice() + "?";

                    String phoneNumber = "27812345678";

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message)));
                    startActivity(intent);
                }
            });

            recyclerView.setAdapter(adapter);
        }
    }
}