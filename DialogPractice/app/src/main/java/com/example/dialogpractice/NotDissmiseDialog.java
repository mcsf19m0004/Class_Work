package com.example.dialogpractice;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotDissmiseDialog extends AppCompatActivity {

    Button notDissmis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_dissmise_dialog);

        notDissmis=findViewById(R.id.notDissmisDailogbutton);

        AlertDialog dialog=new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("ok",null)
                .setNegativeButton("Cancle",null)
                .show();

        Button positiveB=dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NotDissmiseDialog.this,"Not Closing",Toast.LENGTH_SHORT).show();
                //dialog.dismiss();
            }
        });
    }


    public void dismiBack(View view) {
        Intent intent=new Intent(NotDissmiseDialog.this,MainActivity.class);
        startActivity(intent);
    }
}