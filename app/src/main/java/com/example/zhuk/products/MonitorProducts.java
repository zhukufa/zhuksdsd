package com.example.zhuk.products;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuk.Constant;
import com.example.zhuk.R;
import com.example.zhuk.database.Monitors;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class MonitorProducts extends AppCompatActivity {
    private TextView price1, name1;
    private ImageView vest, vest1;
    //private Button add;
    private ArrayList<String> values;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monitor_products);
        values = new ArrayList<>();

        init();
        getIntentMain();
    }

    private void init() {
        price1 = findViewById(R.id.price1);
        name1 = findViewById(R.id.name1);
        vest = findViewById(R.id.vest);
        vest1 = findViewById(R.id.vest1);
       // add = findViewById(R.id.add);
    }
    /*/add.setOnClickListener(new View.OnClickListener() {
        @Override
                public void onClick (View v) {
            add();

        }
    });
    addItem.setOnClickListener(new View.OnClickListener() {
        @Override
                public void onClick(View v) {
                if (totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                }
        }
    });
    private void addedToCart() {
        String SaveCurrentDate, SaveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd,yyyy");
        SaveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        SaveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",products.moniki.listView.getname());
    }
    public void addToCart(View view){
        int item_name = getIntent().getIntExtra("name",0);
        Monitors.items_name.add(item_name);
        Toast.makeText(this,"Добавлено!", Toast.LENGTH_LONG).show();
    }
/*/
    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {

            name1.setText(i.getStringExtra(Constant.MONITORS_NAME));
            price1.setText(i.getStringExtra(Constant.MONITORS_PRICE));
            Picasso.get().load(i.getStringExtra(Constant.MONITORS_PHOTO1)).into(vest);
            Picasso.get().load(i.getStringExtra(Constant.MONITORS_PHOTO2)).into(vest1);
            values.add(i.getStringExtra(Constant.MONITORS_NAME));
            i.putExtra(String.valueOf(Constant.KORZINA), values);
        }
    }
}