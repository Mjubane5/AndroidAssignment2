package com.example.textbookmarketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private int currentFoundBookId = -1; // Tracks the ID for deletion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        EditText etSearchQuery = findViewById(R.id.etSearchQuery);
        Button btnSearch = findViewById(R.id.btnSearch);
        Button btnShareWhatsApp = findViewById(R.id.btnShareWhatsApp);
        Button btnDeleteListing = findViewById(R.id.btnDeleteListing);
        TextView tvSearchResults = findViewById(R.id.tvSearchResults);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = etSearchQuery.getText().toString().toLowerCase().trim();
                StringBuilder results = new StringBuilder();

                DatabaseHelper db = new DatabaseHelper(SearchActivity.this);
                List<Textbook> currentInventory = db.getAllTextbooks();

                currentFoundBookId = -1; // Reset tracker

                if (currentInventory.isEmpty()) {
                    tvSearchResults.setText("The database is empty. Post a listing first!");
                    btnShareWhatsApp.setVisibility(View.GONE);
                    btnDeleteListing.setVisibility(View.GONE);
                    return;
                }

                for (Textbook book : currentInventory) {
                    if (query.isEmpty() || book.getTitle().toLowerCase().contains(query)) {
                        results.append("📚 Title: ").append(book.getTitle()).append("\n")
                                .append("👤 Seller: ").append(book.getSellerName()).append("\n")
                                .append("💰 Price: R").append(book.getPrice()).append("\n\n");

                        currentFoundBookId = book.getId(); // Capture the ID
                    }
                }

                if (results.length() == 0) {
                    tvSearchResults.setText("No matches found.");
                    btnShareWhatsApp.setVisibility(View.GONE);
                    btnDeleteListing.setVisibility(View.GONE);
                } else {
                    tvSearchResults.setText(results.toString());
                    btnShareWhatsApp.setVisibility(View.VISIBLE);
                    btnDeleteListing.setVisibility(View.VISIBLE); // Show delete option
                }
            }
        });

        // DELETE Logic
        btnDeleteListing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentFoundBookId != -1) {
                    DatabaseHelper db = new DatabaseHelper(SearchActivity.this);
                    db.deleteTextbook(currentFoundBookId);
                    Toast.makeText(SearchActivity.this, "Listing Deleted!", Toast.LENGTH_SHORT).show();

                    // Clear screen
                    tvSearchResults.setText("");
                    btnDeleteListing.setVisibility(View.GONE);
                    btnShareWhatsApp.setVisibility(View.GONE);
                }
            }
        });

        // WhatsApp Logic
        btnShareWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultsText = tvSearchResults.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Check out this book: " + resultsText);
                sendIntent.setType("text/plain");
                startActivity(Intent.createChooser(sendIntent, "Share via..."));
            }
        });
    }
}