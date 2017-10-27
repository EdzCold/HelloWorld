package com.volley.profuturo.en501863.imageprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE = "MyDatabase";
    private static final int DATABASE_VERSION = 1;

    static final String TABLE_VICS = "vics";
    static final String COLUMN_ID = "_ID";
    static final String COLUMN_NAME = "name";
    static final String COLUMN_Image_BASE64 = "imagebase64";


    private SQLiteDatabase database;

    DatabaseHelper(Context context) {
        super(context, DATABASE, null, DATABASE_VERSION);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL = " CREATE TABLE " + TABLE_VICS + " ( " +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_Image_BASE64 + " TEXT, " +
                COLUMN_NAME + " TEXT NOT NULL);";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { }
}

