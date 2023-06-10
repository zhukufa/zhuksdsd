package com.example.zhuk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Korzina extends AppCompatActivity {
    private ListView listView;
    private ArrayList<String> values;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korzina);
        listView = findViewById(R.id.listView);
        values = new ArrayList<>();
        Intent i = getIntent();
        values.add(i.getStringExtra(String.valueOf(Constant.KORZINA)));
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, values);
        listView.setAdapter(adapter);
    }
}