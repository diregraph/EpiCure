package com.example.nuwan.epicure.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuwan.epicure.DATABASE.database;
import com.example.nuwan.epicure.R;
import com.example.nuwan.epicure.constants;
import com.example.nuwan.epicure.server_request;

import org.json.JSONException;
import org.json.JSONObject;

public class signup_activity extends AppCompatActivity {
    private EditText etFName_signup;
    private EditText etLName_signup;
    private EditText etUsername_signup;
    private EditText etPassword_signup;
    private EditText etCPassword_signup;
    private Button btnCreate;
    private database sqlDB;
    private TextView tvHaveAnAcc;
    private Spinner spinnerUsertype;
    private EditText etRegNum;

    @Override
    public void onBackPressed() {
        backToLogin();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();
        btnCreate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strFName = etFName_signup.getText().toString();
                        String strLName = etLName_signup.getText().toString();
                        CharSequence charseqUsername = etUsername_signup.getText();
                        String strPassword = etPassword_signup.getText().toString();
                        String strCPassword = etCPassword_signup.getText().toString();
                        String strUsertype = spinnerUsertype.getSelectedItem().toString();
                        if (!strFName.isEmpty() && !strLName.isEmpty() && isValidEmail(charseqUsername) &&
                                strPassword.equals(strCPassword) && !strUsertype.isEmpty() ) {
                            Intent i = new Intent(getApplicationContext(), home_activity.class);
                            i.putExtra("key_email",charseqUsername.toString()); // have to get from the json\
                            i.putExtra("first_name","isuru");
                            i.putExtra("last_name","Dharmadasa");
                            sqlDB = new database(getApplicationContext(),"EpiCure",null, 1);
                            finish();
                            //have to finish the login_activity activity
                            startActivity(i);

                        } else if (strFName.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Enter First Name",Toast.LENGTH_SHORT).show();
                        } else if (strLName.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Enter Last Name",Toast.LENGTH_SHORT).show();
                        } else if (!isValidEmail(charseqUsername) ){
                            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                        } else if (strPassword.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                        } else if (strCPassword.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Confirm Password",Toast.LENGTH_SHORT).show();
                        } else if (!strPassword.equals(strCPassword)){
                            Toast.makeText(getApplicationContext(),"Password Mismatch",Toast.LENGTH_SHORT).show();
                        } else if (strUsertype.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Select User Type",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );

        tvHaveAnAcc.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        backToLogin();
                    }
                }
        );

        spinnerUsertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerUsertype.getSelectedItem().toString().equals("User")){
                    etRegNum.setEnabled(false);
                } else {
                    etRegNum.setEnabled(true);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                etRegNum.setEnabled(false);
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private void backToLogin() {
        Intent i = new Intent(getApplicationContext(),login_activity.class);
        i.putExtra("key_email",etUsername_signup.getText().toString());
        finish();
        startActivity(i);
    }

    private void init() {
        etFName_signup = (EditText) findViewById(R.id.input_fname);
        etLName_signup = (EditText) findViewById(R.id.input_lname);
        etUsername_signup = (EditText) findViewById(R.id.input_email_signup);
        etPassword_signup = (EditText) findViewById(R.id.input_password_profile);
        etCPassword_signup = (EditText) findViewById(R.id.input_confirm_password_profile);
        btnCreate = (Button) findViewById(R.id.btn_create);
        tvHaveAnAcc = (TextView) findViewById(R.id.tv_haveAnAcc);
        spinnerUsertype = (Spinner) findViewById(R.id.spinner_usertype);
        etRegNum = (EditText) findViewById(R.id.input_regnum_signup);
    }

    private void registerUser(final String firstname, final String lastname,
                              final String email, final String username, final String password, final String role){


        JSONObject json = new JSONObject();
        try {
            json.put("first_name", firstname);
            json.put("last_name", lastname);
            json.put("username", username);
            json.put("password", password);
            json.put("email", email);
            json.put("role", role);
            json.put("registration_no", role);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), "JSON error", Toast.LENGTH_SHORT);
            e.printStackTrace();
        }

        final String jsonString = json.toString();
        Log.d(constants.TAG, jsonString);

        final server_request request = new server_request(1,this);
        request.set_server_url(constants.server_signup__url);

        request.setParams(jsonString, "data");

        try {
            String req = request.sendPostRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CountDownTimer timer = new CountDownTimer(4000, 1000) {
            @Override
            public void onFinish() {
                processResponse(request.getResponse());
            }

            @Override
            public void onTick(long millisLeft) {
            }
        };
        timer.start();

    }


    private void processResponse(String response){
        if(response == ""){
            Toast.makeText(this, "Server Timeout", Toast.LENGTH_LONG).show();
        }else{
            try {
                JSONObject jsonRes = new JSONObject(response);
                boolean status = jsonRes.getBoolean("status");
                String message = jsonRes.getString("message");

                if (status){
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getApplicationContext(),
                            home_activity.class);
                    startActivity(i);
                    finish();

                }else{
                    Toast.makeText(this, message, Toast.LENGTH_LONG).show();
                }
            } catch (JSONException e) {
                Toast.makeText(this, response,Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        }



    }
}
