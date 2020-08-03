package com.example.eggapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    MediaPlayer audio;
    SeekBar seekBar;
    Button btn;
    TextView textView;
    int flag = 1;
    CountDownTimer cdt;
    public void countDownStart(View view){

            textView = findViewById(R.id.displayTime);
            btn = findViewById(R.id.buttonView);
            if(flag == 1){

                btn.setText("Stop!");
                seekBar.setEnabled(false);
                String timeText = textView.getText().toString();
                String leftTime[] = timeText.split(":");
                int leftTotalTime = Integer.parseInt(leftTime[0]) * 60 + Integer.parseInt(leftTime[1]);
                cdt = new CountDownTimer(leftTotalTime * 1000 + 100, 1000){

                    public void onTick(long milliSecsUntilDone){

                        int secsUntilDone = (int) milliSecsUntilDone / 1000;
                        textView = (TextView) findViewById(R.id.displayTime);
                        int minutes = secsUntilDone / 60;
                        int seconds = secsUntilDone % 60;
                        if (seconds < 10){

                            textView.setText(minutes + ":" + "0" + seconds);

                        }else{

                            textView.setText(minutes + ":" + seconds);

                        }

                    }

                    public void onFinish(){

                        textView.setText("0:00");
                        audio.start();
                        btn.setText("Again?");
                        seekBar.setEnabled(true);
                        seekBar.setProgress(30);
                        flag = 1;

                    }

                };
                cdt.start();
                flag = 0;

            }else{

                btn.setText("Start!");
                seekBar.setEnabled(true);
                seekBar.setProgress(30);
                textView.setText("0:30");
                cdt.cancel();
                flag = 1;

            }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        audio = MediaPlayer.create(this, R.raw.horn);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                textView = (TextView) findViewById(R.id.displayTime);
                int minutes = (int) i / 60;
                int seconds = i % 60;
                if (seconds < 10){

                    textView.setText(minutes + ":" + "0" + seconds);

                }else{

                    textView.setText(minutes + ":" + seconds);

                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}