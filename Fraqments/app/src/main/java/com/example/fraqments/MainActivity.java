package com.example.fraqments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    Button Button1, Button2, Button3;
    LinearLayout myLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button1 = findViewById(R.id.btn_1);
        Button2 = findViewById(R.id.btn_2);
        Button3 = findViewById(R.id.btn_3);
        myLayout = findViewById(R.id.linearLayout);

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment1 fragment1 = new Fragment1();
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearLayout, fragment1);
                transaction.commit();
            }
        });

        Button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment2 fragment2 = new Fragment2();
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.linearLayout, fragment2);
                transaction.commit();
            }
        });
    }
}