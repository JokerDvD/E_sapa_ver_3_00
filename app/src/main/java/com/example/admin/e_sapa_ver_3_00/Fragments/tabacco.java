package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.admin.e_sapa_ver_3_00.R;
import com.rey.material.widget.Spinner;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabacco, container, false);
        tabac_spinner = (Spinner) view.findViewById(R.id.tabac_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.tabac_city, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tabac_spinner.setAdapter(adapter);
//        tabac_spinner.setAnimation();
        return view;
    }
}