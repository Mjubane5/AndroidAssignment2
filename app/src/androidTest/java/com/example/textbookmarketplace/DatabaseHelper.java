package com.example.textbookmarketplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Configuration
    private static final String DATABASE_NAME = "UFHMarketplace.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "inventory";

    // Column Names
    private static final String COL_ID = "id";
    private static final String COL_SELLER = "seller";
    private static final String COL_TITLE = "title";
    private static final String COL_STOCK = "stock";
    private static final String COL_PRICE = "price";
    private static final String COL_BANKING = "banking";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // This SQL command creates the physical table on the phone's hard drive
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SELLER + " TEXT, " +
                COL_TITLE + " TEXT, " +
                COL_STOCK + " INTEGER, " +
                COL_PRICE + " REAL, " +
                COL_BANKING + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // ⭐ METHOD 1: Add a book to the permanent database
    public boolean addTextbook(Textbook book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_SELLER, book.getSellerName());
        values.put(COL_TITLE, book.getTitle());
        values.put(COL_STOCK, book.getStockCount());
        values.put(COL_PRICE, book.getPrice());
        values.put(COL_BANKING, book.getBankingInfo());

        long result = db.insert(TABLE_NAME, null, values);
        return result != -1; // Returns true if saved successfully
    }

    // ⭐ METHOD 2: Retrieve all books from the database
    public List<Textbook> getAllTextbooks() {
        List<Textbook> bookList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String seller = cursor.getString(1);
                String title = cursor.getString(2);
                int stock = cursor.getInt(3);
                double price = cursor.getDouble(4);
                String banking = cursor.getString(5);

                Textbook book = new Textbook(seller, title, stock, price, banking);
                bookList.add(book);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return bookList;
    }
}