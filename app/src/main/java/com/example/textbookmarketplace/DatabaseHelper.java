package com.example.textbookmarketplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "resourcehub.db";
    // We bump the version to 2 because we added new columns!
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_BOOKS = "textbooks";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_SELLER = "seller";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_CATEGORY = "category";
    private static final String COLUMN_COPIES = "copies";
    private static final String COLUMN_BANKING = "banking_info";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_BOOKS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT, " +
                COLUMN_SELLER + " TEXT, " +
                COLUMN_PRICE + " REAL, " +
                COLUMN_CATEGORY + " TEXT, " +
                COLUMN_COPIES + " INTEGER, " +
                COLUMN_BANKING + " TEXT)";
        db.execSQL(createTable);

        // Pre-populate the database for the presentation
        seedDatabase(db);
    }

    // This method injects dummy data right at the start
    private void seedDatabase(SQLiteDatabase db) {
        String insertSQL = "INSERT INTO " + TABLE_BOOKS + " (" +
                COLUMN_TITLE + ", " + COLUMN_SELLER + ", " + COLUMN_PRICE + ", " +
                COLUMN_CATEGORY + ", " + COLUMN_COPIES + ", " + COLUMN_BANKING + ") VALUES ";

        db.execSQL(insertSQL + "('Advanced Java', 'Mpilwenhle', 200.0, '💻 Tech & Electronics', 2, 'Capitec, 123456789')");
        db.execSQL(insertSQL + "('CompTIA Security+ SY0-701', 'Kamva', 350.0, '💻 Tech & Electronics', 1, 'FNB, 987654321')");
        db.execSQL(insertSQL + "('Calculus Early Transcendentals', 'Lugayeni', 400.0, '📐 Applied Math & Science', 3, 'Standard Bank, 112233445')");
        db.execSQL(insertSQL + "('Database Systems', 'Sisanda', 250.0, '📚 Textbooks & Literature', 4, 'Absa, 556677889')");
        db.execSQL(insertSQL + "('Operating Systems', 'Abahle', 880.0, '💻 Tech & Electronics', 5, 'Absa, 556677889')");
        db.execSQL(insertSQL + "('High-Performance Cluster Systems', 'Ovayo', 250.0, '💻 Tech & Electronics', 5, 'Absa, 556677889')");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOOKS);
        onCreate(db);
    }

    // REQUIREMENT 3: Check for Duplicates
    public boolean checkDuplicate(String title, String seller) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS + " WHERE " +
                        COLUMN_TITLE + "=? AND " + COLUMN_SELLER + "=?",
                new String[]{title, seller});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }

    // Add a new book
    public boolean addBook(Textbook book) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_TITLE, book.getTitle());
        cv.put(COLUMN_SELLER, book.getSeller());
        cv.put(COLUMN_PRICE, book.getPrice());
        cv.put(COLUMN_CATEGORY, book.getCategory());
        cv.put(COLUMN_COPIES, book.getCopies());
        cv.put(COLUMN_BANKING, book.getBankingInfo());

        long result = db.insert(TABLE_BOOKS, null, cv);
        return result != -1;
    }

    // Load all books for the main list
    public List<Textbook> getAllTextbooks() {
        List<Textbook> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS, null);

        if (cursor.moveToFirst()) {
            do {
                list.add(new Textbook(
                        cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getDouble(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    // REQUIREMENT 4: Search by Title OR Seller Name
    public List<Textbook> searchBooks(String query) {
        List<Textbook> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // The % allows for partial matches!
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_BOOKS + " WHERE " +
                        COLUMN_TITLE + " LIKE ? OR " + COLUMN_SELLER + " LIKE ?",
                new String[]{"%" + query + "%", "%" + query + "%"});

        if (cursor.moveToFirst()) {
            do {
                list.add(new Textbook(
                        cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                        cursor.getDouble(3), cursor.getString(4), cursor.getInt(5), cursor.getString(6)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}