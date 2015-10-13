package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.SQLITE.dbFuncClass;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.dbObject.dbObject;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.MarkerOptions;
import com.rey.material.widget.Button;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class history extends Fragment implements View.OnClickListener {

    private int pageNumber;
    private Button history_btn_1, history_btn_2;
    private GoogleMap googleMap;
    private TextView history_text_view_1, history_text_view_2;
    private View view;
    private Spinner history_spinner;
    private List<String> history_list = new ArrayList<>();
    private dbFuncClass funcClass;
    private Animation animation;
    private RelativeLayout history_rl_2;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_history, container, false);

        history_rl_2 = (RelativeLayout) view.findViewById(R.id.relativeLayout2);
        history_rl_2.setVisibility(View.GONE);

        history_text_view_1 = (TextView) view.findViewById(R.id.history_data_result);
        history_text_view_2 = (TextView) view.findViewById(R.id.histrory_data_result_2);

        history_btn_1 = (Button) view.findViewById(R.id.history_btn_1);
        history_btn_1.setOnClickListener(this);
        history_btn_2 = (Button) view.findViewById(R.id.history_btn_2);
        history_btn_2.setOnClickListener(this);

        animation = AnimationUtils.loadAnimation(getActivity(), R.anim.down_anim);

        funcClass = new dbFuncClass(getActivity());

        history_list = funcClass.getFullDB();

        history_spinner = (Spinner) view.findViewById(R.id.history_spinner);
        final ArrayAdapter<String> histiry_Adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, history_list);
        histiry_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        history_spinner.setAdapter(histiry_Adapter);

        history_spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner spinner, View view, int position, long l) {
                spinner.removeView(view);
                history_rl_2.setVisibility(View.INVISIBLE);
                history_rl_2.startAnimation(animation);
                dbObject dbObject = funcClass.getDataObject(position+1);
                if (position+1 == 1) {

                    history_rl_2.setVisibility(View.GONE);
                    history_rl_2.startAnimation(animation);
                    Toast.makeText(getActivity(),R.string.history_info_1,Toast.LENGTH_LONG).show();


                } else {
                    if (dbObject.getResult().equals("1")) {
                        history_text_view_1.setText(R.string.history_info_7);
                        history_text_view_1.setTextColor(getResources().getColor(R.color.hist_positive_color));
                        history_text_view_2.setText(dbObject.getDataofresult());
                    } else {
                        history_text_view_1.setText(R.string.history_info_8);
                        history_text_view_1.setTextColor(getResources().getColor(R.color.hist_negative_color));
                        history_text_view_2.setText(Html.fromHtml(dbObject.getDataofresult()));
                    }

                    getMapPoints(position);
                }
            }
        });
        createMap();
        setPoint();
        return view;
    }

    private void createMap() {
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
    }

    private void setPoint() {
        for (int i = 0; i < history_list.size(); i++) {
            getMapPoints(i);
        }
    }

    private void getMapPoints(int i) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(funcClass.getLocation(i)).zoom(12).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        googleMap.addMarker(new MarkerOptions()
                .position(funcClass.getLocation(i)).title(history_list.get(i))/*.icon(BitmapDescriptorFactory.fromResource(R.drawable.testpic))*/);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.history_btn_1:
                googleMap.clear();
                break;
            case R.id.history_btn_2:
                setPoint();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        android.app.FragmentManager fragmentManager = getActivity().getFragmentManager();
        android.app.Fragment fragment = (fragmentManager.findFragmentById(R.id.map));
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.remove(fragment).commit();
    }
}