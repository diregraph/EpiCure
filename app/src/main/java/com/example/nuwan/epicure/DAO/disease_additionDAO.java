package com.example.nuwan.epicure.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.example.nuwan.epicure.Models.disease_addition;

import java.util.ArrayList;
import java.util.List;

import com.example.nuwan.epicure.DAO.DAO;

/**
 * Created by nuwan on 12/21/2016.
 */

public class disease_additionDAO extends DAO {

    private String local_report_id = "local_report_id";
    private String disease_name_local = "disease_name_local";
    private String location_local = "location_local";
    private String email = "email";
    private String sync_status = "sync_status";
    private SQLiteDatabase sqlDB;
    private String command;

    public disease_additionDAO(Context context, SQLiteDatabase sqlDB){
        super();
        this.tableName = "disease_reports_local";
        this.primaryKey = "local_report_id";
        this.sqlDB = sqlDB;
    }

    public List<disease_addition> getDiseaseAdditionList() {
        command = "SELECT * FROM "+tableName+" WHERE 1;";
        Cursor c = sqlDB.rawQuery(command,null);
        List<disease_addition> diseases_addition = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                disease_addition d = new disease_addition(
                        c.getInt(c.getColumnIndex(local_report_id)),
                        c.getString(c.getColumnIndex(disease_name_local)),
                        c.getString(c.getColumnIndex(location_local)),
                        c.getString(c.getColumnIndex(email)),
                        c.getString(c.getColumnIndex(sync_status)));
                diseases_addition.add(d);
            }while(c.moveToNext());
        }
        return diseases_addition;
    }

    public void addDisease(disease_addition disease_addition) {
        //use prepared statements for insert
        command = "INSERT INTO disease_reports (disease_name,location_local,email,sync_status) VALUES (?,?,?,?)";
        SQLiteStatement statement = sqlDB.compileStatement(command);
        statement.bindString(1, disease_addition.getDiseaseNameLocal());
        statement.bindString(2, disease_addition.getLocationLocal());
        statement.bindString(3, disease_addition.getEmail());
        statement.bindString(4, disease_addition.getSyncStatus());
        statement.executeInsert();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void editDisease(String disease_name_local, String location_local, Integer local_report_id) {
        //use prepared statements for insert
        command = "UPDATE "+tableName+" SET "+
                "disease_name_local = ?, " +
                "location_local = ? WHERE" +
                " local_report_id = ?";
        SQLiteStatement statement = sqlDB.compileStatement(command);
        statement.bindString(1, disease_name_local);
        statement.bindString(2, location_local);
        statement.bindLong(3, local_report_id);
        statement.executeUpdateDelete();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    public void removeDiseaseOnSync(Integer local_report_id){
        command = "DELETE FROM "+tableName+" WHERE local_report_id = ?";
        SQLiteStatement statement = sqlDB.compileStatement(command);
        statement.bindLong(1,local_report_id);
        statement.executeUpdateDelete();
    }
}
