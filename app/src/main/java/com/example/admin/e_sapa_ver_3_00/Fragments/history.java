package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.admin.e_sapa_ver_3_00.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rey.material.widget.Button;

public class history extends Fragment implements View.OnClickListener, OnMapReadyCallback {

    private int pageNumber;
    private Button history_btn_1,history_btn_2;
    private SupportMapFragment history_supp_map;
    private Animation history_animation_1;
    private TextView history_text_view_1,history_text_view_2;

    public static history newInstance(int page) {
        history fragment = new history();
        Bundle args=new Bundle();
        args.putInt("num", page);
        return fragment;
    }

    public history() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static String getTitle(Context context,int position){
        String fragment_name = context.getString(R.string.nav_history);
        return fragment_name;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_history, container, false);
        history_btn_1=(Button)view.findViewById(R.id.history_btn_1);
        history_btn_1.setOnClickListener(this);

        history_btn_2=(Button)view.findViewById(R.id.history_btn_2);
        history_btn_2.setOnClickListener(this);


        history_supp_map=(SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
        history_supp_map.getMapAsync(this);
        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng test=new LatLng(10,10);
        googleMap.addMarker(new MarkerOptions().position(test).title("test"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(test));
    }
}