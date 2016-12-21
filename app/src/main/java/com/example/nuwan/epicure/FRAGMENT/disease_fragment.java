package com.example.nuwan.epicure.FRAGMENT;

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


import com.example.nuwan.epicure.R;

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
                fragTrans.attach(diseaseFragment);
                fragTrans.replace(R.id.frmMain, addDiseaseFrag);
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
    }
}