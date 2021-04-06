package com.example.practic1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button myButton;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myButton= findViewById(R.id.ButtonCode);
        textView= findViewById(R.id.textCode);
    }

    public void someOneIisThere(View view)
    {
        textView.setText("some one new");
    }

}