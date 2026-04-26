package com.example.textbookmarketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // 1. Find ALL the input boxes Kamva created
        EditText etSellerName = findViewById(R.id.etSellerName);
        EditText etBookTitle = findViewById(R.id.etBookTitle);
        EditText etStockCount = findViewById(R.id.etStockCount);
        EditText etBookPrice = findViewById(R.id.etBookPrice);
        EditText etBankingInfo = findViewById(R.id.etBankingInfo);
        Button btnSaveBook = findViewById(R.id.btnSaveBook);

        btnSaveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Grab what the user typed and remove accidental spaces
                String sellerName = etSellerName.getText().toString().trim();
                String title = etBookTitle.getText().toString().trim();
                String stockString = etStockCount.getText().toString().trim();
                String priceString = etBookPrice.getText().toString().trim();
                String bankingInfo = etBankingInfo.getText().toString().trim();

                // Validation: Make sure nothing is blank
                if (sellerName.isEmpty() || title.isEmpty() || stockString.isEmpty() || priceString.isEmpty() || bankingInfo.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // ⭐ RUBRIC REQUIREMENT: The Duplicate Blocker
                for (Textbook existingBook : Inventory.textbookList) {
                    if (existingBook.getTitle().equalsIgnoreCase(title)) {
                        Toast.makeText(AddBookActivity.this, "Error: This textbook is already listed!", Toast.LENGTH_LONG).show();
                        return; // Stop the code right here so it doesn't save
                    }
                }

                // ⭐ RUBRIC REQUIREMENT: Exception Handling
                try {
                    // Try to convert the typed text into math numbers
                    int stockCount = Integer.parseInt(stockString);
                    double price = Double.parseDouble(priceString);

                    // Create the upgraded Textbook object
                    Textbook newBook = new Textbook(sellerName, title, stockCount, price, bankingInfo);

                    // Save to memory
                    Inventory.textbookList.add(newBook);

                    Toast.makeText(AddBookActivity.this, "Successfully Listed: " + newBook.getTitle(), Toast.LENGTH_LONG).show();

                    // Clear the boxes for the next entry
                    etSellerName.setText("");
                    etBookTitle.setText("");
                    etStockCount.setText("");
                    etBookPrice.setText("");
                    etBankingInfo.setText("");

                } catch (NumberFormatException e) {
                    // If they typed letters into the price or copies box, catch the crash!
                    Toast.makeText(AddBookActivity.this, "Error: Please enter valid numbers for price and copies.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}