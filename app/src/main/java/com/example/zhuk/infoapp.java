package com.example.zhuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class infoapp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoapp);
    }

    public void onmain(View view) {
        Intent i;
        i = new Intent(infoapp.this, MainActivity.class);
        startActivity(i);
    }
}