package com.example.admin.e_sapa_ver_3_00.Fragments;


import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.admin.e_sapa_ver_3_00.MainActivity.Fragment_activity;
import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.Preferences.preferenceSave_Load;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;

import java.util.Locale;

public class settings extends Fragment implements View.OnClickListener {

    private int pageNumber;
    private preferenceSave_Load pref;
    private Spinner setting_spinner;
    private String[] setting_lis_lang;
    private View view;
    private Animation animationBase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        animationBase = AnimationUtils.loadAnimation(getActivity(), R.anim.down_anim);
        view = inflater.inflate(R.layout.fragment_settings, container, false);
        view.startAnimation(animationBase);
        pref = new preferenceSave_Load(getActivity());
        setting_lis_lang = getActivity().getResources().getStringArray(R.array.setting_lang);

        setting_spinner = (Spinner) view.findViewById(R.id.set_lang_spinner);
        setting_spinner.startAnimation(animationBase);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, setting_lis_lang);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setting_spinner.setAdapter(adapter);

        setting_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String lang;
                switch (position) {
                    case 1:
                        lang = "kk";
                        break;
                    case 2:
                        lang = "ru";
                        break;

                    case 3:
                        lang = "en";
                        break;

                    default:
                        lang="empty";
                        break;

                }

                if (lang.equals("en")||lang.equals("ru")||lang.equals("kk")) {
                    Locale locale = new Locale(lang);
                    Locale.setDefault(locale);
                    Configuration configuration = new Configuration();
                    configuration.locale = locale;
                    getActivity().getBaseContext().getResources().updateConfiguration(configuration, null);
                    pref.saveLangTag(resourceFile.lang_tag, lang);
                    pref.savePageNum(resourceFile.pageN_tag, 6);
                    Intent intent = new Intent(getActivity(), Fragment_activity.class);
                    getActivity().finish();
                    startActivity(intent);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Toast.makeText(getActivity(), "onNothingSelected", Toast.LENGTH_LONG).show();
            }

        });
        ImageButton set_i_b = (ImageButton) view.findViewById(R.id.set_i_b);
        set_i_b.setOnClickListener(this);

        ImageButton set_i_b_2 = (ImageButton) view.findViewById(R.id.set_i_b_2);
        set_i_b_2.setOnClickListener(this);
        ImageButton set_i_b_3 = (ImageButton) view.findViewById(R.id.set_i_b_3);
        set_i_b_3.setOnClickListener(this);

        ImageButton set_i_b_4 = (ImageButton) view.findViewById(R.id.set_i_b_4);
        set_i_b_4.setOnClickListener(this);

        ImageButton set_i_b_5 = (ImageButton) view.findViewById(R.id.set_i_b_5);
        set_i_b_5.setOnClickListener(this);

        ImageButton set_i_b_6 = (ImageButton) view.findViewById(R.id.set_i_b_6);
        set_i_b_6.setOnClickListener(this);


        return view;
    }

    public void onDestroyView() {
        super.onDestroyView();

    }

    @Override
    public void onClick(View v) {
        int themeIndex = 0;
        switch (v.getId()) {
            case R.id.set_i_b:
                themeIndex = R.drawable.back1;
                break;
            case R.id.set_i_b_2:
                themeIndex = R.drawable.back2;
                break;
            case R.id.set_i_b_3:
                themeIndex = R.drawable.back3;
                break;

            case R.id.set_i_b_4:
                themeIndex = R.color.theme_color_4;
                break;

            case R.id.set_i_b_5:
                themeIndex = R.color.theme_color_5;
                break;

            case R.id.set_i_b_6:
                themeIndex = R.color.theme_color_6;
                break;
            default:
                break;
        }
        if (themeIndex != 0) {
            pref.saveThemeTag(resourceFile.theme_tag, themeIndex);
            getActivity().getWindow().getDecorView().setBackgroundResource(themeIndex);
        }

    }
}