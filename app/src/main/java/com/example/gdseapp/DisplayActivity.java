package com.example.gdseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DisplayActivity extends AppCompatActivity {

    private TextView displayNameTextView;
    private TextView displayAgeTextView;
    private TextView displayStrengthTextView;

    // Firebase
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        // Initialize Firebase
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Initialize TextViews
        displayNameTextView = findViewById(R.id.displayName);
        displayAgeTextView = findViewById(R.id.displayAge);
        displayStrengthTextView = findViewById(R.id.displayStrength);

        // Retrieve data from Firebase
        mDatabase.child("users").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()) {
                    Log.e("firebase", "Error getting data", task.getException());
                } else {
                    DataSnapshot dataSnapshot = task.getResult();
                    if (dataSnapshot.exists()) {
                        // Get data and update TextViews
                        String name = dataSnapshot.child("name").getValue(String.class);
                        String age = dataSnapshot.child("age").getValue(String.class);
                        String strength = dataSnapshot.child("strength").getValue(String.class);

                        // Update TextViews with retrieved data
                        displayNameTextView.setText("Name: " + name);
                        displayAgeTextView.setText("Age: " + age);
                        displayStrengthTextView.setText("Strength: " + strength);
                    }
                }
            }
        });
    }
}
