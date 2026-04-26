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

        // 1. Find the visual elements Kamva created in the XML
        EditText etBookTitle = findViewById(R.id.etBookTitle);
        EditText etBookPrice = findViewById(R.id.etBookPrice);
        Button btnSaveBook = findViewById(R.id.btnSaveBook);

        // 2. Tell the button what to do when clicked
        btnSaveBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Grab the text the user typed
                String title = etBookTitle.getText().toString();
                String priceString = etBookPrice.getText().toString();

                // Basic check to make sure they didn't leave it blank
                if (title.isEmpty() || priceString.isEmpty()) {
                    Toast.makeText(AddBookActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Convert the text price into a math decimal (double)
                double price = Double.parseDouble(priceString);

                // 3. Create the Textbook object using Mpilwenhle's Engine!
                Textbook newBook = new Textbook(title, price);

                // Show a little pop-up bubble (Toast) confirming it worked
                Toast.makeText(AddBookActivity.this, "Success! Posted: " + newBook.getTitle(), Toast.LENGTH_LONG).show();

                // Clear the boxes so they can add another book right away
                etBookTitle.setText("");
                etBookPrice.setText("");
            }
        });
    }
}