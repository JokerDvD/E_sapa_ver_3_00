package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.rey.material.widget.Button;

public class history extends Fragment implements View.OnClickListener {

    private int pageNumber;
    private Button history_btn_1, history_btn_2;
    /*private SupportMapFragment history_supp_map;*/
    private GoogleMap googleMap;
    private Animation history_animation_1;
    private TextView history_text_view_1, history_text_view_2;
    private View view;
    private boolean test = false;

    public history() {
    }

    public static history newInstance(int page) {
        history fragment = new history();
        Bundle args = new Bundle();
        args.putInt("num", page);
        return fragment;
    }

    public static String getTitle(Context context, int position) {
        String fragment_name = context.getString(R.string.nav_history);
        return fragment_name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState == null) {
            test = true;
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history, container, false);
        history_btn_1 = (Button) view.findViewById(R.id.history_btn_1);
        history_btn_1.setOnClickListener(this);

        history_btn_2 = (Button) view.findViewById(R.id.history_btn_2);
        history_btn_2.setOnClickListener(this);
        try {
            if (null == googleMap) {

                googleMap = ((MapFragment) getActivity().getFragmentManager().findFragmentById(R.id.map)).getMap();
                googleMap.setMyLocationEnabled(true);
                googleMap.getUiSettings().setMyLocationButtonEnabled(true);
                googleMap.getUiSettings().setAllGesturesEnabled(true);
                if (googleMap.equals(null)) {
                    Toast.makeText(getActivity(), "Error  creating map", Toast.LENGTH_LONG).show();
                }
            }
        } catch (NullPointerException exception) {
            Log.d(resourceFile.LOG_TAG, exception.toString());
        }

        return view;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        android.app.FragmentManager fragmentManager = getActivity().getFragmentManager();
        android.app.Fragment fragment = (fragmentManager.findFragmentById(R.id.map));
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment).commit();
        /*if (googleMap.equals(null)) {
            android.app.FragmentManager fragmentManager = getActivity().getFragmentManager();
            android.app.Fragment fragment = (fragmentManager.findFragmentById(R.id.map));
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment).commit();

            if (view.equals(null)) {
                ViewGroup parent = (ViewGroup) view.getParent();
                if (parent != null) {
                    parent.removeView(view);
                }
            }
        }*/

    }
}