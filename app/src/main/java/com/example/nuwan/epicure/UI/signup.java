package com.example.nuwan.epicure.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuwan.epicure.DATABASE.database;
import com.example.nuwan.epicure.R;

public class signup extends AppCompatActivity {
    private EditText etFName_signup;
    private EditText etLName_signup;
    private EditText etUsername_signup;
    private EditText etPassword_signup;
    private EditText etCPassword_signup;
    private Button btnCreate;
    private database sqliteDB;
    private TextView tvHaveAnAcc;
    private Spinner spinnerUsertype;

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
                        String strUsername = etUsername_signup.getText().toString();
                        String strPassword = etPassword_signup.getText().toString();
                        String strCPassword = etCPassword_signup.getText().toString();
                        String strUsertype = spinnerUsertype.getSelectedItem().toString();
                        if (!strFName.isEmpty() && !strLName.isEmpty() &&!strUsername.isEmpty() &&
                                !strPassword.isEmpty() && !strCPassword.isEmpty() && !strUsertype.isEmpty() ) {
                            Intent i = new Intent(getApplicationContext(), home.class);
                            i.putExtra("key_email",strUsername); // have to get from the json
                            sqliteDB = new database(getApplicationContext(),"EpiCure",null, 1);
                            finish();
                            //have to finish the login activity
                            startActivity(i);

                        } else if (strFName.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Enter First Name",Toast.LENGTH_SHORT).show();
                        } else if (strLName.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Enter Last Name",Toast.LENGTH_SHORT).show();
                        } else if (strUsername.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Invalid Email",Toast.LENGTH_SHORT).show();
                        } else if (strPassword.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                        } else if (strCPassword.isEmpty() ){
                            Toast.makeText(getApplicationContext(),"Password mismatch",Toast.LENGTH_SHORT).show();
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
    }

    private void backToLogin() {
        Intent i = new Intent(getApplicationContext(),login.class);
        i.putExtra("key_email",etUsername_signup.getText());
        finish();
        startActivity(i);
    }

    private void init() {
        etFName_signup = (EditText) findViewById(R.id.input_fname);
        etLName_signup = (EditText) findViewById(R.id.input_lname);
        etUsername_signup = (EditText) findViewById(R.id.input_email_signup);
        etPassword_signup = (EditText) findViewById(R.id.input_password_signup);
        etCPassword_signup = (EditText) findViewById(R.id.input_confirm_password_signup);
        btnCreate = (Button) findViewById(R.id.btn_create);
        tvHaveAnAcc = (TextView) findViewById(R.id.tv_haveAnAcc);
        spinnerUsertype = (Spinner) findViewById(R.id.spinner_usertype);
    }
}