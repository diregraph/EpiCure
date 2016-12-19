package com.example.nuwan.epicure.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nuwan.epicure.DATABASE.database;
import com.example.nuwan.epicure.R;

public class signup extends AppCompatActivity {
    private TextView tvHaveAnAcc;
    private EditText etUsername_signup;
    private Button btnCreate;
    private database sqliteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        init();

        btnCreate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sqliteDB = new database(getApplicationContext(),"EpiCure1",null, 1);
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
    }

    private void backToLogin() {
        Intent i = new Intent(getApplicationContext(),login.class);
        i.putExtra("key_email",etUsername_signup.getText());
        finish();
        startActivity(i);
    }

    private void init() {
        tvHaveAnAcc = (TextView) findViewById(R.id.tv_haveAnAcc);
        etUsername_signup = (EditText) findViewById(R.id.input_username_signup);
        btnCreate = (Button) findViewById(R.id.btn_create);
    }
}
