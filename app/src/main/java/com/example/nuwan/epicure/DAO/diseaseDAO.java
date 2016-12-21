package com.example.nuwan.epicure.DAO;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.os.Build;
import android.util.Log;

import com.example.nuwan.epicure.Models.disease;
import com.example.nuwan.epicure.Models.disease_addition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nuwan on 12/21/2016.
 */

public class diseaseDAO extends DAO {
    private String disease_name = "disease_name";
    private String symptoms = "symptoms";
    private String causes = "causes";
    private String precautions = "precautions";
    private String first_aid = "first_aid";
    private String added_by = "added_by";
    private SQLiteDatabase sqlDB;
    private String command;

    public diseaseDAO(Context context, SQLiteDatabase sqlDB){
        super();
        this.tableName = "disease";
        this.primaryKey = "disease_name";
        this.sqlDB = sqlDB;
    }

    public List<disease> getDiseaseList() {
        command = "SELECT * FROM "+tableName+" WHERE 1;";
        Cursor c = sqlDB.rawQuery(command,null);
        List<disease> diseases = new ArrayList<>();
        if(c.moveToFirst()){
            do{
                disease d = new disease(
                        c.getString(c.getColumnIndex(disease_name)),
                        c.getString(c.getColumnIndex(symptoms)),
                        c.getString(c.getColumnIndex(causes)),
                        c.getString(c.getColumnIndex(precautions)),
                        c.getString(c.getColumnIndex(first_aid)),
                        c.getString(c.getColumnIndex(added_by)));
                diseases.add(d);
            }while(c.moveToNext());
        }
        return diseases;
    }

    public List<disease> filter(String field){
        command = "SELECT * FROM "+tableName+" WHERE " +
                disease_name+" LIKE \"%" + field + "%\" OR " +
                symptoms+" LIKE \"%" + field + "%\" OR " +
                causes+" LIKE \"%" + field + "%\" OR " +
                precautions+" LIKE \"%" + field + "%\" OR " +
                added_by+" LIKE \"%" + field + "%\" OR " +
                first_aid+" LIKE \"%" + field + "%\";";
        Cursor c = sqlDB.rawQuery(command, null);
        List<disease> filter = new ArrayList<>();
        if(c.moveToFirst()){
            do {
                disease d = new disease(
                        c.getString(c.getColumnIndex(disease_name)),
                        c.getString(c.getColumnIndex(symptoms)),
                        c.getString(c.getColumnIndex(causes)),
                        c.getString(c.getColumnIndex(precautions)),
                        c.getString(c.getColumnIndex(added_by)),
                        c.getString(c.getColumnIndex(first_aid)));
                filter.add(d);
            }while(c.moveToNext());
        }
        return filter;
    }

    public disease getDisease(String disease_name) {
        command = "SELECT * FROM " +tableName+" WHERE disease_name = \"" + disease_name + "\";";
        Cursor c = sqlDB.rawQuery(command, null);
        disease disease = null;
        if(c.moveToFirst()) {
            do{
                disease = new disease(
                        c.getString(c.getColumnIndex(this.disease_name)),
                        c.getString(c.getColumnIndex(this.symptoms)),
                        c.getString(c.getColumnIndex(this.causes)),
                        c.getString(c.getColumnIndex(this.precautions)),
                        c.getString(c.getColumnIndex(this.first_aid)),
                        c.getString(c.getColumnIndex(this.added_by)));
            }while (c.moveToNext());
        }
        return disease;
    }

    public void addDiseaseOnSync(disease disease) {
        //use prepared statements for insert
        command = "INSERT INTO diseases (disease_name,symptoms,causes,precautions,first_aid,added_by) VALUES (?,?,?,?,?,?)";
        SQLiteStatement statement = sqlDB.compileStatement(command);
        statement.bindString(1, disease.getDisease_name());
        statement.bindString(2, disease.getSymptoms());
        statement.bindString(3, disease.getCauses());
        statement.bindString(4, disease.getPrecautions());
        statement.bindString(5, disease.getFirst_aid());
        statement.bindString(5, disease.getAdded_by());
        statement.executeInsert();
    }
}
