package com.example.admin.e_sapa_ver_3_00;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ValidateTabacco extends Fragment {

    private int pageNumber;

    public static ValidateTabacco newInstance(int page) {
        ValidateTabacco fragment = new ValidateTabacco();
        Bundle args=new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public ValidateTabacco() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    static String getTitle(Context context,int position){
        return "Page "+String.valueOf(position+1);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_validate_tabacco, container, false);
        TextView pageHeader=(TextView)result.findViewById(R.id.displayText);
        pageHeader.setText("Фрагмент " + String.valueOf(pageNumber+1));
        return result;
    }
}