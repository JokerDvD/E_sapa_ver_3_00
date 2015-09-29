package com.example.admin.e_sapa_ver_3_00;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    private final Context context;

    public MyAdapter(Context context, FragmentManager mgr) {
        super(mgr);
        this.context = context;
    }

    @Override
    public int getCount() {
        return (10);
    }

    @Override
    public Fragment getItem(int position) {
        return (ValidateTabacco.newInstance(position));
    }

    @Override
    public String getPageTitle(int position) {
        return (ValidateTabacco.getTitle(context, position));
    }
}