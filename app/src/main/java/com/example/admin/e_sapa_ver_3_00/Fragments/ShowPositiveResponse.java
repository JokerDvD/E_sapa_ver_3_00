package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.admin.e_sapa_ver_3_00.R;

import java.util.ArrayList;

/**
 * Created by Admin on 05.11.2015.
 */
public class ShowPositiveResponse extends DialogFragment implements DialogInterface.OnClickListener {
    View view;
    private String data;
    private TextView positive_fragment_1,positive_fragment_2,positive_fragment_3,positive_fragment_4,positive_fragment_5,positive_fragment_6,positive_fragment_7;

    public ShowPositiveResponse() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        view = getActivity().getLayoutInflater().inflate(R.layout.positive_result_fragment, null);
        data=bundle.getString("Data");
        ArrayList<String>dataArray;
        dataArray=getArguments().getStringArrayList("Data");

        positive_fragment_1 = (TextView) view.findViewById(R.id.positive_fragment_1);
        positive_fragment_2 = (TextView) view.findViewById(R.id.positive_fragment_2);
        positive_fragment_3 = (TextView) view.findViewById(R.id.positive_fragment_3);
        positive_fragment_4 = (TextView) view.findViewById(R.id.positive_fragment_4);
        positive_fragment_5 = (TextView) view.findViewById(R.id.positive_fragment_5);
        positive_fragment_6 = (TextView) view.findViewById(R.id.positive_fragment_6);
        positive_fragment_7 = (TextView) view.findViewById(R.id.positive_fragment_7);
        positive_fragment_1.setText(dataArray.get(0));
        positive_fragment_2.setText(dataArray.get(1));
        positive_fragment_3.setText(dataArray.get(2));
        positive_fragment_4.setText(dataArray.get(3));
        positive_fragment_5.setText(dataArray.get(4));
        positive_fragment_6.setText(dataArray.get(5));
        positive_fragment_7.setText(dataArray.get(6));

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return (builder.setTitle(R.string.show_text_info_1).setView(view)
                .setPositiveButton(R.string.show_text_info_2, this).create());
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    }
}