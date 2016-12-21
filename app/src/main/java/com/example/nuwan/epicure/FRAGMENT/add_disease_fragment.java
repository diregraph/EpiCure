package com.example.nuwan.epicure.FRAGMENT;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.nuwan.epicure.R;

public class add_disease_fragment extends Fragment {
    private EditText etDisease;
    private EditText etLocation;
    private Button btnAddDisease;
    private Button btnBackAddDisease;
    private add_disease_fragment addDiseaseFrag = this;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_disease, container, false);
        init(view);

        btnBackAddDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                disease_fragment diseaseFrag = new disease_fragment();
                fragTrans.attach(addDiseaseFrag);
                fragTrans.replace(R.id.frmMain, diseaseFrag);
                fragTrans.commit();
            }
        });

        return view;
    }

    private void init(View view) {
        etDisease = (EditText)view.findViewById(R.id.input_disease_name_add);
        etLocation = (EditText)view.findViewById(R.id.input_location_add);
        btnAddDisease = (Button)view.findViewById(R.id.btn_add_disease);
        btnBackAddDisease = (Button)view.findViewById(R.id.btn_cancel_add_disease);
    }
}

