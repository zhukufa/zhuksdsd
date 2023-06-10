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

import com.example.zhuk.database.Gamepads;
import com.example.zhuk.products.GamepadsProducts;
import com.example.zhuk.products.KlavaMousProducts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class gamepadi extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<Gamepads> listTemp;
    private DatabaseReference mDataBase;
    private String USER_KEY = "Gamepads";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamepads);
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
                    Gamepads gamepads = ds.getValue(Gamepads.class);
                    assert gamepads != null;
                    listData.add(gamepads.name);
                    listTemp.add(gamepads);
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
                Gamepads gamepads = listTemp.get(position);
                Intent i = new Intent(gamepadi.this, GamepadsProducts.class);
                i.putExtra(Constant.GAMEPADS_NAME, gamepads.name);
                i.putExtra(Constant.GAMEPADS_PRICE, gamepads.price);
                i.putExtra(Constant.GAMEPADS_PHOTO1, gamepads.photo1);
                i.putExtra(Constant.GAMEPADS_PHOTO2, gamepads.photo2);
                startActivity(i);
            }
        });
    }

    public void backto(View view) {
        Intent i = new Intent(gamepadi.this, Catalog.class);
        startActivity(i);
    }
}
