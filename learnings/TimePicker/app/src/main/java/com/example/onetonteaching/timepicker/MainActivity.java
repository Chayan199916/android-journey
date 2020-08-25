package com.example.onetonteaching.timepicker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView tvTimer1, tvTimer2;
    int t1Hr, t1Min, t2Hr, t2Min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimer1 = findViewById(R.id.tv_timer1);
        tvTimer2 = findViewById(R.id.tv_timer2);

        tvTimer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(

                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                                t1Hr = i;
                                t1Min = i1;
                                Calendar calendar = Calendar.getInstance();
                                calendar.set(0, 0, 0, t1Hr, t1Min);
                                tvTimer1.setText(DateFormat.format("hh:mm aa", calendar));

                            }
                        }, 12, 0, false

                );

                timePickerDialog.updateTime(t1Hr, t1Min);
                timePickerDialog.show();

            }
        });

        tvTimer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                t2Hr = i;
                                t2Min = i1;
                                String time = t2Hr + ":" + t2Min;
                                SimpleDateFormat f24Hrs = new SimpleDateFormat(
                                        "HH:mm"
                                );
                                try {
                                    Date date = f24Hrs.parse(time);
                                    SimpleDateFormat f12Hrs = new SimpleDateFormat(
                                            "hh:mm aa"
                                    );
                                    tvTimer2.setText(f12Hrs.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );

                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                timePickerDialog.updateTime(t2Hr, t2Min);
                timePickerDialog.show();

            }
        });

    }
}