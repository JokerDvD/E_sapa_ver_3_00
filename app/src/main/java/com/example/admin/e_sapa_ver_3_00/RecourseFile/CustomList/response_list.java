package com.example.admin.e_sapa_ver_3_00.RecourseFile.CustomList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.e_sapa_ver_3_00.R;

import java.util.List;

/**
 * Created by Optimus on 18.09.2015.
 */
public class response_list extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] response_tag;
    private final List<String> response;

    public response_list(Activity context,
                         String[] response_tag, List<String> response) {
        super(context, R.layout.list_single, response_tag);
        this.context = context;
        this.response_tag = response_tag;
        this.response = response;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.response_layout_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.text_1);

        TextView imageView = (TextView) rowView.findViewById(R.id.text_2);
        txtTitle.setText(response_tag[position]);
        imageView.setText(response.get(position));

        return rowView;
    }
}
