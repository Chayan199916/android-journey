package com.example.guessthenumber;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameValid = true;
    int predictNumber;
    public void makeToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public void guessClicked(View view){
//        Log.i("number", Integer.toString((predictNumber)));
        if(!gameValid){
            Random rand = new Random();
            predictNumber = rand.nextInt(20) + 1;
//            Log.i("number", Integer.toString((predictNumber)));
            gameValid = true;
        }
        EditText guessedNumberText = (EditText) findViewById(R.id.guessedNumberText);
        if(guessedNumberText.getText().toString().equals("")){
            makeToast("Oops! You forgot to give an input first.");
            return;
        }
        int number = Integer.parseInt(guessedNumberText.getText().toString());
        if(number < predictNumber){
            makeToast("HIGHER!");
        }else if(number > predictNumber){
            makeToast("LOWER!");
        }else{
            makeToast("CONGRATS! TRY AGAIN!");
            gameValid = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Random rand = new Random();
        predictNumber = rand.nextInt(20) + 1;
    }
}