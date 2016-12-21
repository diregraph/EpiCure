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
                "fname VARCHAR(20), " +
                "lname VARCHAR(30), " +
                "role VARCHAR(10), " +
                "role VARCHAR(10), " +
                "password VARCHAR(50), " +
                "token VARCHAR(100)" +
                "logged_in INTEGER);";
        //Log.i(constants.TAG,command);
        sqLiteDatabase.execSQL(command);

        command = "CREATE TABLE IF NOT EXISTS diseases(" +
                "disease_name VARCHAR(100) PRIMARY KEY, " +
                "symptoms VARCHAR(500), " +
                "causes VARCHAR(500), " +
                "precautions VARCHAR(500), " +
                "first_aid VARCHAR(500), " +
                "added_by VARCHAR(100));";
        //Log.i(constants.TAG,command);
        sqLiteDatabase.execSQL(command);

        command = "CREATE TABLE IF NOT EXISTS disease_reports_local(" +
                "local_report_id INTEGER PRIMARY KEY AUTOINCREMENT(" +
                "disease_name_local VARCHAR(100), " +
                "location_local VARCHAR(50), " +
                "email VARCHAR(50));";
               // "sync_status INTEGER);";
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
