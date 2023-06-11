package com.example.zhuk.products;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuk.CartItem;
import com.example.zhuk.Constant;
import com.example.zhuk.LoginActivity;
import com.example.zhuk.MainActivity;
import com.example.zhuk.R;
import com.example.zhuk.User;
import com.example.zhuk.database.Monitors;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
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

    public void addToCart(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser cUser = mAuth.getCurrentUser();
        Intent i = getIntent();
        if (i == null) {
            return;
        }
        CartItem cartItem = new CartItem();
        cartItem.setName(i.getStringExtra(Constant.MONITORS_NAME));
        cartItem.setPhotourl(i.getStringExtra(Constant.MONITORS_PHOTO1));
        cartItem.setPrice(i.getStringExtra(Constant.MONITORS_PRICE));
        cartItem.setUserid(cUser.getUid());
        Toast.makeText(MonitorProducts.this, cUser.getUid(),
                Toast.LENGTH_SHORT).show();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference cartitemsCollection = db.collection("CartItems");
        DocumentReference newCartItemsRef = cartitemsCollection.document();
        newCartItemsRef.set(cartItem).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    String cartitemid = newCartItemsRef.getId();
                    cartItem.setId(cartitemid);
                    newCartItemsRef.set(cartItem).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(MonitorProducts.this, "Успешно",
                                        Toast.LENGTH_SHORT).show();
                                //addCartIdToUser(db, cartitemid);
                            } else {
                                Toast.makeText(MonitorProducts.this, "Ошибка - " + task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(MonitorProducts.this, "Ошибка - " + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    /*private void addCartIdToUser(FirebaseFirestore db, String cartitemid) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser cUser = mAuth.getCurrentUser();
        DocumentReference userRef = db.collection("Users").document(cUser.getUid());

        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        List<String> existingArray = (List<String>) document.get("cart");
                        if (existingArray == null) {
                            existingArray = new ArrayList<>();
                        }
                        existingArray.add(cartitemid);
                        userRef.update("cart", existingArray)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(MonitorProducts.this, "Добавлено в профиль",
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(MonitorProducts.this, "Ошибка - " + task.getException().getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    } else {
                        Toast.makeText(MonitorProducts.this, "Документ не найден",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MonitorProducts.this, "Ошибка - " + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("Firestore", "Ошибка: " + e.getMessage());
            }
        });
    }*/
}