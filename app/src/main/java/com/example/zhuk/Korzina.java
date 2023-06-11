package com.example.zhuk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhuk.products.MonitorProducts;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Korzina extends AppCompatActivity {

    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_korzina);
        Intent i = getIntent();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference cartItemsRef = db.collection("CartItems");

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser cUser = mAuth.getCurrentUser();

        String userId = cUser.getUid();
        Query query = cartItemsRef.whereEqualTo("userid", userId);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    List<CartItem> cartItems = new ArrayList<>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String name = document.getString("name");
                        String price = document.getString("price");
                        String photourl = document.getString("photourl");
                        String id = document.getString("id");
                        String userid = document.getString("userid");

                        CartItem cartItem = new CartItem(id, name, photourl, price, userid);
                        cartItems.add(cartItem);
                    }

                    adapter = new CartAdapter(cartItems, new CartAdapter.CartItemListener() {
                        @Override
                        public void onCartItemDeleted(int position) {
                            // Обработка удаления товара из корзины
                            cartItems.remove(position);
                            adapter.notifyItemRemoved(position);
                            adapter.notifyItemRangeChanged(position, cartItems.size());
                        }

                        @Override
                        public void deleteCartItemFromFirestore(String cartItemId) {
                            // Удалите элемент из базы данных Firebase Firestore
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            DocumentReference itemRef = db.collection("CartItems").document(cartItemId);
                            itemRef.delete()
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(Korzina.this, "Элемент успешно удален из базы данных",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Korzina.this, "Ошибка - " + task.getException().getMessage(),
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    });
                    RecyclerView recyclerView = findViewById(R.id.cartlist);
                    recyclerView.setAdapter(adapter);
                    recyclerView.setLayoutManager(new LinearLayoutManager(Korzina.this));

                    double totalCost = 0.0;
                    for (CartItem cartItem : cartItems) {
                        String priceString = cartItem.getPrice();
                        priceString = priceString.replaceAll("\\P{Print}", "").replaceAll("\u00A0", "");
                        priceString = priceString.replaceAll("[^\\d.]", "");
                        double itemPrice = Double.parseDouble(priceString);

                        totalCost += itemPrice;
                    }
                    TextView allcostkorzina;
                    allcostkorzina = findViewById(R.id.allcostkorzina);
                    allcostkorzina.setText("Общая стоимость: " + totalCost);
                } else {
                    Toast.makeText(Korzina.this, "Ошибка - " + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}