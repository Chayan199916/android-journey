package com.example.howfastisyourbrain;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btn, again, first, second, third, fourth;
    RelativeLayout main;
    TextView finalResult, counter, result, ques;
    MediaPlayer audio;
    CountDownTimer countDownTimer;
    int chosenNo, sum, totScore, curScore;
    boolean flag;
    ArrayList<Integer> nums;

    public void startGame(View view){

        flag = true;
        totScore = curScore = 0;
        setEverything();
        finalResult = (TextView) findViewById(R.id.result);
        again = (Button) findViewById(R.id.again);
        finalResult.animate().alpha(0f).setDuration(1);
        again.animate().alpha(0f).setDuration(1);
        first.animate().alpha(1f).setDuration(100);
        second.animate().alpha(1f).setDuration(100);
        third.animate().alpha(1f).setDuration(100);
        fourth.animate().alpha(1f).setDuration(100);
        nums = new ArrayList<>();

    }

    public void tapPlay(View view){

            if(flag){

                Button tappedBtn = (Button) view;
                totScore += 1;
                chosenNo = Integer.parseInt(tappedBtn.getText().toString());
                if (chosenNo == sum){

                    curScore += 1;
                    Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(this, "Incorrect!", Toast.LENGTH_SHORT).show();

                }
                result.setText(curScore + "/" + totScore);
                makeRandomQuestions();
            }



    }

    public void makeRandomQuestions(){

        Random rand = new Random();
        nums = new ArrayList<>();
        int firstNo = rand.nextInt(900) + 100;
        int secondNo = rand.nextInt(900) + 100;
        sum = firstNo + secondNo;
        int randomPos = rand.nextInt(4);
        for (int i = 0; i < 4; i++){
            if (randomPos == i) nums.add(sum);
            else{

                int incorAns = rand.nextInt(900) + 100;
                while (incorAns == sum){

                    incorAns = rand.nextInt(900) + 100;

                }
                nums.add(incorAns);
            }

        }
        ques.setText(Integer.toString(firstNo) + "+" + Integer.toString(secondNo) + "=?");
        first.setText(Integer.toString(nums.get(0)));
        second.setText(Integer.toString(nums.get(1)));
        third.setText(Integer.toString(nums.get(2)));
        fourth.setText(Integer.toString(nums.get(3)));

    }

    public void setEverything(){

        countDownTimer = new CountDownTimer(30000 + 100, 1000){

            public void onTick(long milliSecsUntilDone){

                String prefix;
                counter = findViewById(R.id.counter);
                if (milliSecsUntilDone < 10000){

                    prefix = "00:0";

                }else{

                    prefix = "00:";

                }
                counter.setText(prefix + Long.toString(milliSecsUntilDone / 1000));

            }

            public void onFinish(){

                audio.start();
                flag = false;
                result = findViewById(R.id.score);
                String scores[] = result.getText().toString().split("/");
                if (Float.parseFloat(scores[0]) / Float.parseFloat(scores[1]) >= 0.5f){

                    finalResult.setText("Good Performance!");

                }else{

                    finalResult.setText("Bad Performance!");

                }
                finalResult.animate().alpha(1f).setDuration(500);
                again.animate().alpha(1f).setDuration(500);
                first.animate().alpha(0.3f).setDuration(100);
                second.animate().alpha(0.3f).setDuration(100);
                third.animate().alpha(0.3f).setDuration(100);
                fourth.animate().alpha(0.3f).setDuration(100);
            }

        };
        countDownTimer.start();
        makeRandomQuestions();
        result.setText("0/0");

    }

    public void mainShow(View view){

        btn = (Button) view;
        btn.setVisibility(View.INVISIBLE);
        main = (RelativeLayout) findViewById(R.id.main);
        main.animate().alpha(1f).setDuration(500);
        setEverything();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout main = (RelativeLayout) findViewById(R.id.main);
        main.animate().alpha(0f).setDuration(1);
        finalResult = (TextView) findViewById(R.id.result);
        again = (Button) findViewById(R.id.again);
        finalResult.animate().alpha(0f).setDuration(1);
        again.animate().alpha(0f).setDuration(1);
        audio = MediaPlayer.create(this, R.raw.horn);
        result = (TextView) findViewById(R.id.score);
        ques = findViewById(R.id.ques);
        first = (Button) findViewById(R.id.first);
        second = (Button) findViewById(R.id.second);
        third = (Button) findViewById(R.id.third);
        fourth = (Button) findViewById(R.id.fourth);
        flag = true;
        totScore = curScore = 0;
    }

    @Override
    protected void onPause() {
            super.onPause();
            countDownTimer.cancel();
    }
}