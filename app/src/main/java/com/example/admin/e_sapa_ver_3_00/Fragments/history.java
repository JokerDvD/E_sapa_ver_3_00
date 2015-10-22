package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.content.Context;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.CustomList.CustomList;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.SQLITE.dbFuncClass;
import com.google.android.gms.maps.GoogleMap;
import com.rey.material.widget.Button;
import com.rey.material.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class history extends Fragment  {

    private int pageNumber;
    private Button history_btn_1, history_btn_2;
    private GoogleMap googleMap;
    private TextView history_text_view_1, history_text_view_2;

    @Override
    public void onStart() {
        super.onStart();
                
    }

    private View view;
    private Spinner history_spinner;
    private List<String> history_list = new ArrayList<>();
    private List<String> history_list2 = new ArrayList<>();
    private dbFuncClass funcClass;
    private Animation animation;
    private RelativeLayout history_rl_2;
    private Location hist_location;
    private ListView history_listView;
    private CustomList.history_list_view hist_adapter;

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
       funcClass=new dbFuncClass(getActivity());
        hist_adapter =new CustomList.history_list_view(getActivity(),
                funcClass.get_all_barcode(),
                funcClass.get_all_description(),
                funcClass.get_all_result());

        history_listView=(ListView)view.findViewById(R.id.history_list_view);
        history_listView.setAdapter(hist_adapter);
        history_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              Toast toast=  Toast.makeText(getActivity(), Html.fromHtml(funcClass.get_item_selected(position + 1).toString()), Toast.LENGTH_LONG);
                      toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        });

        return view;
    }

private class load_data_of_BD extends AsyncTask<Void,Void,Void>{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        
        return null;
    }
}

}