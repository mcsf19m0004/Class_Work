package com.example.dialogpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SimpleDialog extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_dialog);
        button=(Button)findViewById(R.id.sDialog);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //open dialog
                openDialog();

            }
        });



    }
    public void openDialog()
    {
        //create instance od dialog
        simpleDailog simplDailog=new simpleDailog();
        simplDailog.show(getSupportFragmentManager(),"exsnple dialog");
    }

    public void sBack(View view) {
        Intent intent=new Intent(SimpleDialog.this,MainActivity.class);
        startActivity(intent);
    }
}