package com.example.dialogpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CustomeDialog extends AppCompatActivity {
private TextView userName, password;
private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custome_dialog);

        userName=findViewById(R.id.customename);
        password=findViewById(R.id.password);
        button=findViewById(R.id.cusButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }
    public void openDialog()
    {

    }
    public void customBack(View view) {
        Intent intent=new Intent(CustomeDialog.this,MainActivity.class);
        startActivity(intent);
    }
}