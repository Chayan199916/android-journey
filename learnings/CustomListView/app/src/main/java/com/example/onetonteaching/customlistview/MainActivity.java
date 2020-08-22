package com.example.onetonteaching.customlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    public static Integer demoImages[] = {

            R.drawable.image, R.drawable.insta_logo,
            R.drawable.image, R.drawable.insta_logo,
            R.drawable.image, R.drawable.insta_logo,
            R.drawable.image, R.drawable.insta_logo,

    };
    public static String names[] = {
            "demo text 1", "demo text 2", "demo text 3", "demo text 4",
            "demo text 1", "demo text 2", "demo text 3", "demo text 4"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyListAdapter adapter = new MyListAdapter(this, names, demoImages);
        listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}