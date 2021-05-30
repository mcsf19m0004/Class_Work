package com.example.gomart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gomart.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText  gmail, password;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // getSupportActionBar().hide();
        auth= FirebaseAuth.getInstance();

        gmail=findViewById(R.id.gmail);
        password=findViewById(R.id.password);
    }

    public void signin(View view) {
        String userEmail=gmail.getText().toString();
        String userPassword=password.getText().toString();

        if(TextUtils.isEmpty(userEmail))
        {
            Toast.makeText(this, "Enter Gmail Address", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword))
        {
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length()<8)
        {
            Toast.makeText(this, "Passowrd Lenght too short enter minimum 8 digits", Toast.LENGTH_SHORT).show();
            return;
        }
        auth.signInWithEmailAndPassword(userEmail,userPassword)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Error: "+task.getException(), Toast.LENGTH_SHORT).show();

                }

            }
        });

    }

    public void signup(View view) {
        Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
        startActivity(intent);
    }
}