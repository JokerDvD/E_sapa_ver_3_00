package com.example.admin.e_sapa_ver_3_00;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import java.util.Timer;
import java.util.TimerTask;

public class Splash_Screen extends AppCompatActivity {

    private long Delay = 2000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash__screen);


        Timer RunSplash = new Timer();
        TimerTask ShowSplash = new TimerTask() {
            @Override
            public void run() {
                finish();
                Intent myIntent = new Intent(Splash_Screen.this,
                        Fragment_activity.class);
                startActivity(myIntent);
            }
        };
        RunSplash.schedule(ShowSplash, Delay);
    }
}