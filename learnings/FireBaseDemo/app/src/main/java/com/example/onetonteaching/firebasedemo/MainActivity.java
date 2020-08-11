package com.example.onetonteaching.firebasedemo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // step - 1 : Initialize cloud firestore

    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button button;
    TextView textView;
    ListenerRegistration listener;
    DocumentReference docref = db.collection("users").document("uNT2muB7HGkPKh1E0p5a");

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

//        Inserting data

//        // step - 2 : Create new map object to send data to db
//
//        Map<String, Object> user = new HashMap<>();
//
//        // step - 3 : Add data to the map
//
//        user.put("first", "manjistha");
//        user.put("last", "paul");
//        user.put("city", "kolkata");
//        user.put("state", "west bengal");
//        user.put("country", "india");
//
//        // step - 4 : Add a new document with a generated ID:
//        // Model of Firestore : data -> document -> collection
//
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//
//                        // here we will put the code that will be executed after successful entry of data
//                        Log.i("status", "successful data insertion");
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        // here we will put the code that will be executed after unsuccessful entry of data
//                        Log.i("status", "unsuccessful data insertion");
//
//                    }
//                });

        //           Reading data from firestore

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView);

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                db.collection("users")
//                        .get()
//                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                            @Override
//                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                will be executed when data will be completely received
//                                if (task.isSuccessful()){
//
//                                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){
//
//                                        textView.setText("data received" + documentSnapshot.getId() + " -> " + documentSnapshot.getData());
//                                            textView.setText(documentSnapshot.getString("state"));
//
//                                    }
//
//                                }
//
//                            }
//                        });
//
//            }
//        });

//        Deleting data

//        docref.update("first", FieldValue.delete());
//        docref.update("last", FieldValue.delete());
//        docref.update("city", FieldValue.delete());
//        docref.update("state", FieldValue.delete());
//        docref.update("country", FieldValue.delete());

//        Deleting document

//        docref.delete();


//        Creating custom object

        /*
        * Create a model class
        * Connecting this model class with main activity
        * Using model class in CRUD operations
        *
        * */

//        Model model = new Model("chayan", "sengupta", "kolkata", "west bengal", "india");
//
//        docref.set(model)
//                .addOnSuccessListener(new OnSuccessListener<Void>() {
//                    @Override
//                    public void onSuccess(Void aVoid) {
//
//                        Toast.makeText(MainActivity.this, "Saving Done...", Toast.LENGTH_SHORT).show();
//
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//
//                        Toast.makeText(MainActivity.this, "Failed to save...", Toast.LENGTH_SHORT).show();
//
//                    }
//                });

    }

    @Override
    protected void onStart(){

        super.onStart();

        listener = docref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (e != null){

                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    return;

                }

                if (documentSnapshot.exists()){

//                    String first = documentSnapshot.getString("first");
//                    try {
//
////                        Log.i("state", state);
//                        textView.setText(first);
//
//                    }catch (NullPointerException e1){
//
//                        textView.setText("Not available");
//
//                    }

                    Model model = documentSnapshot.toObject(Model.class);

                    String first = model.getFirst();
                    String last = model.getLast();

                    textView.setText(first + " " + last);

                }

            }
        });

    }

}