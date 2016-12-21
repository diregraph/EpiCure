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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nuwan.epicure.R;

public class edit_details_list_fragment extends Fragment {
    private Button btnCancelEditDisease;
    private edit_details_list_fragment editDiseaseDetailsFrag = this;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_details_list, container, false);
        init(view);

        btnCancelEditDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction fragTrans = getFragmentManager().beginTransaction();
                disease_fragment diseaseFrag = new disease_fragment();
                fragTrans.attach(editDiseaseDetailsFrag);
                fragTrans.replace(R.id.frmMain, diseaseFrag);
                fragTrans.commit();
            }
        });

        return view;
    }

    private void init(View view) {
        btnCancelEditDisease = (Button)view.findViewById(R.id.btn_cancel_edit_detail_list);
    }
}
