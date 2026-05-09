package com.example.textbookmarketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddBookActivity extends AppCompatActivity {

    TextInputEditText etTitle, etPrice, etSeller, etCopies, etBankingInfo;
    Spinner spinnerCategory;
    Button btnSave;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // Link IDs
        etTitle = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
        etSeller = findViewById(R.id.etSeller);
        etCopies = findViewById(R.id.etCopies);
        etBankingInfo = findViewById(R.id.etBankingInfo);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnSave = findViewById(R.id.btnSave);

        databaseHelper = new DatabaseHelper(this);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString().trim();
                String priceStr = etPrice.getText().toString().trim();
                String seller = etSeller.getText().toString().trim();
                String copiesStr = etCopies.getText().toString().trim();
                String banking = etBankingInfo.getText().toString().trim();
                String category = spinnerCategory.getSelectedItem().toString();

                // Validation check
                if (title.isEmpty() || priceStr.isEmpty() || seller.isEmpty() || copiesStr.isEmpty() || banking.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check for duplicates!
                if (databaseHelper.checkDuplicate(title, seller)) {
                    Toast.makeText(AddBookActivity.this, "Error: You have already listed this textbook!", Toast.LENGTH_LONG).show();
                    return;
                }

                // Parse numbers and Save
                try {
                    double price = Double.parseDouble(priceStr);
                    int copies = Integer.parseInt(copiesStr);

                    Textbook newBook = new Textbook(title, seller, price, category, copies, banking);
                    boolean success = databaseHelper.addBook(newBook);

                    if (success) {
                        Toast.makeText(AddBookActivity.this, "Resource Successfully Added!", Toast.LENGTH_SHORT).show();
                        finish(); // Closes screen and goes back
                    } else {
                        Toast.makeText(AddBookActivity.this, "Database Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(AddBookActivity.this, "Please enter valid numbers for price and copies", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}