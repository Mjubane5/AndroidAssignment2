package com.example.textbookmarketplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "UFHMarketplace.db";
    private static final String TABLE_NAME = "inventory";
    private static final String COL_ID = "id";
    private static final String COL_TITLE = "title";
    private static final String COL_SELLER = "seller";
    private static final String COL_PRICE = "price";
    private static final String COL_STOCK = "stock";
    private static final String COL_BANKING = "banking";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TITLE + " TEXT, " + COL_SELLER + " TEXT, " +
                COL_PRICE + " REAL, " + COL_STOCK + " INTEGER, " +
                COL_BANKING + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addTextbook(Textbook book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_TITLE, book.getTitle());
        values.put(COL_SELLER, book.getSellerName());
        values.put(COL_PRICE, book.getPrice());
        values.put(COL_STOCK, book.getStockCount());
        values.put(COL_BANKING, book.getBankingInfo());
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public List<Textbook> getAllTextbooks() {
        List<Textbook> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Textbook(
                        cursor.getInt(0),    // ID
                        cursor.getString(1), // THIS MUST BE TITLE
                        cursor.getString(2), // THIS MUST BE SELLER
                        cursor.getDouble(3), // Price
                        cursor.getInt(4),    // Stock
                        cursor.getString(5)  // Banking
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    public void deleteTextbook(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}