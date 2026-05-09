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
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayout emptyStateLayout;
    DatabaseHelper databaseHelper;
    Button btnSearch;
    TextInputEditText etSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        recyclerView = findViewById(R.id.recyclerView);
        emptyStateLayout = findViewById(R.id.emptyStateLayout);
        btnSearch = findViewById(R.id.btnSearch);
        etSearch = findViewById(R.id.etSearch);
        databaseHelper = new DatabaseHelper(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadDataIntoView("");

        btnSearch.setOnClickListener(v -> {
            String searchQuery = etSearch.getText().toString().trim();
            loadDataIntoView(searchQuery);
        });
    }

    private void loadDataIntoView(String query) {
        List<Textbook> myData;

        if (query.isEmpty()) {
            myData = databaseHelper.getAllTextbooks();
        } else {
            myData = databaseHelper.searchBooks(query);
        }

        if (myData.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyStateLayout.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyStateLayout.setVisibility(View.GONE);

            // Connect the Adapter to our new Dialog Method
            ResourceAdapter adapter = new ResourceAdapter(myData, clickedItem -> showBookDetailsDialog(clickedItem));

            recyclerView.setAdapter(adapter);
        }
    }

    // NEW: The Detail View Dialog
    private void showBookDetailsDialog(Textbook book) {
        String details = "📖 Category: " + book.getCategory() + "\n\n" +
                "📦 Copies Available: " + book.getCopies() + "\n" +
                "👤 Seller: " + book.getSeller() + "\n" +
                "💳 Banking Info: " + book.getBankingInfo() + "\n\n" +
                "💰 Price: R " + book.getPrice();

        new MaterialAlertDialogBuilder(this)
                .setTitle(book.getTitle())
                .setMessage(details)
                .setPositiveButton("Message Seller", (dialog, which) -> {
                    // Trigger WhatsApp when they click this button
                    String message = "Hi " + book.getSeller() + ", I saw your listing for *"
                            + book.getTitle() + "* on ResourceHub. Is it still available for R"
                            + book.getPrice() + "?";
                    String phoneNumber = "27812345678";

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("https://api.whatsapp.com/send?phone=" + phoneNumber + "&text=" + Uri.encode(message)));
                    startActivity(intent);
                })
                .setNegativeButton("Close", (dialog, which) -> dialog.dismiss())
                .show();
    }
}