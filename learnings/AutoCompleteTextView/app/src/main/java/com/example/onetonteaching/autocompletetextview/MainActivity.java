package com.example.onetonteaching.autocompletetextview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class MainActivity extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextView;
    String names[] = {

            "Chayan",
            "Manjistha",
            "Sagnik",
            "Reshav",
            "Sanglap",
            "Ouddhav"

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        autoCompleteTextView = (AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.select_dialog_item, names);

        autoCompleteTextView.setThreshold(1); // no of characters needed to start auto-completion
        autoCompleteTextView.setAdapter(adapter);

    }
}