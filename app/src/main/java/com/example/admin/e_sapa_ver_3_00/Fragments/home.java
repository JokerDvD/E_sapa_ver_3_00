package com.example.admin.e_sapa_ver_3_00.Fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
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

import java.util.HashMap;

public class home extends Fragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


    private View view;
    private SliderLayout mDemoSlider;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        mDemoSlider = (SliderLayout) view.findViewById(R.id.slider);

        Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.drawable.fon_6);
        Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.fon_3);
        Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.fon_7);

        Integer[] imageCo={R.drawable.fon_6,R.drawable.fon_3,R.drawable.fon_7};

        HashMap<String, Integer> file_maps = new HashMap<>();
        file_maps.put(getResources().getString(R.string.home_text_info_6), R.drawable.fon_3);
        file_maps.put(getResources().getString(R.string.home_text_info_3), R.drawable.fon_3);
        file_maps.put(getResources().getString(R.string.home_text_info_7), R.drawable.fon_3);
        file_maps.put(getResources().getString(R.string.home_text_info_1), R.drawable.fon_3);
/*

        file_maps.put(getResources().getString(R.string.home_text_info_6), bitmap1);
        file_maps.put(getResources().getString(R.string.home_text_info_3), bitmap2);
        file_maps.put(getResources().getString(R.string.home_text_info_7), bitmap3);
        file_maps.put(getResources().getString(R.string.home_text_info_1), bitmap1);
*/

        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            mDemoSlider.addSlider(textSliderView);
        }
        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.FlipHorizontal);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setCurrentPosition(1);
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);
        /*For update VCS*/
        return view;
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast toast = Toast.makeText(getActivity(), slider.getBundle().get("extra") + "", Toast.LENGTH_SHORT);

        toast.show();
    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }
}
