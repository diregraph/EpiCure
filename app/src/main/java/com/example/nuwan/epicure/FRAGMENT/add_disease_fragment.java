package com.example.nuwan.epicure.FRAGMENT;

import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nuwan.epicure.DAO.disease_additionDAO;
import com.example.nuwan.epicure.DATABASE.database;
import com.example.nuwan.epicure.Models.disease_addition;
import com.example.nuwan.epicure.R;
import com.example.nuwan.epicure.constants;

public class add_disease_fragment extends Fragment {
    private EditText etDisease;
    private Button btnAddDisease;
    private Button btnBackAddDisease;
    private add_disease_fragment addDiseaseFrag = this;
    private Spinner spinnerLocation;
    private disease_additionDAO diseaseAdditionDAO;
    private disease_addition diseaseAddition;
    private String email;
    private SQLiteDatabase sqlDB;
    //private String sync_status;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_disease, container, false);
        init(view);
        database database = new database(getActivity(), constants.database_name, null, 1 );
        sqlDB = database.getDatabase();
        diseaseAdditionDAO = new disease_additionDAO(getActivity(),sqlDB);


        btnAddDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etDisease.getText().toString().equals("") ){
                    Toast.makeText(getActivity(),"Add a disease",Toast.LENGTH_SHORT).show();
                } else if (spinnerLocation.getSelectedItem().toString().equals("Select Location") ){
                    Toast.makeText(getActivity(),"Select Location",Toast.LENGTH_SHORT).show();
                }else{
                    diseaseAddition = new disease_addition(etDisease.getText().toString(),
                            spinnerLocation.getSelectedItem().toString(),email );

                    diseaseAdditionDAO.addDisease(diseaseAddition);
                    FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                    disease_fragment diseaseFrag = new disease_fragment();
                    fragTrans.attach(addDiseaseFrag);
                    fragTrans.replace(R.id.frmMain, diseaseFrag);
                    fragTrans.commit();
                }
            }
        });

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
        spinnerLocation = (Spinner) view.findViewById(R.id.spinner_location);
        btnAddDisease = (Button)view.findViewById(R.id.btn_add_disease);
        btnBackAddDisease = (Button)view.findViewById(R.id.btn_cancel_add_disease);
    }

    public void setEmailStatus(String email){
        this.email = email;
       //this.sync_status = sync_status;
    }
}

