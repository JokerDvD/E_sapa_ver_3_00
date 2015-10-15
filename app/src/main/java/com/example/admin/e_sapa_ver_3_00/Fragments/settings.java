package com.example.admin.e_sapa_ver_3_00.Fragments;


import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.e_sapa_ver_3_00.MainActivity.Fragment_activity;
import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.CustomList.CustomList;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.Preferences.preferenceSave_Load;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;
import com.rey.material.widget.Spinner;

import java.util.Locale;

public class settings extends Fragment {

    private int pageNumber;
    private preferenceSave_Load pref;
    private Spinner setting_spinner;
    private String[] setting_lis_lang, setting_list_theme;
    private ListView setting_list_view;
    private TextView textView;
    private Integer[] setting_image = {
            R.drawable.pic1,
            R.drawable.pic2,
            R.drawable.pic3,
            R.drawable.pic4,
            R.drawable.pic5,
            R.drawable.pic6,
            R.drawable.pic7,
            R.drawable.pic8,
            R.drawable.pic9,

    };

    public settings() {
    }

    public static settings newInstance(int page) {
        settings fragment = new settings();
        Bundle args = new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public static String getTitle(Context context, int position) {
        String fragment_name = context.getString(R.string.action_settings);
        return fragment_name;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        textView = (TextView) view.findViewById(R.id.textView9);

        pref=new preferenceSave_Load(getActivity());

        setting_lis_lang = getActivity().getResources().getStringArray(R.array.setting_lang);
        setting_list_theme = getActivity().getResources().getStringArray(R.array.setting_theme);

        setting_spinner = (Spinner) view.findViewById(R.id.set_lang_spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, setting_lis_lang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setting_spinner.setAdapter(adapter);

        setting_spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(Spinner spinner, View view, int i, long l) {
                String lang;
                spinner.removeView(view);

                switch (i) {
                    case 0:
                        lang = "kk";
                        break;
                    case 1:
                        lang = "ru";
                        break;

                    case 2:
                        lang = "en";
                        break;

                    default:
                        lang = "ru";
                        Toast.makeText(getActivity(), "Select Language", Toast.LENGTH_LONG).show();

                        break;

                }

                if (lang.equals(null)) {
                    Toast.makeText(getActivity()," ты никогда не увидишь это ",Toast.LENGTH_LONG).show();
                }else{
                    Locale locale = new Locale(lang);
                    Locale.setDefault(locale);
                    Configuration configuration = new Configuration();
                    configuration.locale = locale;
                    getActivity().getBaseContext().getResources().updateConfiguration(configuration, null);
                    pref.saveLangTag(resourceFile.lang_tag, lang);
                    pref.savePageNum(resourceFile.pageN_tag, 6);
                    Intent intent=new Intent(getActivity(), Fragment_activity.class);
                    startActivity(intent);
                }
            }
        });

        CustomList setting_customList = new CustomList(getActivity(), setting_list_theme, setting_image);
        setting_list_view = (ListView) view.findViewById(R.id.setting_list_view);

        setting_list_view.setAdapter(setting_customList);
        setting_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                int themeIndex = 0;
                switch (position) {
                    case 0:
                        themeIndex = R.drawable.pic1;
                        break;
                    case 1:
                        themeIndex = R.drawable.pic2;
                        break;
                    case 2:
                        themeIndex = R.drawable.pic3;
                        break;
                    case 3:
                        themeIndex = R.drawable.pic4;
                        break;

                    case 4:
                        themeIndex = R.drawable.pic5;
                        break;
                    case 5:
                        themeIndex = R.drawable.pic6;
                        break;
                    case 6:
                        themeIndex = R.drawable.pic7;
                        break;
                    case 7:
                        themeIndex = R.drawable.pic8;
                        break;
                    case 8:
                        themeIndex = R.drawable.pic9;
                        break;

                    default:
                        themeIndex = R.color.background_2;
                        break;
                }
                if (themeIndex != 0) {
                    pref.saveThemeTag(resourceFile.theme_tag, themeIndex);
                    getActivity().getWindow().getDecorView().setBackgroundResource(themeIndex);
                }
            }
        });
        return view;
    }
}