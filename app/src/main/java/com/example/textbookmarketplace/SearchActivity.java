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

                if (Inventory.textbookList.isEmpty()) {
                    tvSearchResults.setText("The marketplace is currently empty. Add some books first!");
                    return;
                }

                // Loop through the inventory
                for (Textbook book : Inventory.textbookList) {
                    // ⭐ RUBRIC REQUIREMENT: Search by Title OR Seller Name
                    if (query.isEmpty() ||
                            book.getTitle().toLowerCase().contains(query) ||
                            book.getSellerName().toLowerCase().contains(query)) {

                        // Display all the Assignment 2 details
                        results.append("📚 Title: ").append(book.getTitle()).append("\n")
                                .append("👤 Seller: ").append(book.getSellerName()).append("\n")
                                .append("💰 Price: R").append(book.getPrice()).append("\n")
                                .append("📦 Copies Available: ").append(book.getStockCount()).append("\n")
                                .append("🏦 Banking Info: ").append(book.getBankingInfo()).append("\n\n");
                    }
                }

                if (results.length() == 0) {
                    tvSearchResults.setText("No textbooks or sellers found matching: '" + query + "'");
                } else {
                    tvSearchResults.setText(results.toString());
                }
            }
        });
    }
}