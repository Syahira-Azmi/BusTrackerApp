package com.example.bustrackerapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bustrackerapp.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the bus table
        String CREATE_TABLE_BUS = "CREATE TABLE bus (" +
                "busID INTEGER PRIMARY KEY, " +
                "capacity INTEGER, " +
                "status TEXT, " +
                "userID INTEGER, " +
                "scheduleID INTEGER)";
        db.execSQL(CREATE_TABLE_BUS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if it exists
        db.execSQL("DROP TABLE IF EXISTS bus");
        // Create tables again
        onCreate(db);
    }

    // Method to get bus details by busID
    public Cursor getBusDetailsByID(int busID) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM bus WHERE busID = ?";
        return db.rawQuery(query, new String[]{String.valueOf(busID)});
    }
}
