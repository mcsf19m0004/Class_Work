package com.example.gomart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class RegistrationActivity extends AppCompatActivity {

    EditText name, gmail, password;
    private FirebaseAuth auth;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //getSupportActionBar().hide();
        auth= FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!=null)
        {
            Intent intent=new Intent(RegistrationActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        name=findViewById(R.id.name);
        gmail=findViewById(R.id.gmail);
        password=findViewById(R.id.password);
        sharedPreferences=getSharedPreferences("onBoardingScreen" , MODE_PRIVATE);
        boolean isFirstTime= sharedPreferences.getBoolean("firstTime",true);
        if(isFirstTime)
        {
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();
            Intent intent=new Intent(RegistrationActivity.this,onBoardingActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signup(View view) {
        String userName=name.getText().toString();
        String userEmail=gmail.getText().toString();
        String userPassword=password.getText().toString();
        if(TextUtils.isEmpty(userName))
        {
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
            return;
        }
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

        auth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful())
                        {
                            Toast.makeText(RegistrationActivity.this, "Successfully Register", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(RegistrationActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "Registeration Failed"+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }

    public void signin(View view) {
        Intent intent=new Intent(RegistrationActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}