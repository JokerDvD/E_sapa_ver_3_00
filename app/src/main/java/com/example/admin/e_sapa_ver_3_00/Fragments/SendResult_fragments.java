package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.admin.e_sapa_ver_3_00.R;

/**
 * Created by Admin on 14.10.2015.
 */


public class SendResult_fragments extends DialogFragment implements DialogInterface.OnClickListener,DialogInterface.OnDismissListener {
    private View view;
    private EditText srf_edit_text_1,srf_edit_text_2,srf_edit_text_3,srf_edit_text_4,srf_edit_text_5;
    private TextView srf_edit_text_6;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        view = getActivity().getLayoutInflater()
                .inflate(R.layout.send_request_fragments, null);
        srf_edit_text_1=(EditText)view.findViewById(R.id.TextContactName);
        srf_edit_text_2=(EditText)view.findViewById(R.id.TextContactPhone);
        srf_edit_text_3=(EditText)view.findViewById(R.id.TextEmail);
        srf_edit_text_4=(EditText)view.findViewById(R.id.TextLocation);
        srf_edit_text_5=(EditText)view.findViewById(R.id.TextDescription1);
        srf_edit_text_6=(TextView)view.findViewById(R.id.TextPuidType);
        Bundle supicode=getArguments();

        srf_edit_text_6.setText(supicode.getString("BarCode"));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return (builder.setTitle(R.string.srf_text_info_1).setView(view).setPositiveButton(R.string.srf_text_info_2, this).create());
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        srf_func_showdialog();

    }

    private void srf_func_showdialog() {
        final AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.srf_text_info_8)
                .setCancelable(true)
                .setTitle(R.string.srf_text_info_9)
                .setNegativeButton(R.string.comm_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }
}
