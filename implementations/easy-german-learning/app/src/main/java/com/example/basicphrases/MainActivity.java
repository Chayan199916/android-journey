package com.example.basicphrases;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    int voices[] = {R.raw.esfreutmich, R.raw.gutenabend, R.raw.gutenmorgen,
                    R.raw.gutentag, R.raw.ichheisse, R.raw.wiegehtesihnen,
                    R.raw.aufwiedersehen, R.raw.bitte, R.raw.estutmirleid, R.raw.gutenacht};

    public void playVoice(View view){

        int curTag = Integer.parseInt(view.getTag().toString());
        MediaPlayer audio = MediaPlayer.create(this, voices[curTag]);
        audio.start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}