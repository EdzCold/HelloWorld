package com.volley.profuturo.en501863.storage.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLClientInfoException;

/**
 * Created by EN501863 on 11/10/2017.
 */

public abstract class CreationOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION  = 1;
    private static final String DATABASE_NAME = "";

    private static final String TABLE_NAME = "";


    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "KEY_WORD" + " TEXT, " +
                    "KEY_DEFINITION" + " TEXT);";


    public CreationOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }
}
