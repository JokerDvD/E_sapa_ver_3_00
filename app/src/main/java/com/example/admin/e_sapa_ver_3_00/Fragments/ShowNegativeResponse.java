package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.example.admin.e_sapa_ver_3_00.R;

/**
 * Created by Admin on 16.10.2015.
 */
public class ShowNegativeResponse extends DialogFragment implements DialogInterface.OnClickListener {
    View view;
    private String data;
    private TextView rf_text_view;

    public ShowNegativeResponse() {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        view = getActivity().getLayoutInflater().inflate(R.layout.negative_result_fragment, null);
        data=bundle.getString("Data");
        String []dataArray=data.split(",");
        rf_text_view = (TextView) view.findViewById(R.id.rf_text_view);
        rf_text_view.setText(Html.fromHtml(data));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return (builder.setTitle(R.string.show_text_info_1).setView(view)
                .setPositiveButton(R.string.show_text_info_2, this).create());
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
    }
}
