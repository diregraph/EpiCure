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
                        String strPassword = etPassword_login.getText().toString();
                        if (isValidEmail(charseqUsername) && !strPassword.isEmpty() ) {
                            Intent i = new Intent(getApplicationContext(), home_activity.class);
                            i.putExtra("key_email",charseqUsername ); //have to get from the json
                            //i.putExtra("key_Name",) have to get the name from the json
                            finish();
                            startActivity(i);
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
        startActivity(i);
    }

    private void init() {
        etUsername_login = (EditText) findViewById(R.id.input_username_login);
        etPassword_login = (EditText) findViewById(R.id.input_password_login);
        btnLogin = (Button) findViewById(R.id.btn_login);
        tvSignup = (TextView) findViewById(R.id.tv_signup);
    }
}
