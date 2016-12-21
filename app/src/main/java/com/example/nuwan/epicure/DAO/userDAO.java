package com.example.nuwan.epicure.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import com.example.nuwan.epicure.Models.user;

/**
 * Created by nuwan on 12/21/2016.
 */

public class userDAO extends DAO {
    private SQLiteDatabase sqlDB;
    private String command;
    private Context context;

    public userDAO(Context context, SQLiteDatabase sqlDB) {
        super();
        this.tableName = "user_login_details";
        this.primaryKey = "email";
        this.context = context;
        this.sqlDB = sqlDB;
    }

    public void signout(String email){
        command = "UPDATE " + tableName + " SET logged_in =\"0\" WHERE email =\"" + email + "\";";
        sqlDB.execSQL(command);
    }

    public String checkAutoLogin(){
        command = "SELECT email FROM " + tableName + " WHERE logged_in=\"1\";";
        Cursor c = sqlDB.rawQuery(command,null);
        c.moveToFirst();
        if(c.getCount() == 1){
            return c.getString(0);
        }else{
            return "";
        }
    }

    public user getUser(String email){
        command = "SELECT * FROM "+tableName+" WHERE email = \"" + email + "\";";
        Cursor c = sqlDB.rawQuery(command,null);
        user user = null;
        if(c.moveToFirst()) {
            do {
                user = new user(
                        c.getString(c.getColumnIndex("email")),
                        c.getString(c.getColumnIndex("fname")),
                        c.getString(c.getColumnIndex("lname")),
                        c.getString(c.getColumnIndex("role")),
                        c.getString(c.getColumnIndex("reg_num")),
                        c.getString(c.getColumnIndex("token")),
                        c.getInt(c.getColumnIndex("logged_in")));
            } while (c.moveToNext());
        }
        return user;
    }


    public void addUser(user User) {
        //use prepared statements for insert
        command = "INSERT INTO " + tableName + " (email,fname,lname,role,reg_num,password,token,logged_in) VALUES (?,?,?,?,?,?,?,?)";
        SQLiteStatement statement = sqlDB.compileStatement(command);
        statement.bindString(1, User.getEmail());
        statement.bindString(2, User.getFName());
        statement.bindString(3, User.getLName());
        statement.bindString(4, User.getRole());
        statement.bindString(5, User.getRegNum());
        statement.bindString(6, User.getPassword());
        statement.bindString(7, User.getToken());
        statement.bindLong(8, User.getLoggedIn());
        statement.executeInsert();
    }
}
