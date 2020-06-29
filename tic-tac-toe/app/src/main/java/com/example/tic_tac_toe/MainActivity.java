package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    boolean gameValid = true;
    int curPosOfGame[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int activePlayer = 0;
    int validWinPositions[][] = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};
    public void tap_to_play(View view){
        int flag = 1;
        if(!gameValid){
            gameReset();
        }
        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
        if(curPosOfGame[tappedImg] == 2 && gameValid) {
            curPosOfGame[tappedImg] = activePlayer;
            img.setTranslationY(-500f);
            TextView status = findViewById(R.id.status);
            if(activePlayer == 0){
                status.setText("Player 2, Now your turn.");
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
            }
            else{
                status.setText("Player 1, Now your turn.");
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
            }
            img.animate().translationYBy(500f).setDuration(70);
            for(int i = 0; i < 8; i++){
                if(curPosOfGame[validWinPositions[i][0]] == curPosOfGame[validWinPositions[i][1]] && curPosOfGame[validWinPositions[i][1]] == curPosOfGame[validWinPositions[i][2]] && curPosOfGame[validWinPositions[i][1]] != 2){
                   String winTeam = curPosOfGame[validWinPositions[i][0]] == 0 ? "Player 1" : "Player 2";
                   Toast.makeText(this, "GAME OVER !", Toast.LENGTH_SHORT).show();
                   status.setText(winTeam + " has won, Congrats!");
                   gameValid = false;
                   break;
                }
            }
            for(int i = 0; i < curPosOfGame.length; i++){
                if(curPosOfGame[i] == 2){
                    flag = 0;
                    break;
                }
            }
            if(flag == 1 && gameValid != false){
                Toast.makeText(this, "GAME OVER !", Toast.LENGTH_SHORT).show();
                status.setText("Match tied! Tap to start again.");
                gameValid = false;
            }
        }
    }
    public void gameReset(){
        activePlayer = 0;
        for(int i = 0; i < curPosOfGame.length; i++){
            curPosOfGame[i] = 2;
        }
        ImageView img = (ImageView) findViewById(R.id.imageView11);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView10);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView9);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView8);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView7);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView6);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView5);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView4);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView3);
        img.setImageResource(0);
        TextView status = findViewById(R.id.status);
        status.setText("X's turn - tap to play");
        gameValid = true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}