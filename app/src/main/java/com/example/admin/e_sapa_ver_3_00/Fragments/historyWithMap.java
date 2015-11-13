package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.admin.e_sapa_ver_3_00.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class historyWithMap extends DialogFragment implements DialogInterface.OnClickListener {
    private View view;
    static final LatLng TutorialsPoint = new LatLng(21 , 57);
    private GoogleMap googleMap;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_history_with_map, null);
Bundle bundle=getArguments();

        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(TutorialsPoint).title("TutorialsPoint"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return (builder.setTitle(R.string.show_text_info_1).setView(view)
                .setPositiveButton(R.string.show_text_info_2, this).create());
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}