package com.example.nuwan.epicure.FRAGMENT;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import com.example.nuwan.epicure.DAO.diseaseDAO;
import com.example.nuwan.epicure.DAO.userDAO;
import com.example.nuwan.epicure.DATABASE.database;
import com.example.nuwan.epicure.Models.user;
import com.example.nuwan.epicure.R;
import com.example.nuwan.epicure.constants;

/**
 * Created by nuwan on 12/20/2016.
 */

public class disease_fragment extends Fragment {
    private EditText etSearch;
    private TextView lnkAddDisease;
    private TextView lnkEditDetails;
    private TextView lnkAddDetails;
    private ListView lvDisease;
    private disease_fragment diseaseFragment = this;
    private diseaseDAO disease_dao;
    private String email;
    private userDAO user_dao;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_disease, container, false);
        init(view);


        lnkAddDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                add_disease_fragment addDiseaseFrag = new add_disease_fragment();
                addDiseaseFrag.setEmailStatus(email); //get sync status from internet
                fragTrans.attach(diseaseFragment);
                fragTrans.replace(R.id.frmMain, addDiseaseFrag);
                fragTrans.commit();
            }
        });

        lnkEditDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                edit_details_list_fragment editDiseaseListFrag = new edit_details_list_fragment();
                fragTrans.attach(diseaseFragment);
                fragTrans.replace(R.id.frmMain, editDiseaseListFrag);
                fragTrans.commit();
            }
        });

        lnkAddDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                add_detail_list_fragment addDetailListFrag = new add_detail_list_fragment();
                fragTrans.attach(diseaseFragment);
                fragTrans.replace(R.id.frmMain, addDetailListFrag);
                fragTrans.commit();
            }
        });

        return view;

    }



    private void init(View view) {
        etSearch = (EditText)view.findViewById(R.id.etSearchDisease);
        lvDisease = (ListView)view.findViewById(R.id.lstDisease);
        lnkAddDisease = (TextView)view.findViewById(R.id.tv_add_disease);
        lnkAddDetails = (TextView)view.findViewById(R.id.tv_add_details);
        lnkEditDetails = (TextView)view.findViewById(R.id.tv_edit_disease);
        database sqlDB = new database(getContext(), constants.database_name,null,1);
        user_dao = new userDAO(getContext(),sqlDB.getDatabase());
        disease_dao = new diseaseDAO(getContext(),sqlDB.getDatabase());
        user user = user_dao.getUser(email);
//        if(user.getRole().equals("User")){
//            lnkAddDisease.setVisibility(View.INVISIBLE);
//            lnkEditDetails.setVisibility(View.INVISIBLE);
//            lnkAddDetails.setVisibility(View.INVISIBLE);
//        }else{
//            lnkAddDisease.setVisibility(View.VISIBLE);
//        }
    }

    public void setEmailDiseaseFrag(String email){
        this.email = email;
    }
}