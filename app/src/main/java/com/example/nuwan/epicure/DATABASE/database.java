package com.example.nuwan.epicure.DATABASE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by nuwan on 12/18/2016.
 */

public class database extends SQLiteOpenHelper{
    private Context context;
    private String command;
    public database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        onCreate(getWritableDatabase());
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        command = "CREATE TABLE IF NOT EXISTS user_login_details (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "email VARCHAR(50));";
        db.execSQL(command);
        Log.i("TAG",command);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        command = "DROP TABLE IF EXISTS user_login_details;";
        Log.i("TAG",command);
        db.execSQL(command);
        onCreate(db);
    }
}
