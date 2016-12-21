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
    private String DB_NAME;
    private String command;
    public database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
        this.DB_NAME = name;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        command = "CREATE TABLE IF NOT EXISTS user_login_details(" +
                "email VARCHAR(50) PRIMARY KEY, " +
                "name VARCHAR(60), " +
                "role VARCHAR(10), " +
                "status INTEGER);";
        //Log.i(constants.TAG,command);
        sqLiteDatabase.execSQL(command);

        command = "CREATE TABLE IF NOT EXISTS diseases(" +
                "disease_name VARCHAR(100) PRIMARY KEY, " +
                "";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        command = "DROP TABLE IF EXISTS login_detail;";
        //Log.i(constants.TAG,command);
        sqLiteDatabase.execSQL(command);
        onCreate(sqLiteDatabase);
    }

    public SQLiteDatabase getDatabase(){
        return getWritableDatabase();
    }
}
