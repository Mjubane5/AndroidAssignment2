package com.example.textbookmarketplace;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // This links Ovayo's Java code to Kamva's visual design!
        setContentView(R.layout.activity_add_book);
    }
}