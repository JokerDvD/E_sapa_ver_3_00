package com.example.admin.e_sapa_ver_3_00.RecourseFile.CustomList;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.e_sapa_ver_3_00.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Optimus on 18.09.2015.
 */
public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] setting_list_theme;

    public CustomList(Activity context, String[] text) {
        super(context, R.layout.list_single, text);
        this.context = context;
        this.setting_list_theme = text;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        txtTitle.setText(setting_list_theme[position]);

        return rowView;
    }

    /**
     * Created by Admin on 16.10.2015.
     */
    public static class history_list_view extends ArrayAdapter<String> {

        private final Activity context;

        private List<String> dateTime = new ArrayList<>();
        private List<String> code = new ArrayList<>();
        private List<String> description = new ArrayList<>();
        private List<String> bool = new ArrayList<>();

        public history_list_view(Activity context, List<String> code, List<String> description, List<String> bool, List<String> dateTime) {
            super(context, R.layout.list_single, code);
            this.context = context;
            this.code = code;
            this.bool = bool;
            this.description = description;
            this.dateTime = dateTime;

        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            LayoutInflater inflater = context.getLayoutInflater();

            View rowView = inflater.inflate(R.layout.history_list_view, null, true);
            if(dateTime.get(position) !="No data to show") {
                TextView text_code = (TextView) rowView.findViewById(R.id.text_code);
                TextView text_description = (TextView) rowView.findViewById(R.id.text_description);
                TextView text_bool = (TextView) rowView.findViewById(R.id.text_bool);
                TextView text_dateTime = (TextView) rowView.findViewById(R.id.text_dateTime);
                int bool_int = Integer.parseInt(bool.get(position));

                if (bool_int == 1) {
                    text_bool.setText(R.string.history_info_7);
                    text_bool.setTextColor(getContext().getResources().getColor(R.color.hist_positive_color));
                } else {
                    text_bool.setTextColor(getContext().getResources().getColor(R.color.hist_negative_color));
                    text_bool.setText(R.string.history_info_8);
                }

                text_code.setText(Html.fromHtml(code.get(position).toString()));
                text_description.setText(Html.fromHtml(description.get(position).toString()));
                text_dateTime.setText(dateTime.get(position));
            }
            return rowView;
        }
    }
}
