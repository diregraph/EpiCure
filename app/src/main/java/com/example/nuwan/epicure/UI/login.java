package com.example.nuwan.epicure.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nuwan.epicure.R;

public class login extends AppCompatActivity {
    private EditText etUsername_login;
    private EditText etPassword_login;
    private Button btnLogin;
    private TextView tvSignup;
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
                        Intent i = new Intent(getApplicationContext(),home.class);
                        i.putExtra("key_email",etUsername_login.getText().toString());
                        finish();
                        startActivity(i);
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
