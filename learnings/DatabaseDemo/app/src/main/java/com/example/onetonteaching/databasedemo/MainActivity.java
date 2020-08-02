package com.example.onetonteaching.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SQLiteDatabase database = this.openOrCreateDatabase("Employees", MODE_PRIVATE, null);

//        database.execSQL("CREATE TABLE IF NOT EXISTS employees (name VARCHAR, department VARCHAR, salary INT(4))");
//        database.execSQL("INSERT INTO employees (name, department, salary) VALUES('reshav', 'HR', 1200)");
//        database.execSQL("INSERT INTO employees (name, department, salary) VALUES('sagnik', 'IT', 800)");

//        database.execSQL("UPDATE employees SET salary = ? WHERE name = ?", new String[] {"900", "sagnik"});

        Cursor cursor = database.rawQuery("SELECT * FROM employees", null);

        cursor.moveToFirst();

        int nameIndex = cursor.getColumnIndex("name");
        int deptIndex = cursor.getColumnIndex("department");
        int salIndex = cursor.getColumnIndex("salary");

        if (cursor != null){

            Log.i("name", cursor.getString(nameIndex));
            Log.i("department", cursor.getString(deptIndex));
            Log.i("salary", Integer.toString(cursor.getInt(salIndex)));

        }

        while (cursor != null && cursor.moveToNext()){

            Log.i("name", cursor.getString(nameIndex));
            Log.i("department", cursor.getString(deptIndex));
            Log.i("salary", Integer.toString(cursor.getInt(salIndex)));

        }

//        database.execSQL("DELETE FROM employees");

        cursor.close();

    }
}