package com.example.onetonteaching.firebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Real time database handling

//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

//        Map<String, String> map= new HashMap<>();
//        map.put("name", "chayan");
//        databaseReference.push().setValue(map, new DatabaseReference.CompletionListener() {
//            @Override
//            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
//
//                if (databaseError == null){
//
//                    Log.i("status", "Saving Done...");
//
//                }else {
//
//                    Log.i("status", "Saving failed");
//
//                }
//
//            }
//        });

        // Firestore

        // step - 1 : Initialize cloud firestore

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // step - 2 : Create new map object to send data to db

        Map<String, Object> user = new HashMap<>();

        // step - 3 : Add data to the map

        user.put("first", "chayan");
        user.put("last", "sengupta");
        user.put("city", "kolkata");
        user.put("state", "west bengal");
        user.put("country", "india");

        // step - 4 : Add a new document with a generated ID:
        // Model of Firestore : data -> document -> collection

        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {

                        // here we will put the code that will be executed after successful entry of data
                        Log.i("status", "successful data insertion");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        // here we will put the code that will be executed after unsuccessful entry of data
                        Log.i("status", "unsuccessful data insertion");

                    }
                });



    }
}