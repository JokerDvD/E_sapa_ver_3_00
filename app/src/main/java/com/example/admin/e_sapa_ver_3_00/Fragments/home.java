package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.admin.e_sapa_ver_3_00.R;
import com.rey.material.widget.Button;

import java.util.HashMap;

public class home extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {

    private int pageNumber;
    private SliderLayout mDemoSlider;
    private View view;
    private Button home_btn1, home_btn2, home_btn3, home_btn4, home_btn5, home_btn6, home_btn7;

    public home() {
    }

    public static home newInstance(int page) {
        home fragment = new home();
        Bundle args = new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public static String getTitle(Context context, int position) {
        String fragment_name = context.getString(R.string.nav_home);
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
        view = inflater.inflate(R.layout.fragment_home, container, false);

        /*home_btn1 = (Button) view.findViewById(R.id.home_btn1);
        home_btn2 = (Button) view.findViewById(R.id.home_btn2);
        home_btn3 = (Button) view.findViewById(R.id.home_btn3);
        home_btn4 = (Button) view.findViewById(R.id.home_btn4);
        home_btn5 = (Button) view.findViewById(R.id.home_btn5);
        home_btn6 = (Button) view.findViewById(R.id.home_btn6);
        home_btn7 = (Button) view.findViewById(R.id.home_btn7);

        home_btn1.setOnClickListener(this);
        home_btn2.setOnClickListener(this);
        home_btn3.setOnClickListener(this);
        home_btn4.setOnClickListener(this);
        home_btn5.setOnClickListener(this);
        home_btn6.setOnClickListener(this);
        home_btn7.setOnClickListener(this);*/

        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);

        HashMap<String, Integer> file_maps = new HashMap<>();
        file_maps.put("Hannibal", R.drawable.hannibal);
        file_maps.put("Big Bang Theory", R.drawable.bigbang);
        file_maps.put("House of Cards", R.drawable.house);
        file_maps.put("Game of Thrones", R.drawable.game_of_thrones);

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());

            textSliderView.description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra", name);
            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

        return view;
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /*@Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_btn1:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.CubeIn);
                break;
            case R.id.home_btn2:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
                break;
            case R.id.home_btn3:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Background2Foreground);
                break;
            case R.id.home_btn4:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
                break;
            case R.id.home_btn5:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomIn);
                break;
            case R.id.home_btn6:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
                break;
            case R.id.home_btn7:
                mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipPage);
                break;
        }
    }*/
}