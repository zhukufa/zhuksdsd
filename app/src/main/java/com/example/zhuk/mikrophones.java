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

import com.example.zhuk.database.Headset_and_microphones;
import com.example.zhuk.products.MikrophoneProducts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class mikrophones extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<Headset_and_microphones> listTemp;
    private DatabaseReference mDataBase;
    private String USER_KEY = "Headset_and_microphones";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_klava_mouse);
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
                    Headset_and_microphones microphones = ds.getValue(Headset_and_microphones.class);
                    assert microphones != null;
                    listData.add(microphones.name);
                    listTemp.add(microphones);
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
                Headset_and_microphones microphones = listTemp.get(position);
                Intent i = new Intent(mikrophones.this, MikrophoneProducts.class);
                i.putExtra(Constant.HEADSET_AND_MICROPHONES_NAME, microphones.name);
                i.putExtra(Constant.HEADSET_AND_MICROPHONES_PRICE, microphones.price);
                i.putExtra(Constant.HEADSET_AND_MICROPHONES_PHOTO1, microphones.photo1);
                i.putExtra(Constant.HEADSET_AND_MICROPHONES_PHOTO2, microphones.photo2);
                startActivity(i);
            }
        });
    }

    public void backto3(View view) {
        Intent i = new Intent(mikrophones.this, Catalog.class);
        startActivity(i);
    }
}
