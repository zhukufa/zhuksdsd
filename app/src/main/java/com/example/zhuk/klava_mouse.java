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

import com.example.zhuk.database.Keyboards_and_mice;
import com.example.zhuk.products.KlavaMouseProducts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class klava_mouse extends AppCompatActivity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<Keyboards_and_mice> listTemp;
    private DatabaseReference mDataBase;
    private String USER_KEY = "Keyboards_and_mice";

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
                    Keyboards_and_mice keyboards_and_mice = ds.getValue(Keyboards_and_mice.class);
                    assert keyboards_and_mice != null;
                    listData.add(keyboards_and_mice.name);
                    listTemp.add(keyboards_and_mice);
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
                Keyboards_and_mice keyboards_and_mice = listTemp.get(position);
                Intent i = new Intent(klava_mouse.this, KlavaMouseProducts.class);
                i.putExtra(Constant.KEYBOARDS_AND_MICE_NAME, keyboards_and_mice.name);
                i.putExtra(Constant.KEYBOARDS_AND_MICE_PRICE, keyboards_and_mice.price);
                i.putExtra(Constant.KEYBOARDS_AND_MICE_PHOTO1, keyboards_and_mice.photo1);
                i.putExtra(Constant.KEYBOARDS_AND_MICE_PHOTO2, keyboards_and_mice.photo2);
                startActivity(i);
            }
        });
    }

    public void backto4(View view) {
        Intent i = new Intent(klava_mouse.this, Catalog.class);
        startActivity(i);
    }
}
