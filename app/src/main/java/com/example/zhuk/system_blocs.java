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

import com.example.zhuk.database.System_blocs;
import com.example.zhuk.products.BlocsProducts;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class system_blocs extends AppCompatActivity {
    private ListView ListView;
    private ArrayAdapter<String> adapter;
    private List<String> listData;
    private List<System_blocs> listTemp;
    private DatabaseReference mDataBase;
    private String USER_KEY = "System_blocks";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system_blocs);
        init();
        getDataFromDB();
        setOnClickItem();
    }

    private void init() {
        ListView = findViewById(R.id.ListView);
        listData = new ArrayList<>();
        listTemp = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        ListView.setAdapter(adapter);
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
                    System_blocs bloks = ds.getValue(System_blocs.class);
                    assert bloks != null;
                    listData.add(bloks.name);
                    listTemp.add(bloks);
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
        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                System_blocs bloks = listTemp.get(position);
                Intent i = new Intent(system_blocs.this, BlocsProducts.class);
                i.putExtra(Constant.SYSTEM_BLOCS_NAME, bloks.name);
                i.putExtra(Constant.SYSTEM_BLOCS_PRICE, bloks.price);
                i.putExtra(Constant.SYSTEM_BLOCS_PHOTO1, bloks.photo1);
                i.putExtra(Constant.SYSTEM_BLOCS_PHOTO2, bloks.photo2);
                startActivity(i);
            }
        });
    }

    public void backto7(View view) {
        Intent i = new Intent(system_blocs.this, Catalog.class);
        startActivity(i);
    }
}
