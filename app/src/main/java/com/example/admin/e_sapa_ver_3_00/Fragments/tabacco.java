package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.admin.e_sapa_ver_3_00.R;

public class tabacco extends Fragment {

    private int pageNumber;

    public static tabacco newInstance(int page) {
        tabacco fragment = new tabacco();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public tabacco() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    public static String getTitle(Context context,int position){
        return "Page "+String.valueOf(position+1);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_tabacco, container, false);

        return result;
    }
}