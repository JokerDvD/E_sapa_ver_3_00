package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.admin.e_sapa_ver_3_00.R;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.Connection;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.CustomList.response_list;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.SQLITE.dbFuncClass;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.other.GPS;
import com.example.admin.e_sapa_ver_3_00.RecourseFile.resourceFile;
import com.rey.material.widget.Button;
import com.rey.material.widget.EditText;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class alcohol extends Fragment implements View.OnClickListener {

    private static String fragment_name;
    private int pageNumber;
    private View view;
    private Connection alco_connection;
    private GPS gps_class;
    private String[] positive_response;
    private dbFuncClass db_helper_class;
    private RelativeLayout alco_response;
    private LinearLayout alco_header;
    private ListView alco_list_view;
    private Animation animationBase;
    private ImageView alco_image_captcha;
    private EditText alco_text_captcha;
    private Button alco_button;
    private EditText alco_part_1t;
    private EditText alco_part_2t;
    private EditText alco_part_3t;
    private TextView alco_result;
    private ProgressDialog dialog;
    private String valueFormBuildId;
    private String captcha_token;
    private String captcha_sid;
    private String captchaReferrer;
    private String alco_series_Numeration;
    private EditText alco_series_label;
    private String alco_series_label_text;
    private String alco_captcha_text;
    private Document doc;
    private List<String> alco_list_response = new ArrayList<>();
    private String alco_captcha_referrer;


    public alcohol() {
    }

    public static alcohol newInstance(int page) {
        alcohol fragment = new alcohol();
        Bundle args = new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public static String getTitle(Context context, int position) {
        fragment_name = context.getResources().getString(R.string.nav_alcohol);
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

        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            view = inflater.inflate(R.layout.fragment_alcohol, container, false);

            alco_connection = new Connection();
            gps_class = new GPS(getActivity());

            animationBase = AnimationUtils.loadAnimation(getActivity(), R.anim.down_anim);
            alco_header = (LinearLayout) view.findViewById(R.id.alco_header);
            alco_header.startAnimation(animationBase);

            alco_response = (RelativeLayout) view.findViewById(R.id.alco_response);


            alco_list_view = (ListView) view.findViewById(R.id.alco_list_view);
            alco_list_view.setVisibility(View.GONE);

            RelativeLayout RL = (RelativeLayout) view.findViewById(R.id.alco_body);
            RL.startAnimation(animationBase);

            db_helper_class = new dbFuncClass(getActivity());
            positive_response = getResources().getStringArray(R.array.common_array_1);

            alco_image_captcha = (ImageView) view.findViewById(R.id.alco_captcha_image);
            alco_text_captcha = (EditText) view.findViewById(R.id.alco_captcha_text);

            alco_button = (Button) view.findViewById(R.id.alco_button);
            alco_button.setOnClickListener(this);

            alco_part_1t = (EditText) view.findViewById(R.id.alco_part1a);
            alco_part_2t = (EditText) view.findViewById(R.id.alco_part2a);
            alco_part_3t = (EditText) view.findViewById(R.id.alco_part3a);

            alco_result = (TextView) view.findViewById(R.id.alco_result);
            alco_result.setVisibility(View.GONE);
            alco_series_label = (EditText) view.findViewById(R.id.alco_series_label);

            new alco_get_captcha_1().execute();
        } else {

            Toast.makeText(getActivity(), "Connect To Internet", Toast.LENGTH_LONG).show();

        }
        return view;
    }

    @Override
    public void onClick(View view) {

        if (alco_part_1t.length() != 0 && alco_part_2t.length() != 0 && alco_part_3t.length() != 0 && alco_text_captcha.length()!=0) {

            if (gps_class.canGetLocation()) {
                alco_result.setVisibility(View.GONE);
                alco_list_view.setVisibility(View.GONE);
                alco_series_Numeration = alco_part_1t.getText().toString().replaceAll(" ", "") + alco_part_2t.getText().toString().replaceAll(" ", "") + alco_part_3t.getText().toString().replaceAll(" ", "");
                alco_series_label_text = alco_series_label.getText().toString().replaceAll(" ", "");
                alco_captcha_text = alco_text_captcha.getText().toString().replaceAll(" ", "");
                new alco_validaty().execute(alco_captcha_text);
                new alco_get_captcha_2().execute();


            } else {

                Toast.makeText(getActivity(),R.string.comm_no_gps,Toast.LENGTH_LONG).show();

            }

        } else {
            YoYo.with(Techniques.BounceInRight).duration(1000).playOn(alco_part_1t);
            YoYo.with(Techniques.BounceInRight).duration(1000).playOn(alco_part_2t);
            YoYo.with(Techniques.BounceInRight).duration(1000).playOn(alco_part_3t);
            YoYo.with(Techniques.BounceInRight).duration(1000).playOn(alco_text_captcha);
            YoYo.with(Techniques.BounceInRight).duration(1000).playOn(alco_image_captcha);

        }

    }

    private class alco_get_captcha_1 extends AsyncTask<Void, Void, String> {


        @Override
        protected void onPreExecute() {

            dialog = new ProgressDialog(getActivity());
            dialog.setMessage(getResources().getString(R.string.alco_text_info_5));
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            StringBuffer response = new StringBuffer();
            try {
                URL url = new URL(resourceFile.urlForAlcohol);
                HttpURLConnection alco_conn = (HttpURLConnection) url.openConnection();
                alco_conn.setRequestMethod("GET");
                InputStream is = alco_conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                Document doc = Jsoup.parse(response.toString());
                Elements divCaptcha = doc.select("div.captcha").eq(0);
                Elements input = divCaptcha.select("input");
                Elements div_tags = doc.select("form.pure-form").select("input");
                for (Element build_id : div_tags) {
                    String id = build_id.attr("name");
                    if (id.equals("form_build_id")) {
                        valueFormBuildId = build_id.attr("value");
                    }
                }
                for (Element link : input) {
                    String test = link.attr("name");
                    if (test.equals("captcha_token")) {
                        captcha_token = link.attr("value");
                    } else if (test.equals("captcha_sid")) {
                        captcha_sid = link.attr("value");
                    }
                }
                Elements img = divCaptcha.select("img").eq(0);
                alco_captcha_referrer = img.attr("src");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return alco_captcha_referrer;
        }

        @Override
        protected void onPostExecute(String url) {
            super.onPostExecute(url);
            Picasso.with(getActivity())
                    .load("http://kgd.gov.kz" + url)
                    .placeholder(R.drawable.logo_main)
                    .error(R.drawable.logo_main)
                    .into(alco_image_captcha);
            dialog.hide();
            dialog.dismiss();
        }
    }

    private class alco_get_captcha_2 extends AsyncTask<Void, Void, String> {



        @Override
        protected String doInBackground(Void... params) {
            StringBuffer response = new StringBuffer();
            try {
                URL url = new URL(resourceFile.urlForAlcohol);
                HttpURLConnection alco_conn = (HttpURLConnection) url.openConnection();
                alco_conn.setRequestMethod("GET");
                InputStream is = alco_conn.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = rd.readLine()) != null) {
                    response.append(line);
                    response.append('\r');
                }
                rd.close();
                Document doc = Jsoup.parse(response.toString());
                Elements divCaptcha = doc.select("div.captcha").eq(0);
                Elements input = divCaptcha.select("input");
                Elements div_tags = doc.select("form.pure-form").select("input");
                for (Element build_id : div_tags) {
                    String id = build_id.attr("name");
                    if (id.equals("form_build_id")) {
                        valueFormBuildId = build_id.attr("value");
                    }
                }
                for (Element link : input) {
                    String test = link.attr("name");
                    if (test.equals("captcha_token")) {
                        captcha_token = link.attr("value");
                    } else if (test.equals("captcha_sid")) {
                        captcha_sid = link.attr("value");
                    }
                }
                Elements img = divCaptcha.select("img").eq(0);
                alco_captcha_referrer = img.attr("src");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return alco_captcha_referrer;
        }

        @Override
        protected void onPostExecute(String url) {
            super.onPostExecute(url);
            Picasso.with(getActivity())
                    .load("http://kgd.gov.kz" + url)
                    .placeholder(R.drawable.logo_main)
                    .error(R.drawable.logo_main)
                    .into(alco_image_captcha);
        }
    }

    private class alco_validaty extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(getActivity());
            dialog.setMessage("Send request ... ");
            dialog.setCancelable(false);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... captcha_text) {
            resourceFile.latitude = gps_class.getLatitude();
            resourceFile.longitude = gps_class.getLongitude();
            return alco_connection.Connection("method=series&series=" + alco_series_label_text + "&number=" + alco_series_Numeration + "&id_code=&captcha_sid=" + captcha_sid +
                    "&captcha_token=" + captcha_token + "&captcha_response=" + captcha_text[0] + "&form_id=product_check_form&_triggering_element_name=op&form_build_id=" + valueFormBuildId);

        }

        @Override
        protected void onPostExecute(String result) {
            String data = null;
            dialog.hide();
            dialog.dismiss();

            try {
                JSONArray jsonarray = new JSONArray(result);
                for (int i = 0; i < jsonarray.length(); i++) {
                    JSONObject jsonobject = jsonarray.getJSONObject(i);

                    if (!jsonobject.optString("data").isEmpty()) {
                        data = jsonobject.optString("data");
                    }
                }

                if (!data.isEmpty()) {

                    if (data.startsWith("<div id=\"results\">")) {

                        doc = Jsoup.parse(data);
                        Elements tr_odd = doc.select("tr.odd");
                        Elements td = tr_odd.select("td");

                        if (!td.isEmpty()) {

                            alco_list_view.setVisibility(View.VISIBLE);

                            for (Element tagTd : td) {
                                String span = tagTd.select("span").toString();
                                alco_list_response.add(String.valueOf(Html.fromHtml(span)));
                            }

                            boolean bool = true;
                            db_helper_class.writeSQLITE(alco_series_label_text + alco_series_Numeration, bool, alco_list_response.toString(), resourceFile.latitude, resourceFile.longitude);
                            response_list alco_adapter = new response_list(getActivity(), positive_response, alco_list_response);

                            alco_list_view.setVisibility(View.VISIBLE);
                            alco_list_view.setAdapter(alco_adapter);

                            alco_result.startAnimation(animationBase);
                            alco_result.setVisibility(View.GONE);
                        } else {

                            alco_result.setVisibility(View.VISIBLE);
                            doc = Jsoup.parse(data);

                            Elements divResults = doc.select("div#results");
                            Elements pure_form = divResults.select("p");

                            alco_result.setText(Html.fromHtml(pure_form.toString()));

                            alco_list_view.setVisibility(View.GONE);
                        }

                    } else {

                        alco_result.setVisibility(View.VISIBLE);
                        alco_result.setText(Html.fromHtml(data));

                        alco_list_view.setVisibility(View.GONE);

                    }

                } else {
                    Toast.makeText(getActivity(), "You never see that message", Toast.LENGTH_SHORT).show();

                }


            } catch (JSONException e1) {
                e1.printStackTrace();

            }
        }
    }
}