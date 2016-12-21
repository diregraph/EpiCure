package com.example.nuwan.epicure;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by nuwan on 12/21/2016.
 */

public class server_request extends AppCompatActivity {
    ArrayList<String> params;
    ArrayList<String> keys;
    String SERVER_URL = "";
    RequestQueue requestQueue;
    String response_msg = "";
    Activity activity;

    public server_request(Activity activity){
        this.activity = activity;
    }

    public server_request(int arg_count, Activity activity){
        params = new ArrayList<String>(arg_count);
        keys = new ArrayList<String>(arg_count);
        this.activity = activity;
    }

    public void set_server_url(String url){
        SERVER_URL = url;
    }

    public void setParams(String param_name, String key){
        params.add(param_name);
        keys.add(key);
    }

    public String sendPostRequest() throws JSONException {
        StringRequest request = new StringRequest(Request.Method.POST, SERVER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        setResponse_msg(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        setResponse_msg("Network is unreachable");
                        Log.i(constants.TAG,"ERROR OCCOURED DURING REQUEST :- "  + error.toString());
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> paramMap = new HashMap<>();
                for(int i=0; i<params.size(); i++){
                    paramMap.put(keys.get(i),params.get(i));
                    Log.i(constants.TAG,keys.get(i) + "   " + params.get(i));
                }
                return paramMap;
            }
        };
        request.setTag(constants.TAG);
        requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(request);
        return getResponse();
    }

    public void setResponse_msg(String msg){response_msg = msg;}
    public String getResponse(){return response_msg;}

    public void destroy(){
        requestQueue.cancelAll(constants.TAG);
    }

    public String sendGetRequest(HashMap<String,String> args, String server_url){
        String param = "?";
        for(String key:args.keySet()){
            if (param.equals("?")) {
                param += key + "=" + args.get(key);
            }else{
                param += "&" + key + "=" + args.get(key);
            }
        }
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(activity);
        String url = server_url + param;

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        setResponse_msg(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                setResponse_msg("Network is unreachable");
                Log.i(constants.TAG,error.toString());
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
        stringRequest.setTag(constants.TAG);
        return getResponse();
    }
}

