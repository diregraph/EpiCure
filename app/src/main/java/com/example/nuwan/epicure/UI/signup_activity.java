package com.example.nuwan.epicure.UI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nuwan.epicure.DATABASE.database;
import com.example.nuwan.epicure.R;

public class signup_activity extends AppCompatActivity {
    private EditText etFName_signup;
    private EditText etLName_signup;
    private EditText etUsername_signup;
    private EditText etPassword_signup;
    private EditText etCPassword_signup;
    private Button btnCreate;
    private database sqliteDB;
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
                                !strPassword.isEmpty() && !strCPassword.isEmpty() && !strUsertype.isEmpty() ) {
                            Intent i = new Intent(getApplicationContext(), home_activity.class);
                            i.putExtra("key_email",charseqUsername.toString()); // have to get from the json
                            sqliteDB = new database(getApplicationContext(),"EpiCure",null, 1);
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

        spinnerUsertype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerUsertype.getSelectedItem().toString().equals("Normal")){
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
        etFName_signup = (EditText) findViewById(R.id.input_fname_signup);
        etLName_signup = (EditText) findViewById(R.id.input_lname_profile);
        etUsername_signup = (EditText) findViewById(R.id.input_email_signup);
        etPassword_signup = (EditText) findViewById(R.id.input_password_profile);
        etCPassword_signup = (EditText) findViewById(R.id.input_confirm_password_profile);
        btnCreate = (Button) findViewById(R.id.btn_create);
        tvHaveAnAcc = (TextView) findViewById(R.id.tv_haveAnAcc);
        spinnerUsertype = (Spinner) findViewById(R.id.spinner_usertype);
        etRegNum = (EditText) findViewById(R.id.input_regnum_signup);
    }
}
