package com.example.textbookmarketplace;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;

public class AddBookActivity extends AppCompatActivity {

    // Declare the new UI variables
    TextInputEditText etTitle, etPrice, etSeller;
    Spinner spinnerCategory;
    Button btnSave;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // 1. Link to the NEW IDs from the updated XML file
        etTitle = findViewById(R.id.etTitle);
        etPrice = findViewById(R.id.etPrice);
        etSeller = findViewById(R.id.etSeller);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        btnSave = findViewById(R.id.btnSave);

        databaseHelper = new DatabaseHelper(this);

        // 2. Set up the save button
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Grab the text the user typed in
                String title = etTitle.getText().toString().trim();
                String price = etPrice.getText().toString().trim();
                String seller = etSeller.getText().toString().trim();
                String category = spinnerCategory.getSelectedItem().toString();

                // Make sure they didn't leave it blank
                if (title.isEmpty() || price.isEmpty() || seller.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // ==========================================
                // PUT YOUR DATABASE SAVE CODE HERE!
                // Example: databaseHelper.addBook(title, seller, price);
                // ==========================================

                Toast.makeText(AddBookActivity.this, "Resource Added!", Toast.LENGTH_SHORT).show();
                finish(); // Closes the screen and goes back to search
            }
        });
    }
}