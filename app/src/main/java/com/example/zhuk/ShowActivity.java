package com.example.zhuk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    private TextView tvName, tvSecName, tvPrice;
    private ImageView imBD;
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        /*/init();
        getIntentMain();/*/
    }
    /*/private void init()
    {
        imBD = findViewById(R.id.imBD);
        tvName = findViewById(R.id.tvName);
        tvPrice = findViewById(R.id.tvPrice);
    }
    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null)
        {

            tvName.setText(i.getStringExtra(Constant.USER_NAME));
            tvSecName.setText(i.getStringExtra(Constant.USER_SEC_NAME));
            tvPrice.setText(i.getStringExtra(Constant.USER_PRICE));
        }
    }
    public void back1 (View view)
    {
        Intent i = new Intent(ShowActivity.this, MainActivity.class);
        startActivity(i);
    }/*/
}
