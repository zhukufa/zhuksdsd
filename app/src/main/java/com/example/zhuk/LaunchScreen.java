package com.example.zhuk;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class LaunchScreen extends AppCompatActivity {
    private final int LANCHSCREEN_DISPLAY_LENGTH = 2550;
    ImageView zhuk;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch_screen);
        zhuk = findViewById(R.id.zhuk);

        final Animation animationRotate = AnimationUtils.loadAnimation(
                this, R.anim.rotate);
        zhuk.startAnimation(animationRotate);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                SharedPreferences sp = getSharedPreferences("hasVisted",
                        Context.MODE_PRIVATE);
                boolean hasVisited = sp.getBoolean("hasVisited",false);

                if (!hasVisited){
                    SharedPreferences.Editor e = sp.edit();
                    e.putBoolean("hasVisited",true);
                    e.commit();
                    Intent mainIntent = new Intent(LaunchScreen.this, LoginActivity.class);
                    LaunchScreen.this.startActivity(mainIntent);
                    LaunchScreen.this.finish();
                } else {
                    Intent mainIntent = new Intent(LaunchScreen.this, LoginActivity.class);
                    LaunchScreen.this.startActivity(mainIntent);
                    LaunchScreen.this.finish();
                }
            }
        }, LANCHSCREEN_DISPLAY_LENGTH);
    }
}