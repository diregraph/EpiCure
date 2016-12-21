package com.example.nuwan.epicure.FRAGMENT;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nuwan.epicure.R;

public class add_detail_list_fragment extends Fragment {
    private Button btnCancelAddDisease;
    private add_detail_list_fragment addDiseaseDetailsFrag = this;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_detail_list, container, false);
        init(view);

        btnCancelAddDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                disease_fragment diseaseFrag = new disease_fragment();
                fragTrans.attach(addDiseaseDetailsFrag);
                fragTrans.replace(R.id.frmMain, diseaseFrag);
                fragTrans.commit();
            }
        });

        return view;
    }

    private void init(View view) {
        btnCancelAddDisease = (Button)view.findViewById(R.id.btn_cancel_add_detail_list);
    }
}
