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

import com.example.zhuk.database.Smart_watch;
import com.example.zhuk.products.WatchProducts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class smartwatch extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<Smart_watch> listTemp;
    private DatabaseReference mDataBase;
    private String USER_KEY = "Smart_watch";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smartwatch);
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
                    Smart_watch smart_watch = ds.getValue(Smart_watch.class);
                    assert smart_watch != null;
                    listData.add(smart_watch.name);
                    listTemp.add(smart_watch);
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
                Smart_watch smart_watch = listTemp.get(position);
                Intent i = new Intent(smartwatch.this, WatchProducts.class);
                i.putExtra(Constant.SMART_WATCH_NAME, smart_watch.name);
                i.putExtra(Constant.SMART_WATCH_PRICE, smart_watch.price);
                i.putExtra(Constant.SMART_WATCH_PHOTO1, smart_watch.photo1);
                i.putExtra(Constant.SMART_WATCH_PHOTO2, smart_watch.photo2);
                startActivity(i);
            }
        });
    }

    public void backto6(View view) {
        Intent i = new Intent(smartwatch.this, Catalog.class);
        startActivity(i);
    }
}
