package com.example.zhuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.zhuk.database.Bluetooth_speakers;
import com.example.zhuk.database.Gamepads;
import com.example.zhuk.database.Headset_and_microphones;
import com.example.zhuk.database.Keyboards_and_mice;
import com.example.zhuk.database.Monitors;
import com.example.zhuk.database.Smart_watch;
import com.example.zhuk.database.System_blocs;

public class Catalog extends AppCompatActivity {
    private Button monitor,blok,micro,klava,gamepad,kolonka,kovrik,back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        monitor = findViewById(R.id.monitor);
        blok = findViewById(R.id.blok);
        micro = findViewById(R.id.micro);
        klava = findViewById(R.id.klava);
        gamepad = findViewById(R.id.gamepad);
        kolonka = findViewById(R.id.kolonka);
        kovrik = findViewById(R.id.kovrik);
        back = findViewById(R.id.back);

    }
    public void backk (View view)
    {
        Intent i = new Intent(Catalog.this, MainActivity.class);
        startActivity(i);
    }

    public void monik(View view) {
        Intent i = new Intent(Catalog.this, moniki.class);
        startActivity(i);
    }

    public void systbloki(View view) {
        Intent i = new Intent(Catalog.this, system_blocs.class);
        startActivity(i);
    }

    public void mikro(View view) {
        Intent i = new Intent(Catalog.this, mikrophones.class);
        startActivity(i);
    }

    public void klavaa(View view) {
        Intent i = new Intent(Catalog.this, klava_mouse.class);
        startActivity(i);
    }

    public void gamepadi(View view) {
        Intent i = new Intent(Catalog.this, gamepadi.class);
        startActivity(i);
    }

    public void kolonki(View view) {
        Intent i = new Intent(Catalog.this, kolonki.class);
        startActivity(i);
    }

    public void watches(View view) {
        Intent i = new Intent(Catalog.this, smartwatch.class);
        startActivity(i);
    }
}