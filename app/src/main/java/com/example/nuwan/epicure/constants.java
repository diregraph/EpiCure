package com.example.nuwan.epicure;

/**
 * Created by nuwan on 12/21/2016.
 */

public class constants {
    public static final String database_name = "EpiCure";

    //java sprint tomcat server ---------------------------------------------------------------------------------------------
    //Host computer ip address and port
    public static final String server_host_ip = "10.0.2.2:8000";

    //server urls
    public static final String server_login_url = "http://" + server_host_ip + "/signIn";
    public static final String server_disease_search_url = "http://" + server_host_ip + "/diseases/search";
    public static final String server_signup__url = "http://" + server_host_ip + "/signup";
    public static final String server_patient_search_url = "http://" + server_host_ip + "/patients";

    //php localhost server --------------------------------------------------------------------------------------------------
    //public static final String server_url = "http://192.168.43.101/request/HealthCareServer.php";

    //emulator --------------------------------------------------------------------------------------------------------------
    //public static final String server_url = "http://10.0.2.2/request/HealthCareServer.php";

    // ----------------------------------------------------------------------------------------------------------------------

    //Log
    public static final String TAG = "TAG";

    //Internet connetion
    public static final int[] Internet_Array = {0,1};
    // 0 - Mobile Data
    // 1 - Wifi

    //Flag values for identify weather the data sync with the server or not
    public static final String OFFLINE_FLAG = "0";
    public static final String ONLINE_FLAG = "1";
}
