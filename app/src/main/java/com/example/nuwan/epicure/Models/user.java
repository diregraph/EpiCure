package com.example.nuwan.epicure.Models;

/**
 * Created by nuwan on 12/21/2016.
 */

public class user {
    private String email;
    private String fname;
    private String lname;
    private String role;
    private String token;
    private Integer logged_in;

    public void user(String email, String fname, String lname, String role, String token, Integer logged_in ){
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
        this.token = token;
        this.logged_in = logged_in;
    }

    public void setFName(String fname){
        this.fname = fname;
    }

    public void setLName(String lname){
        this.lname = lname;
    }
}
