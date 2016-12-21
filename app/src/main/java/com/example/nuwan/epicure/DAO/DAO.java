package com.example.nuwan.epicure.DAO;

/**
 * Created by nuwan on 12/21/2016.
 */

public abstract class DAO {
    protected String tableName;
    protected String primaryKey;

    public DAO(){
        this.tableName = "";
        this.primaryKey = "";
    }
}