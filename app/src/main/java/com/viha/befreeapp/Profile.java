package com.viha.befreeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView profileUsername, profileEmail, profilePassword;
    TextView titleUsername, faqsButton, aboutUsButton, logoutButton;
    Button editProfile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profileUsername = findViewById(R.id.profileUsername);
        profileEmail = findViewById(R.id.profileEmail);
        profilePassword = findViewById(R.id.profilePassword);
        titleUsername = findViewById(R.id.titleUsername);
        editProfile = findViewById(R.id.editButton);
        faqsButton = findViewById(R.id.redirectToFaqs);
        aboutUsButton = findViewById(R.id.redicrectToAboutus);
        logoutButton = findViewById(R.id.tvLogout);

        showAllUserData();
        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });

        faqsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, FAQs.class));
            }
        });

        aboutUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Profile.this, AboutUs.class));
            }
        });

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirecting to login screen
                Intent intent = new Intent(Profile.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    public void showAllUserData(){
        Intent intent = getIntent();
        String updatedUsername = intent.getStringExtra("username");
        String updatedEmail = intent.getStringExtra("email");
        String updatedPassword = intent.getStringExtra("password");


        titleUsername.setText(updatedUsername);
        profileUsername.setText(updatedUsername);
        profileEmail.setText(updatedEmail);
        profilePassword.setText(updatedPassword);
    }
    public void passUserData() {
        String userUsername = profileUsername.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkUserDatabase = reference.orderByChild("username").equalTo(userUsername);
        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String usernameFromDB = snapshot.child(userUsername).child("username").getValue(String.class);
                    String emailFromDB = snapshot.child(userUsername).child("email").getValue(String.class);
                    String passwordFromDB = snapshot.child(userUsername).child("password").getValue(String.class);
                    Intent intent = new Intent(Profile.this, EditProfile.class);
                    intent.putExtra("username", usernameFromDB);
                    intent.putExtra("email", emailFromDB);
                    intent.putExtra("password", passwordFromDB);
                    startActivityForResult(intent, 1); // Start EditProfile for result
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        showAllUserData(); // Refresh the user data
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            String updatedUsername = data.getStringExtra("username");
            String updatedEmail = data.getStringExtra("email");
            String updatedPassword = data.getStringExtra("password");

            titleUsername.setText(updatedUsername);
            profileUsername.setText(updatedUsername);
            profileEmail.setText(updatedEmail);
            profilePassword.setText(updatedPassword);
        }
    }

}