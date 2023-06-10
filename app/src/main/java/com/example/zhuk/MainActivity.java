package com.example.zhuk;
import static com.google.android.material.color.utilities.MaterialDynamicColors.error;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.zhuk.products.BlocProducts;
import com.example.zhuk.products.BlocsProducts;
import com.example.zhuk.products.GamepadProducts;
import com.example.zhuk.products.GamepadsProducts;
import com.example.zhuk.products.KlavaMousProducts;
import com.example.zhuk.products.KlavaMouseProducts;
import com.google.firebase.auth.FirebaseAuth;
import android.widget.ImageButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    private EditText edName, edSecName, edEmail;
    private DatabaseReference mDataBase;
    private Button catal, exit;
    private ImageButton vest, vest1, vest2, vest3;
    private String USER_KEY = "User";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        vest = findViewById(R.id.vest);
        vest1 = findViewById(R.id.vest1);
        vest2 = findViewById(R.id.vest2);
        vest3 = findViewById(R.id.vest3);
        DatabaseReference blok = FirebaseDatabase.getInstance().getReference("System_blocks");
        blok.orderByChild("id_system_blocs").equalTo("htedhHRE156FAE").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datas : snapshot.getChildren()) {
                    String str0 = datas.child("id_system_blocs").getValue().toString();
                    String str1 = datas.child("name").getValue().toString();
                    String str2 = datas.child("photo1").getValue().toString();
                    String str3 = datas.child("photo2").getValue().toString();
                    String str4 = datas.child("price").getValue().toString();
                    vest.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, BlocProducts.class);
                            i.putExtra(Constant.SYSTEM_BLOCS_NAME, str1);
                            i.putExtra(Constant.SYSTEM_BLOCS_PRICE, str4);
                            i.putExtra(Constant.SYSTEM_BLOCS_PHOTO1, str2);
                            i.putExtra(Constant.SYSTEM_BLOCS_PHOTO2, str3);
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        DatabaseReference mous = FirebaseDatabase.getInstance().getReference("Keyboards_and_mice");
        mous.orderByChild("id_keyboards_and_mice").equalTo("klfgdklgHYP").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datas : snapshot.getChildren()) {
                    String str0 = datas.child("id_keyboards_and_mice").getValue().toString();
                    String str1 = datas.child("name").getValue().toString();
                    String str2 = datas.child("photo1").getValue().toString();
                    String str3 = datas.child("photo2").getValue().toString();
                    String str4 = datas.child("price").getValue().toString();
                    vest1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, KlavaMousProducts.class);
                            i.putExtra(Constant.KEYBOARDS_AND_MICE_NAME, str1);
                            i.putExtra(Constant.KEYBOARDS_AND_MICE_PRICE, str4);
                            i.putExtra(Constant.KEYBOARDS_AND_MICE_PHOTO1, str2);
                            i.putExtra(Constant.KEYBOARDS_AND_MICE_PHOTO2, str3);
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        DatabaseReference game1 = FirebaseDatabase.getInstance().getReference("Gamepads");
        game1.orderByChild("id_gamepads").equalTo("jgfdjkgdf23").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datas : snapshot.getChildren()) {
                    String str0 = datas.child("id_gamepads").getValue().toString();
                    String str1 = datas.child("name").getValue().toString();
                    String str2 = datas.child("photo1").getValue().toString();
                    String str3 = datas.child("photo2").getValue().toString();
                    String str4 = datas.child("price").getValue().toString();
                    vest2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, GamepadProducts.class);
                            i.putExtra(Constant.GAMEPADS_NAME, str1);
                            i.putExtra(Constant.GAMEPADS_PRICE, str4);
                            i.putExtra(Constant.GAMEPADS_PHOTO1, str2);
                            i.putExtra(Constant.GAMEPADS_PHOTO2, str3);
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        DatabaseReference game2 = FirebaseDatabase.getInstance().getReference("Gamepads");
        game2.orderByChild("id_gamepads").equalTo("jhfdkkksd").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot datas : snapshot.getChildren()) {
                    String str0 = datas.child("id_gamepads").getValue().toString();
                    String str1 = datas.child("name").getValue().toString();
                    String str2 = datas.child("photo1").getValue().toString();
                    String str3 = datas.child("photo2").getValue().toString();
                    String str4 = datas.child("price").getValue().toString();
                    vest3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(MainActivity.this, GamepadProducts.class);
                            i.putExtra(Constant.GAMEPADS_NAME, str1);
                            i.putExtra(Constant.GAMEPADS_PRICE, str4);
                            i.putExtra(Constant.GAMEPADS_PHOTO1, str2);
                            i.putExtra(Constant.GAMEPADS_PHOTO2, str3);
                            startActivity(i);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void init() {
        edName = findViewById(R.id.edName);
        edSecName = findViewById(R.id.edSecName);
        edEmail = findViewById(R.id.edEmail);
        catal = findViewById(R.id.catal);
        mDataBase = FirebaseDatabase.getInstance().getReference(USER_KEY);
        vest = findViewById(R.id.vest);
        vest1 = findViewById(R.id.vest1);
        vest2 = findViewById(R.id.vest2);
        vest3 = findViewById(R.id.vest3);
        exit = findViewById(R.id.exit);
        mAuth = FirebaseAuth.getInstance();
    }


    public void savee(View view) {
        String id = mDataBase.getKey();
        String name = edName.getText().toString();
        String sec_name = edSecName.getText().toString();
        String email = edEmail.getText().toString();
        User newUser = new User(id, name, sec_name, email);
        if (!TextUtils.isEmpty(name)) {
            mDataBase.push().setValue(newUser);
            Toast.makeText(this, "Сохранено", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Пустое поле", Toast.LENGTH_SHORT).show();
        }
    }

    private void getIntentMain() {
        Intent i = getIntent();
        if (i != null) {

            Picasso.get().load(i.getStringExtra(Constant.GAMEPADS_PHOTO1)).into(vest3);
            Picasso.get().load(i.getStringExtra(Constant.GAMEPADS_PHOTO2)).into(vest2);
            Picasso.get().load(i.getStringExtra(Constant.KEYBOARDS_AND_MICE_PHOTO1)).into(vest1);
            Picasso.get().load(i.getStringExtra(Constant.SYSTEM_BLOCS_PHOTO1)).into(vest);
        }
    }

    public void cat(View view) {
        Intent i;
        i = new Intent(MainActivity.this, Catalog.class);
        startActivity(i);
    }

    public void kkkk(View view) {
        Intent i;
        i = new Intent(MainActivity.this, Korzina.class);
        startActivity(i);
    }

    public void info(View view) {
        Intent i;
        i = new Intent(MainActivity.this, infoapp.class);
        startActivity(i);
    }

    public void signouut(View view) {
        FirebaseAuth.getInstance().signOut();
        notSigned();
    }

    private void notSigned() {
        Intent i;
        i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);
    }
}

