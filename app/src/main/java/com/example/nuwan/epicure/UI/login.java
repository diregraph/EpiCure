package com.example.nuwan.epicure.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.nuwan.epicure.R;


public class login extends AppCompatActivity {
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
                        String strUsername = etUsername_login.getText().toString();
                        String strPassword = etPassword_login.getText().toString();
                        if (!strUsername.isEmpty() && !strPassword.isEmpty() ) {
                            Intent i = new Intent(getApplicationContext(), home.class);
                            i.putExtra("key_email",strUsername ); //have to get from the json
                            //i.putExtra("key_Name",) have to get the name from the json
                            finish();
                            startActivity(i);
                        } else if (strUsername.isEmpty() ){
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

    private void signupProcess() {
        Intent i = new Intent(getApplicationContext(),signup.class);
        startActivity(i);
    }

    private void init() {
        etUsername_login = (EditText) findViewById(R.id.input_username_login);
        etPassword_login = (EditText) findViewById(R.id.input_password_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvSignup = (TextView) findViewById(R.id.tv_signup);
    }
}
