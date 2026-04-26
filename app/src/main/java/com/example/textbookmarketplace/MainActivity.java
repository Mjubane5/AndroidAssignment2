package com.example.textbookmarketplace;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This links to Lugayeni's visual design
        setContentView(R.layout.activity_main);

        // Find the button we created in the XML
        Button btnAddBook = findViewById(R.id.btnAddBook);

        // Tell the button what to do when clicked
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // The "Intent" to move from MainActivity to AddBookActivity
                Intent intent = new Intent(MainActivity.this, AddBookActivity.class);
                startActivity(intent);
            }
        });
    }
}