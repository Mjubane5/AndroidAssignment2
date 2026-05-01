package com.example.textbookmarketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        EditText etSellerName = findViewById(R.id.etSellerName);
        EditText etBookTitle = findViewById(R.id.etBookTitle);
        EditText etStockCount = findViewById(R.id.etStockCount);
        EditText etBookPrice = findViewById(R.id.etBookPrice);
        EditText etBankingInfo = findViewById(R.id.etBankingInfo);
        Button btnSaveBook = findViewById(R.id.btnSaveBook);

        btnSaveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sellerName = etSellerName.getText().toString().trim();
                String title = etBookTitle.getText().toString().trim();
                String stockString = etStockCount.getText().toString().trim();
                String priceString = etBookPrice.getText().toString().trim();
                String bankingInfo = etBankingInfo.getText().toString().trim();

                if (sellerName.isEmpty() || title.isEmpty() || stockString.isEmpty() || priceString.isEmpty() || bankingInfo.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Connect to SQLite Database
                DatabaseHelper db = new DatabaseHelper(AddBookActivity.this);
                List<Textbook> currentInventory = db.getAllTextbooks();

                // Duplicate Blocker
                for (Textbook existingBook : currentInventory) {
                    if (existingBook.getTitle().equalsIgnoreCase(title)) {
                        Toast.makeText(AddBookActivity.this, "Error: This textbook is already listed!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                try {
                    int stockCount = Integer.parseInt(stockString);
                    double price = Double.parseDouble(priceString);

                   
                    Textbook newBook = new Textbook(0, title, sellerName, price, stockCount, bankingInfo);

                    boolean isInserted = db.addTextbook(newBook);

                    if (isInserted) {
                        Toast.makeText(AddBookActivity.this, "Successfully Listed to Database: " + newBook.getTitle(), Toast.LENGTH_LONG).show();
                        etSellerName.setText("");
                        etBookTitle.setText("");
                        etStockCount.setText("");
                        etBookPrice.setText("");
                        etBankingInfo.setText("");
                    } else {
                        Toast.makeText(AddBookActivity.this, "Database Error: Could not save.", Toast.LENGTH_SHORT).show();
                    }

                } catch (NumberFormatException e) {
                    Toast.makeText(AddBookActivity.this, "Error: Please enter valid numbers.", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}