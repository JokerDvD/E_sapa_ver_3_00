package com.example.admin.e_sapa_ver_3_00;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {
    public MyAdapter(FragmentManager mgr) {
        super(mgr);
    }
    @Override
    public int getCount() {
        return(10);
    }
    @Override
    public Fragment getItem(int position) {
        return(ValidateTabacco.newInstance(position));
    }
}