package com.example.textbookmarketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText etSearchQuery = findViewById(R.id.etSearchQuery);
        Button btnSearch = findViewById(R.id.btnSearch);
        TextView tvSearchResults = findViewById(R.id.tvSearchResults);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearchQuery.getText().toString().toLowerCase().trim();
                StringBuilder results = new StringBuilder();

                // DIAGNOSTIC CHECK: Is the memory totally empty?
                if (Inventory.textbookList.isEmpty()) {
                    tvSearchResults.setText("ERROR: The inventory memory is completely empty! The books are not saving.");
                    return;
                }

                // Loop through the global inventory list
                for (Textbook book : Inventory.textbookList) {
                    // If the box is blank OR if it matches the search, show it
                    if (query.isEmpty() || book.getTitle().toLowerCase().contains(query)) {
                        results.append("Found: ").append(book.getTitle())
                                .append(" - R").append(book.getPrice()).append("\n\n");
                    }
                }

                if (results.length() == 0) {
                    tvSearchResults.setText("Books are in memory, but no match found for: '" + query + "'");
                } else {
                    tvSearchResults.setText(results.toString());
                }
            }
        });
    }
}