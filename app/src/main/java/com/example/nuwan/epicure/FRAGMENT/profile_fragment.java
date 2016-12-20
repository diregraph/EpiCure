package com.example.nuwan.epicure.FRAGMENT;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.nuwan.epicure.R;

public class profile_fragment extends Fragment {
    private EditText etFName_profile;
    private EditText etLName_profile;
    private EditText etPassword_profile;
    private EditText etCPassword_profile;
    private Button btnUpdate;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        etFName_profile = (EditText)view.findViewById(R.id.input_fname_profile);
        etLName_profile = (EditText)view.findViewById(R.id.input_lname_profile);
        etPassword_profile = (EditText)view.findViewById(R.id.input_password_profile);
        etCPassword_profile = (EditText)view.findViewById(R.id.input_confirm_password_profile);
        btnUpdate = (Button)view.findViewById(R.id.btn_update_profile);
    }
}

