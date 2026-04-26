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

        EditText etBookTitle = findViewById(R.id.etBookTitle);
        EditText etBookPrice = findViewById(R.id.etBookPrice);
        Button btnSaveBook = findViewById(R.id.btnSaveBook);

        btnSaveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etBookTitle.getText().toString();
                String priceString = etBookPrice.getText().toString();

                if (title.isEmpty() || priceString.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double price = Double.parseDouble(priceString);
                Textbook newBook = new Textbook(title, price);

                // ⭐ THE CRUCIAL MISSING LINE: This physically shoves the book into memory!
                Inventory.textbookList.add(newBook);

                // This new Toast proves the newer code is running
                Toast.makeText(AddBookActivity.this, "Saved to Inventory: " + newBook.getTitle(), Toast.LENGTH_LONG).show();

                etBookTitle.setText("");
                etBookPrice.setText("");
            }
        });
    }
}