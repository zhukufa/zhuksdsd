package com.example.zhuk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.zhuk.database.Bluetooth_speakers;
import com.example.zhuk.database.Monitors;
import com.example.zhuk.products.KolonkiProducts;
import com.example.zhuk.products.MonitorProducts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class kolonki extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<Bluetooth_speakers> listTemp;
    private DatabaseReference mDataBase;
    private String USER_KEY = "Bluetooth_speakers";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kolonki);
        init();
        getDataFromDB();
        setOnClickItem();
    }

    private void init() {
        listView = findViewById(R.id.ListView);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        listView.setAdapter(adapter);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
    }

    private void getDataFromDB() {
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (listData.size() > 0) listData.clear();
                if (listTemp.size() > 0) listTemp.clear();
                for (DataSnapshot ds : dataSnapshot.getChildren())
                {
                    Bluetooth_speakers bluetooth_speakers = ds.getValue(Bluetooth_speakers.class);
                    assert bluetooth_speakers != null;
                    listData.add(bluetooth_speakers.name);
                    listTemp.add(bluetooth_speakers);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };
        mDataBase.addValueEventListener(vListener);
    }

    private void setOnClickItem()
    {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Bluetooth_speakers bluetooth_speakers = listTemp.get(position);
                Intent i = new Intent(kolonki.this, KolonkiProducts.class);
                i.putExtra(Constant.BLUETOOTH_SPEAKERS_NAME, bluetooth_speakers.name);
                i.putExtra(Constant.BLUETOOTH_SPEAKERS_PRICE, bluetooth_speakers.price);
                i.putExtra(Constant.BLUETOOTH_SPEAKERS_PHOTO1, bluetooth_speakers.photo1);
                i.putExtra(Constant.BLUETOOTH_SPEAKERS_PHOTO2, bluetooth_speakers.photo2);
                startActivity(i);
            }
        });
    }

    public void backto5(View view) {
        Intent i = new Intent(kolonki.this, Catalog.class);
        startActivity(i);
    }
}
