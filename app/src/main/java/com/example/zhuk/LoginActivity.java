package com.example.zhuk;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;

public class LoginActivity extends AppCompatActivity {
    private EditText ed_email, ed_pass;
    private FirebaseAuth mAuth;
    private Button bStart;
    private Button bSignIn;
    private Button bSignUp, bSignOut;
    private TextView tvUsername;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cUser = mAuth.getCurrentUser();
        if (cUser != null) {
            showSigned();
            String userName = "Вы вошли как : " + cUser.getEmail();
            tvUsername.setText(userName);

            Toast.makeText(this, "Вы уже вошли!", Toast.LENGTH_SHORT);
        } else {
            notSigned();
            Toast.makeText(this, "Пользователь ещё не зарегистрирован", Toast.LENGTH_SHORT);
        }
    }

    private void init() {
        ed_email = findViewById(R.id.ed_email);
        ed_pass = findViewById(R.id.ed_pass);
        mAuth = FirebaseAuth.getInstance();
        tvUsername = findViewById(R.id.tvUsername);
        bStart = findViewById(R.id.bStart);
        bSignIn = findViewById(R.id.bSignIn);
        bSignUp = findViewById(R.id.bSignUp);
        bSignOut = findViewById(R.id.bSignOut);
    }

    public void signup(View view) {
        if (!TextUtils.isEmpty(ed_email.getText().toString()) && !TextUtils.isEmpty(ed_pass.getText().toString()))
            mAuth.createUserWithEmailAndPassword(ed_email.getText().toString(), ed_pass.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        showSigned();
                        sendEmailVer();
                        String uploadEmail = ed_email.getText().toString();
                        uploadUser(user, uploadEmail);
                        Toast.makeText(getApplicationContext(), "Регистрация успешно выполнена!", Toast.LENGTH_SHORT).show();
                    } else {
                        notSigned();
                        Toast.makeText(getApplicationContext(), "Пользователь уже зарегистрирован!", Toast.LENGTH_SHORT).show();
                    }

                }

            });
        else {
            Toast.makeText(getApplicationContext(), "Введите логин и пароль!", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadUser(FirebaseUser user, String uploadEmail){

        User newUser = new User();

        newUser.setId(user.getUid());
        newUser.setEmail(uploadEmail);

        FirebaseFirestore.getInstance().collection("Users").document(user.getUid())
                .set(newUser)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, user.getUid(),
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        } else {
                            Toast.makeText(LoginActivity.this, "Ошибка - " + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signin(View view) {
        if (!TextUtils.isEmpty(ed_email.getText().toString()) && !TextUtils.isEmpty(ed_pass.getText().toString()))
            mAuth.signInWithEmailAndPassword(ed_email.getText().toString(), ed_pass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        showSigned();
                        Toast.makeText(getApplicationContext(), "Пользователь вошел успешно", Toast.LENGTH_SHORT).show();
                    } else {
                        notSigned();
                        Toast.makeText(getApplicationContext(), "Вы не зарегистрированы!", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    }

    public void signout(View view) {
        FirebaseAuth.getInstance().signOut();
        notSigned();
    }

    private void showSigned() {
        FirebaseUser user = mAuth.getCurrentUser();

        assert user != null;
        if (user.isEmailVerified()) {
            String userName = "Вы уже вошли как : " + user.getEmail();
            tvUsername.setText(userName);
            bStart.setVisibility(View.VISIBLE);
            tvUsername.setVisibility(View.VISIBLE);
            ed_email.setVisibility(View.GONE);
            ed_pass.setVisibility(View.GONE);
            bSignUp.setVisibility(View.GONE);
            bSignIn.setVisibility(View.GONE);
            bSignOut.setVisibility(View.VISIBLE);
        } else {
            Toast.makeText(getApplicationContext(), "На вашу электронную пришло сообщение с подтвеждением адреса.", Toast.LENGTH_SHORT).show();
        }
    }
    private void notSigned()
    {
        bStart.setVisibility(View.GONE);
        tvUsername.setVisibility(View.GONE);
        ed_email.setVisibility(View.VISIBLE);
        ed_pass.setVisibility(View.VISIBLE);
        bSignUp.setVisibility(View.VISIBLE);
        bSignIn.setVisibility(View.VISIBLE);
        bSignOut.setVisibility(View.GONE);
    }
    public void butstart (View view)
    {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
    }
    private void sendEmailVer()
    {
        FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful() )
                {
                    Toast.makeText(getApplicationContext(), "На вашу электронную пришло сообщение с подтвеждением адреса.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Не удалось найти почту.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}