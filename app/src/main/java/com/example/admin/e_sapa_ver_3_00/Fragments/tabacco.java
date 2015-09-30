package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.e_sapa_ver_3_00.R;

public class tabacco extends Fragment {

    private int pageNumber;
    private com.rey.material.widget.Spinner tabac_spinner;

    public tabacco() {
    }

    public static tabacco newInstance(int page) {
        tabacco fragment = new tabacco();
        Bundle args = new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public static String getTitle(Context context, int position) {
        String fragment_name = context.getString(R.string.nav_smoking);
        return fragment_name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tabacco, container, false);

        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

        } else {
            view = inflater.inflate(R.layout.no_connection_layout, container,
                    false);
        }


        return view;
    }
}