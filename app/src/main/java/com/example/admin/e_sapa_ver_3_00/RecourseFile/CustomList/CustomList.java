package com.example.admin.e_sapa_ver_3_00.RecourseFile.CustomList;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.e_sapa_ver_3_00.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Optimus on 18.09.2015.
 */
public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;

    public CustomList(Activity context,
                      String[] web, Integer[] imageId) {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }

    /**
     * Created by Admin on 16.10.2015.
     */
    public static class history_list_view extends ArrayAdapter<String> {
        private final Activity context;
        private List<String> code=new ArrayList<>();
        private final List<String> description;
        private final List<String> bool;

        public history_list_view(Activity context,
                          List<String>code, List<String> description,List<String>bool) {
            super(context, R.layout.list_single, code);
            this.context = context;
            this.code = code;
            this.bool=bool;
            this.description= description;

        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView = inflater.inflate(R.layout.history_list_view, null, true);

            TextView text_code = (TextView) rowView.findViewById(R.id.text_code);
            TextView text_description = (TextView) rowView.findViewById(R.id.text_description);
            TextView text_bool=(TextView)rowView.findViewById(R.id.text_bool);

            if(bool.get(position)=="1") {
                text_bool.setText(R.string.history_info_7);
                text_bool.setTextColor(getContext().getResources().getColor(R.color.hist_positive_color));
            }else {
                text_bool.setTextColor(getContext().getResources().getColor(R.color.hist_negative_color));
                text_bool.setText(R.string.history_info_8);
            }
            text_code.setText(Html.fromHtml(code.get(position).toString()));

            text_description.setText(Html.fromHtml(description.get(position).toString()));
            return rowView;
        }
    }
}
