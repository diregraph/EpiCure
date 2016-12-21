package com.example.nuwan.epicure.Models;

/**
 * Created by nuwan on 12/21/2016.
 */

public class disease_addition {
    private Integer local_report_id;
    private String disease_name_local;
    private String location_local;
    private String email;
    private String sync_status;

    public disease_addition(String disease_name_local, String location_local,
                            String email){
        this.disease_name_local = disease_name_local;
        this.location_local = location_local;
        this.email = email;
        //this.sync_status = sync_status;
    }

    public void setDiseaseNameLocal(String disease_name_local){
        this.disease_name_local = disease_name_local;
    }

    public void setLocationLocal(String location_local){
        this.location_local = location_local;
    }

    public void setEmail(String email){
        this.email = email;
    }

//    //public void setSyncStatus(String sync_status){
//        this.sync_status = sync_status;
//    }



    public Integer getLocalReportId(){
        return this.local_report_id;
    }

    public String getDiseaseNameLocal(){
        return this.disease_name_local;
    }

    public String getLocationLocal(){
        return this.location_local;
    }

    public String getEmail(){
        return this.email;
    }

//    public String getSyncStatus(){
//        return this.sync_status;
//    }

}
