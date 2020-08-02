package com.example.crossfadeanimation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    int flag = 1;

    public void fadeFun2(View view){

//        if (flag == 1){
//
//            ImageView img1 = (ImageView) findViewById(R.id.imageView2);
//
//            img1.animate().alpha(0f).setDuration(2000);
//
//            ImageView img2 = (ImageView) findViewById(R.id.imageView1);
//
//            img2.animate().alpha(1f).setDuration(2000);
//
//            flag = 0;
//
//        }else{
//
//            ImageView img1 = (ImageView) findViewById(R.id.imageView1);
//
//            img1.animate().alpha(0f).setDuration(2000);
//
//            ImageView img2 = (ImageView) findViewById(R.id.imageView2);
//
//            img2.animate().alpha(1f).setDuration(2000);
//
//            flag = 1;
            ImageView img = (ImageView) findViewById(R.id.imageView2);
            img.animate().rotationBy(360f).scaleX(0.5f).scaleY(0.5f).setDuration(5000);
            img.animate().rotationBy(-360f).scaleX(2f).scaleY(2f).setDuration(5000);


        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ImageView img = (ImageView) findViewById(R.id.imageView2);
//        img.setTranslationX(-1500f);
    }
}