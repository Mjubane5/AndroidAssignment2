package com.example.textbookmarketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText etSearchQuery = findViewById(R.id.etSearchQuery);
        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnShareWhatsApp = findViewById(R.id.btnShareWhatsApp);
        TextView tvSearchResults = findViewById(R.id.tvSearchResults);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearchQuery.getText().toString().toLowerCase().trim();
                StringBuilder results = new StringBuilder();

                DatabaseHelper db = new DatabaseHelper(SearchActivity.this);
                List<Textbook> currentInventory = db.getAllTextbooks();

                if (currentInventory.isEmpty()) {
                    tvSearchResults.setText("The forum database is currently empty. Post a listing first!");
                    btnShareWhatsApp.setVisibility(View.GONE);
                    return;
                }

                for (Textbook book : currentInventory) {
                    if (query.isEmpty() ||
                            book.getTitle().toLowerCase().contains(query) ||
                            book.getSellerName().toLowerCase().contains(query)) {

                        results.append("📚 Title: ").append(book.getTitle()).append("\n")
                                .append("👤 Seller: ").append(book.getSellerName()).append("\n")
                                .append("💰 Price: R").append(book.getPrice()).append("\n")
                                .append("📦 Copies: ").append(book.getStockCount()).append("\n")
                                .append("🏦 Bank: ").append(book.getBankingInfo()).append("\n\n");
                    }
                }

                if (results.length() == 0) {
                    tvSearchResults.setText("No listings found matching: '" + query + "'");
                    btnShareWhatsApp.setVisibility(View.GONE);
                } else {
                    tvSearchResults.setText(results.toString());
                    btnShareWhatsApp.setVisibility(View.VISIBLE);
                }
            }
        });

        btnShareWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultsText = tvSearchResults.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hey! Check out this listing:\n\n" + resultsText);
                sendIntent.setType("text/plain");
                Intent shareIntent = Intent.createChooser(sendIntent, "Contact Seller via...");
                startActivity(shareIntent);
            }
        });
    }
}