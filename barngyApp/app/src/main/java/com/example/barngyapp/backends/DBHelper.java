package com.example.barngyapp.backends;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "applications.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "applications";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DOCUMENT_NAME = "document_name";
    private static final String COLUMN_STATUS = "status";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_DOCUMENT_NAME + " TEXT,"
                + COLUMN_STATUS + " TEXT)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Method to insert application
    public void insertApplication(String documentName, String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DOCUMENT_NAME, documentName);
        values.put(COLUMN_STATUS, status);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Method to get all applications for the user (retrieve documents with status)
    public Cursor getApplications() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query(TABLE_NAME, new String[]{COLUMN_DOCUMENT_NAME, COLUMN_STATUS}, null, null, null, null, null);
    }
}
