package com.example.zhuk.products;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhuk.Constant;
import com.example.zhuk.R;
import com.squareup.picasso.Picasso;

public class WatchProducts extends AppCompatActivity {
    private TextView price1, name1;
    private ImageView vest, vest1;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_products);

        init();
        getIntentMain();
    }

    private void init() {
        price1 = findViewById(R.id.price1);
        name1 = findViewById(R.id.name1);
        vest = findViewById(R.id.vest);
        vest1 = findViewById(R.id.vest1);
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {

            name1.setText(i.getStringExtra(Constant.SMART_WATCH_NAME));
            price1.setText(i.getStringExtra(Constant.SMART_WATCH_PRICE));
            Picasso.get().load(i.getStringExtra(Constant.SMART_WATCH_PHOTO1)).into(vest);
            Picasso.get().load(i.getStringExtra(Constant.SMART_WATCH_PHOTO2)).into(vest1);
        }
    }
}