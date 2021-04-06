package com.example.p5tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameThisPlay extends AppCompatActivity {

    private TicTacToeBoard ticTacToeBoard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_this_play);
        Button playAgainBTN=findViewById(R.id.playAgainBTN);
        Button homeBTN=findViewById(R.id.homeBTN);
        TextView playerTurn=findViewById(R.id.playerDisplay);

        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);

        String[] playerNames=getIntent().getStringArrayExtra("Players Name");
        if(playerNames!=null)
        {
            playerTurn.setText((playerNames[0]+"'s Turn"));
        }
        ticTacToeBoard=findViewById(R.id.ticTacToeBoard);
        ticTacToeBoard.setUpGame(playAgainBTN,homeBTN,playerTurn,playerNames);
    }

    public void playAgainButtonClick(View view) {
        ticTacToeBoard.resetGame();
        ticTacToeBoard.invalidate();
    }

    public void homeButtonClick(View view) {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}