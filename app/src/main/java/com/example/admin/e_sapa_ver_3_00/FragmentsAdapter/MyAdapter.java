package com.example.admin.e_sapa_ver_3_00.FragmentsAdapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.admin.e_sapa_ver_3_00.Fragments.ValidateTabacco;
import com.example.admin.e_sapa_ver_3_00.Fragments.alcohol;
import com.example.admin.e_sapa_ver_3_00.Fragments.barcode;
import com.example.admin.e_sapa_ver_3_00.Fragments.history;
import com.example.admin.e_sapa_ver_3_00.Fragments.home;
import com.example.admin.e_sapa_ver_3_00.Fragments.qrcode;
import com.example.admin.e_sapa_ver_3_00.Fragments.settings;
import com.example.admin.e_sapa_ver_3_00.Fragments.tabacco;

public class MyAdapter extends FragmentPagerAdapter {
    private final Context context;

    public MyAdapter(Context context, FragmentManager mgr) {
        super(mgr);
        this.context = context;
    }

    @Override
    public int getCount() {
        return (11);
    }

    @Override
    public Fragment getItem(int position) {
        /*switch (position){
            case 0:
                return home.newInstance(position);
            break;
            case 1:
                return (qrcode.newInstance(position));
            case 2:
                return (barcode.newInstance(position));
            case 3:
                return (tabacco.newInstance(position));
            case 4:
                return (alcohol.newInstance(position));
            case 5:
                return (history.newInstance(position));
            case 6:
                return (settings.newInstance(position));
            default:
                return (ValidateTabacco.newInstance(position));
        }*/
        switch (position) {
            case 0:
                return home.newInstance(position);
            case 1:
                return  qrcode.newInstance(position);
            case 2:
                return (barcode.newInstance(position));
            case 3:
                return (tabacco.newInstance(position));
            case 4:
                return alcohol.newInstance(position);
            case 5:
                return history.newInstance(position);
            case 6:
                return settings.newInstance(position);
            default:
                return settings.newInstance(position);
            }

    }

    @Override
    public String getPageTitle(int position) {
        return (ValidateTabacco.getTitle(context, position));
    }
}