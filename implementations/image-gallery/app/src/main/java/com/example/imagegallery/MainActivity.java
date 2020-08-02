package com.example.imagegallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int start = 0;
    int max = 5;
    int counter = start;
    int images[] ={R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f};
    String names[] ={"Nature photo image 1", "Nature photo image 2", "Nature photo image 3", "Nature photo image 4", "Nature photo image 5", "Nature photo image 6"};
    public void clickPrev(View view){
        TextView numberCounter = findViewById(R.id.counter);
        TextView name = findViewById((R.id.name));
        counter--;
        if(counter < 0){
            counter = max;
        }
        numberCounter.setText(Integer.toString(counter + 1) + "|6");
        name.setText(names[counter]);
        ImageView img = findViewById(R.id.mainImage);
        img.setImageResource(images[counter]);
    }
    public void clickNext(View view){
        TextView numberCounter = findViewById(R.id.counter);
        TextView name = findViewById((R.id.name));
        counter++;
        if(counter > max){
            counter = start;
        }
        numberCounter.setText(Integer.toString(counter + 1) + "|6");
        name.setText(names[counter]);
        ImageView img = findViewById(R.id.mainImage);
        img.setImageResource(images[counter]);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}