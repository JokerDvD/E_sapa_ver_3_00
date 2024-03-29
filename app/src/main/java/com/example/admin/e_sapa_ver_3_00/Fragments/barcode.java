package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.e_sapa_ver_3_00.R;

public class barcode extends Fragment {

    private int pageNumber;
    private static String fragment_name;

    public static barcode newInstance(int page) {
        barcode fragment = new barcode();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public barcode() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    public static String getTitle(Context context,int position){
        fragment_name=context.getResources().getString(R.string.nav_brandcode);
        return fragment_name;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_barcode, container, false);

        return result;
    }
}