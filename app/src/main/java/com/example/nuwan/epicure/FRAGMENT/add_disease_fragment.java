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
import android.widget.TextView;

import com.example.nuwan.epicure.R;

public class add_disease_fragment extends Fragment {
    private EditText etDisease;
    private EditText etLocation;
    private Button btnAddDisease;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_disease, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        etDisease = (EditText)view.findViewById(R.id.input_disease_name_add);
        etLocation = (EditText)view.findViewById(R.id.input_location_add);
        btnAddDisease = (Button)view.findViewById(R.id.btn_add_disease);
    }
}

