package com.ishaq.AsusIshaq2125250060.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.VideoView;

import com.ishaq.asusishaq2125250060.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final VideoView videoView = findViewById(R.id.vv_splash_screen);
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/raw/intro"));
        videoView.start();



        // Handler untuk mengatur berapa lama delay splashscreen
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            }
        }, 3000);
    }

}