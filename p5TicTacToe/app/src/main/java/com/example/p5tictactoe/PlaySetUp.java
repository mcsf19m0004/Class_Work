package com.example.p5tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class PlaySetUp extends AppCompatActivity {

    private EditText player1;
    private EditText player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_set_up);
        player1=findViewById(R.id.player1name);
        player2=findViewById(R.id.player2name);
    }

    public void submitButtonClick(View view) {
        String plaer1name=player1.getText().toString();
        String plaer2name=player2.getText().toString();

        Intent intent=new Intent(this,GameThisPlay.class);
        intent.putExtra("Players Name",new String[]{plaer1name,plaer2name});

        startActivity(intent);
    }
}