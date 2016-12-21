package com.example.nuwan.epicure.Models;

/**
 * Created by nuwan on 12/21/2016.
 */

public class user {
    private String email;
    private String fname;
    private String lname;
    private String role;
    private String reg_num;
    private String token;
    private String password;
    private Integer logged_in;



    public user(String email, String fname, String lname, String role, String reg_num, String token, int logged_in ){
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
        this.reg_num = reg_num;
        this.token = token;
        this.logged_in = logged_in;
    }

    public void setFName(String fname){
        this.fname = fname;
    }

    public void setLName(String lname){
        this.lname = lname;
    }

    public void setRole(String role){
        this.role = role;
    }

    public void setRegNum(String reg_num){ this.reg_num = reg_num; }

    public void setToken(String token){
        this.token = token;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setLoggedIn(int logged_in){
        this.logged_in = logged_in;
    }

    public String getEmail(){return email;}
    public String getFName(){
        return fname;
    }
    public String getLName(){
        return lname;
    }
    public String getRole(){
        return role;
    }
    public String getRegNum(){
        return reg_num;
    }
    public String getToken(){
        return token;
    }
    public String getPassword(){
        return password;
    }
    public int getLoggedIn(){return logged_in;}
}
