package com.example.p2login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private TextView info;
    private Button login;
    private int counter=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name=(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.etPassword);
        info =(TextView)findViewById(R.id.tvInfo);
        login=(Button)findViewById(R.id.buttonLogin);
        info.setText("number of attemps reaming : 5");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(name.getText().toString(),password.getText().toString());
            }
        });

    }

    private  void validate(String userName,String userPassword)
    {
        if((userName=="Admin") && (userPassword=="1234"))
        {
            Intent intent=new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
        else {
            counter--;
            info.setText("number of attemps remaing : "+ String.valueOf(counter));
            if(counter==0)
            {
                login.setEnabled(false);
            }
        }
    }



}