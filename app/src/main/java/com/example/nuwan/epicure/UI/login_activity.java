package com.example.nuwan.epicure.UI;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuwan.epicure.R;
import com.example.nuwan.epicure.constants;
import com.example.nuwan.epicure.server_request;

import org.json.JSONException;
import org.json.JSONObject;


public class login_activity extends AppCompatActivity {
    private EditText etUsername_login;
    private EditText etPassword_login;
    private Button btnLogin;
    private TextView tvSignup;
    private static final String tag = "tag_login" ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null ){
            etUsername_login.setText(bundle.getString("key_email"));
        }

        btnLogin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        CharSequence charseqUsername = etUsername_login.getText();
                        String stringUsername = charseqUsername.toString();
                        String strPassword = etPassword_login.getText().toString();
                        if (isValidEmail(charseqUsername) && !strPassword.isEmpty() ) {
                            loginRequest(stringUsername, strPassword);
                        } else if (!isValidEmail(charseqUsername) ){
                            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                        } else if (strPassword.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();
                        }
                   }
                }
        );

        tvSignup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        signupProcess();
                    }
                }
        );

    }

    private boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private void signupProcess() {
        Intent i = new Intent(getApplicationContext(),signup_activity.class);
        finish();
        startActivity(i);
    }

    private void init() {
        etUsername_login = (EditText) findViewById(R.id.input_username_login);
        etPassword_login = (EditText) findViewById(R.id.input_password_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvSignup = (TextView) findViewById(R.id.tv_signup);
    }

    private void loginRequest(String email, String password){
        final server_request loginRequest = new server_request(1,this);
        loginRequest.set_server_url(constants.server_login_url);
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("email",email );
            jsonObject.put("password", password);
        }catch (JSONException e){
            e.printStackTrace();
            Log.d("Login", "Login JSON parse failed");
        }

        String jsonStr = jsonObject.toString();
        loginRequest.setParams(jsonStr, "data");

        loginRequest.setResponse_msg(constants.TAG);

        try {
            loginRequest.sendPostRequest();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        CountDownTimer timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onFinish() {
                processResponse(loginRequest.getResponse());
            }

            @Override
            public void onTick(long millisLeft) {

            }
        };
        timer.start();

    }

    private void processResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);

            boolean status = jsonObject.getBoolean("status");
            if (status){
                String token = jsonObject.getString ("token");
                String email = jsonObject.getString("email");
                String password = jsonObject.getString("password");
                String first_name = jsonObject.getString("first_name");
                String last_name = jsonObject.getString("last_name");
                Intent i = new Intent(getApplicationContext(), home_activity.class);
                i.putExtra("key_email",email);
                i.putExtra("key_password",email);
                i.putExtra("first_name",email);
                i.putExtra("last_name",email);
                i.putExtra("token",email);
                finish();
                startActivity(i);
            }else {
                String message = jsonObject.getString("message");
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("login response", response );
        }
    }
}
