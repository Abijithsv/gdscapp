package com.example.gdseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.gdseapp.databinding.ActivityProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Profile extends AppCompatActivity {

    ActivityProfileBinding binding;
    String Name, Age, Strength;
    FirebaseDatabase db;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Name = binding.Name.getText().toString();
                Age = binding.Age.getText().toString();
                Strength = binding.Strength.getText().toString();

                if (!Name.isEmpty() && !Age.isEmpty() && !Strength.isEmpty()) {
                    Users users = new Users(Name, Age, Strength);
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference("Users");

                    reference.child(Name).setValue(users).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            binding.Name.setText("");
                            binding.Age.setText("");
                            binding.Strength.setText("");

                            Toast.makeText(Profile.this, "Successfully Updated", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        // Add onClickListener for Save Button to navigate to DisplayActivity
        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open DisplayActivity after successful update
                Intent intent = new Intent(Profile.this, DisplayActivity.class);
                startActivity(intent);
            }
        });
    }
}
