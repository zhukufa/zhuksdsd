package com.example.zhuk.products;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zhuk.Constant;
import com.example.zhuk.MainActivity;
import com.example.zhuk.R;
import com.squareup.picasso.Picasso;

public class GamepadProducts extends AppCompatActivity {
    private TextView price1, name1;
    private ImageView vest, vest1;
    private Button button8;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamepad_products);
        init();
        getIntentMain();
    }

    private void init() {
        price1 = findViewById(R.id.price1);
        name1 = findViewById(R.id.name1);
        vest = findViewById(R.id.vest);
        vest1 = findViewById(R.id.vest1);
        button8 = findViewById(R.id.button8);
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

    public void back222(View view) {
        Intent i = new Intent(GamepadProducts.this, MainActivity.class);
        startActivity(i);
    }
}