package com.example.onetonteaching.customswitch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    SwitchCompat switchCompat;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        switchCompat = findViewById(R.id.switchButton);
        imageView = findViewById(R.id.imageView);

        imageView.setImageDrawable(getResources().getDrawable(R.drawable.off));

        switchCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (switchCompat.isChecked()){

                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.on));

                }else {

                    imageView.setImageDrawable(getResources().getDrawable(R.drawable.off));

                }

            }
        });

    }
}