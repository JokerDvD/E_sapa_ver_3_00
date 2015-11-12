package com.example.admin.e_sapa_ver_3_00;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class WTF extends Fragment implements View.OnClickListener {

    double latitude, longitude;
    Button loginBtn/*, signupButton*/;
    EditText loginUsername, loginPassword;
    private boolean gps_enabled = false;
    private boolean network_enabled = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wt, container, false);

        loginUsername = (EditText) view.findViewById(R.id.loginUsername);
        loginPassword = (EditText) view.findViewById(R.id.loginPassword);

        loginBtn = (Button) view.findViewById(R.id.loginButton);
        loginBtn.setOnClickListener(this);
        /*signupButton =(Button)view.findViewById(R.id.signupButton);
        signupButton.setOnClickListener(this);*/
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
        }
    }
}