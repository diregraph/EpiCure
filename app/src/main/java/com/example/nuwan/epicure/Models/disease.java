package com.example.nuwan.epicure.Models;

import android.widget.Spinner;

/**
 * Created by nuwan on 12/21/2016.
 */

public class disease {
    private String disease_name;
    private String symptoms;
    private String causes;
    private String precautions;
    private String first_aid;
    private String added_by;

    public disease(String disease_name, String symptoms, String causes, String precautions, String first_aid, String added_by){
        this.disease_name = disease_name;
        this.symptoms = symptoms;
        this.causes = causes;
        this.precautions = precautions;
        this.first_aid = first_aid;
        this.added_by = added_by;
    }

    public void setDisease_name(String disease_name){
        this.disease_name = disease_name;
    }

    public void setSymptoms(String symptoms){
        this.symptoms = symptoms;
    }

    public void setCauses(String causes){ this.causes = causes; }

    public void setPrecautions(String precautions){ this.precautions = precautions; }

    public void setFirst_aid(String first_aid){ this.first_aid = first_aid; }

    public void setAdded_by(String added_by){ this.added_by = added_by; }



    public String getDisease_name(){
        return this.disease_name;
    }

    public String getSymptoms(){
        return this.symptoms;
    }

    public String getCauses(){
        return this.causes;
    }

    public String getPrecautions(){ return this.precautions;}

    public String getFirst_aid( ){ return this.first_aid; }

    public String getAdded_by( ){ return this.added_by; }
}
