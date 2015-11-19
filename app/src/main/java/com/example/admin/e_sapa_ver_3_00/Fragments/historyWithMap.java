package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.SQLITE.dbFuncClass;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class historyWithMap extends DialogFragment implements DialogInterface.OnClickListener {
    private LatLng latLngProduct;
    private View view;
    private GoogleMap googleMap;
    private dbFuncClass funcClass;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_history_with_map, null);
        Bundle bundle = getArguments();
        funcClass=new dbFuncClass(getActivity());
        latLngProduct=funcClass.get_location(bundle.getInt("Details"));
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().
                        findFragmentById(R.id.map)).getMap();
            }
            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            Marker TP = googleMap.addMarker(new MarkerOptions().
                    position(latLngProduct).title("Test Point"));
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLngProduct, 13);
            googleMap.animateCamera(cameraUpdate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return (builder.setTitle(R.string.show_text_info_1).setView(view)
                .setPositiveButton(R.string.show_text_info_2, this).create());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Fragment fragment = (getFragmentManager().findFragmentById(R.id.map));
        FragmentTransaction ft = getActivity().getFragmentManager().beginTransaction();
        ft.remove(fragment);
        ft.commit();

    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}