package com.example.barangayappv1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class dblogin extends SQLiteOpenHelper {

    public static final String databaseName = "login.db";

    // Constructor
    public dblogin(@Nullable Context context) {
        super(context, databaseName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase logindatabase) {
        // Create table with proper column names
        logindatabase.execSQL("CREATE TABLE allusers (" +
                "name TEXT, " +
                "username TEXT PRIMARY KEY, " +
                "password TEXT, " +
                "number TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase logindatabase, int oldVersion, int newVersion) {
        logindatabase.execSQL("DROP TABLE IF EXISTS allusers");
        onCreate(logindatabase);
    }

    public Boolean insertData(String fullname, String number, String username, String password) {
        SQLiteDatabase mydatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("name", fullname);
        contentValues.put("number", number);

        long result = mydatabase.insert("allusers", null, contentValues);
        Log.d("DBInsert", "Insertion result: " + result); // Log the result of the insertion
        return result != -1; // Return true if insert is successful
    }



    public Boolean checkFullnameNumberUsernamePassword(String fullname, String number, String username, String password) {
        SQLiteDatabase mydatabase = this.getReadableDatabase();
        Cursor cursor = mydatabase.rawQuery("SELECT * FROM allusers WHERE name = ? AND number = ? AND username = ? AND password = ?",
                new String[]{fullname, number, username, password});

        boolean result = cursor.getCount() > 0;
        cursor.close();  // Close cursor to avoid memory leaks
        return result;
    }


    // Check if username and password exist together (used for login)
    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase mydatabase = this.getReadableDatabase();
        Cursor cursor = mydatabase.rawQuery("SELECT * FROM allusers WHERE username = ? AND password = ?",
                new String[]{username, password});

        boolean result = cursor.getCount() > 0;
        cursor.close();  // Close cursor to avoid memory leaks
        return result;
    }

    // Retrieve user data by username
    public Cursor getUserData(String fullname, String number) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM allusers WHERE fullname = ? and number = ?";
        return db.rawQuery(query, new String[]{fullname, number});
    }

}

