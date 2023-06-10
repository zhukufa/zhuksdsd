package com.example.zhuk.products;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhuk.Constant;
import com.example.zhuk.MainActivity;
import com.example.zhuk.R;
import com.example.zhuk.gamepadi;
import com.squareup.picasso.Picasso;

public class GamepadsProducts extends AppCompatActivity {
    private TextView price1, name1;
    private ImageView vest, vest1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamepads_products);
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

            name1.setText(i.getStringExtra(Constant.GAMEPADS_NAME));
            price1.setText(i.getStringExtra(Constant.GAMEPADS_PRICE));
            Picasso.get().load(i.getStringExtra(Constant.GAMEPADS_PHOTO1)).into(vest);
            Picasso.get().load(i.getStringExtra(Constant.GAMEPADS_PHOTO2)).into(vest1);
        }
    }

    public void back1111(View view) {
        Intent i = new Intent(GamepadsProducts.this, gamepadi.class);
        startActivity(i);
    }
}