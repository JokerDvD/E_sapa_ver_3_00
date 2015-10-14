package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.Connection;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.Parsers.SaxParser;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.SQLITE.dbFuncClass;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.other.BuildingString;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.other.GPS;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;
import com.rey.material.widget.Button;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class tabacco extends Fragment implements View.OnClickListener {

    private int pageNumber;
    private View view;
    private TextView tab_edit_text_view_1, tab_edit_text_view_2, tab_edit_text_view_3, tab_edit_text_view_4;
    private Button tab_button_1;
    private Connection tab_connection;
    private GPS tab_gps;
    private dbFuncClass tab_FuncClass;
    private BuildingString tab_BuildingString;
    private SendResult_fragments srf;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();

        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {

            view = inflater.inflate(R.layout.fragment_tabacco, container, false);

            tab_gps = new GPS(getActivity());
            if (tab_gps.canGetLocation()) {

                tab_FuncClass = new dbFuncClass(getActivity());

                tab_connection = new Connection();

                tab_edit_text_view_1 = (EditText) view.findViewById(R.id.tabac_part1t);
                tab_edit_text_view_2 = (EditText) view.findViewById(R.id.tabac_part2t);
                tab_edit_text_view_3 = (EditText) view.findViewById(R.id.tabac_part3t);
                tab_edit_text_view_4 = (EditText) view.findViewById(R.id.tabac_part4t);

                tab_button_1 = (Button) view.findViewById(R.id.tabac_sendrequest_btn);
                tab_button_1.setOnClickListener(this);

            } else {
                tab_gps.showSettingsAlert();
            }
        } else {

            view = inflater.inflate(R.layout.no_connection_layout, container,
                    false);
            tab_func_turn_wifi();
        }
        return view;
    }

    private void tab_func_turn_wifi() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        // Setting Dialog Title
        alertDialog.setTitle(getResources().getString(R.string.tabac_textInfo_9));

        // Setting Dialog Message
        alertDialog.setMessage(getResources().getString(R.string.tabac_textInfo_7));

        // Setting Icon to Dialog
        // alertDialog.setIcon(R.drawable.ic_launcher);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton(getResources().getString(R.string.tabac_textInfo_8),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        // Activity transfer to wifi settings
                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                    }
                });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton(getResources().getString(R.string.comm_text_CANCEL),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event

                        dialog.cancel();
                    }
                });

        // Showing Alert Message
        alertDialog.show();
    }

    @Override
    public void onClick(View v) {
        if (tab_edit_text_view_1.length() != 0 && tab_edit_text_view_2.length() != 0 && tab_edit_text_view_3.length() != 0 && tab_edit_text_view_4.length() != 0) {

            tab_BuildingString = new BuildingString();
            tab_BuildingString.SoapActionBuildString(tab_edit_text_view_1.getText().toString().replaceAll(" ", ""),
                    tab_edit_text_view_2.getText().toString().replaceAll(" ", ""),
                    tab_edit_text_view_3.getText().toString().replaceAll(" ", ""),
                    tab_edit_text_view_4.getText().toString().replaceAll(" ", ""),
                    123123123,
                    resourceFile.CityText);

            resourceFile.latitude = tab_gps.getLatitude();
            resourceFile.longitude = tab_gps.getLongitude();

            new CheckCodeAsynkTask().execute();


        } else {
            YoYo.with(Techniques.Bounce).duration(1000).playOn(tab_edit_text_view_1);
            YoYo.with(Techniques.Bounce).duration(1000).playOn(tab_edit_text_view_2);
            YoYo.with(Techniques.Bounce).duration(1000).playOn(tab_edit_text_view_3);
            YoYo.with(Techniques.Bounce).duration(1000).playOn(tab_edit_text_view_4);
        }
    }

    private void tab_func_show_Alert_dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.tabac_textInfo_3)
                .setMessage(R.string.tabac_textInfo_3)
                .setCancelable(true)
                .setNegativeButton(R.string.tabac_textInfo_5,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        })
                .setPositiveButton(R.string.comm_text_SEND,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Bundle bundle = new Bundle();
                                bundle.putString("BarCode", resourceFile.SupiCode);

                                srf = new SendResult_fragments();
                                srf.setArguments(bundle);
                                srf.show(getActivity().getFragmentManager(), "login");

                                dialog.cancel();
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private class CheckCodeAsynkTask extends AsyncTask<Void, Void, String> {
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
//            dialog.setMessage(getResources().getString(R.string.please));
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            return tab_connection.Connection();
        }

        @Override
        protected void onPostExecute(String values) {
            dialog.hide();
            dialog.dismiss();

            try {
                BufferedReader br = new BufferedReader(new StringReader(values));
                InputSource is = new InputSource(br);
                SaxParser parser = new SaxParser();
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser sp = factory.newSAXParser();
                XMLReader reader = sp.getXMLReader();
                reader.setContentHandler(parser);
                reader.parse(is);
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (resourceFile.IsValid) {
                tab_FuncClass.writeSQLITE(resourceFile.SupiCode,
                        resourceFile.IsValid,
                        resourceFile.BatchStatus + " " + resourceFile.BrandDescription,
                        resourceFile.latitude,
                        resourceFile.longitude);
            } else {
                tab_FuncClass.writeSQLITE(resourceFile.SupiCode,
                        resourceFile.IsValid,
                        "Продукт не прошел провеку",
                        resourceFile.latitude,
                        resourceFile.longitude);
                tab_func_show_Alert_dialog();
            }
        }
    }
}