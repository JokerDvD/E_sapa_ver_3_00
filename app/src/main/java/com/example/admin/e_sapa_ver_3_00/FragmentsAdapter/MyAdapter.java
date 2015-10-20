package com.example.admin.e_sapa_ver_3_00.FragmentsAdapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.example.admin.e_sapa_ver_3_00.Fragments.alcohol;
import com.example.admin.e_sapa_ver_3_00.Fragments.history;
import com.example.admin.e_sapa_ver_3_00.Fragments.home;
import com.example.admin.e_sapa_ver_3_00.Fragments.settings;
import com.example.admin.e_sapa_ver_3_00.Fragments.tabacco;

public class MyAdapter extends FragmentPagerAdapter {
    private final Context context;

    public MyAdapter(Context context, FragmentManager mgr) {
        super(mgr);
        this.context = context;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        FragmentManager manager = ((Fragment) object).getFragmentManager();
        FragmentTransaction trans = manager.beginTransaction();
        trans.remove((Fragment) object);
        trans.commit();
        super.destroyItem(container, position, object);
    }

    @Override
    public int getCount() {
        return (5);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = home.newInstance(position);
                break;
           /* case 1:
                fragment = qrcode.newInstance(position);
                break;
            case 2:
                fragment = barcode.newInstance(position);
                break;*/
            case 1:
                fragment = tabacco.newInstance(position);
                break;
            case 2:
                fragment = alcohol.newInstance(position);
                break;
            case 3:
                fragment = history.newInstance(position);
                break;
            case 4:
                fragment = settings.newInstance(position);
                break;
            default:
                fragment = settings.newInstance(position);
                break;
        }
        return fragment;
    }

    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return home.getTitle(context, position);
            /*case 1:
                return qrcode.getTitle(context, position);
            case 2:
                return (barcode.getTitle(context, position));*/
            case 1:
                return (tabacco.getTitle(context, position));
            case 2:
                return alcohol.getTitle(context, position);
            case 3:
                return history.getTitle(context, position);
            case 4:
                return settings.getTitle(context, position);
            default:
                return settings.getTitle(context, position);

        }
    }
}