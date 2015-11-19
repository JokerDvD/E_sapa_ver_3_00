package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.CustomList.CustomList;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.SQLITE.dbFuncClass;

public class history extends Fragment {

    private View view;
    private dbFuncClass funcClass;
    private Animation animationBase;

    private ListView history_listView;
    private CustomList.history_list_view hist_adapter;

    public history() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        animationBase = AnimationUtils.loadAnimation(getActivity(), R.anim.down_anim);

        view = inflater.inflate(R.layout.fragment_history, container, false);
        view.startAnimation(animationBase);
        funcClass = new dbFuncClass(getActivity());
        hist_adapter = new CustomList.history_list_view(getActivity(),
                funcClass.get_all_barcode(),
                funcClass.get_all_description(),
                funcClass.get_all_result_boolean(),
                funcClass.get_all_dateTime());

        history_listView = (ListView) view.findViewById(R.id.history_list_view);
        history_listView.setAdapter(hist_adapter);
        history_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getActivity(), Html.fromHtml(funcClass.get_item_selected(position + 1).toString()), Toast.LENGTH_LONG);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
                historyWithMap mapFragment = new historyWithMap();
                Bundle bundle = new Bundle();
                bundle.putInt("Details", position+1);
                mapFragment.setArguments(bundle);
                mapFragment.show(getActivity().getFragmentManager(), "test");

            }
        });
        return view;
    }
}