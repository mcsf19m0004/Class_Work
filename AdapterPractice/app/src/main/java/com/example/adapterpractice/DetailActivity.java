package com.example.adapterpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        Log.d("checking",intent.getStringExtra("Friend"));
        TextView textView=findViewById(R.id.textViewDetail);
        //Intent intent=getIntent();
        textView.setText(intent.getStringExtra("Friend"));
    }
}