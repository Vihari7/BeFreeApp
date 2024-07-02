package com.viha.befreeapp;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {
    EditText signupUsername, signupEmail, signupPassword;
    TextView loginRedirectText;
    Button signupButton;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupUsername = findViewById(R.id.signup_username);
        signupEmail = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        signupButton = findViewById(R.id.signup_button);

        signupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Initialize Firebase database and reference
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");
                // Get user input from EditText fields
                String username = signupUsername.getText().toString();
                String email = signupEmail.getText().toString();
                String password = signupPassword.getText().toString();

                // Validate email format
                if (!isValidEmail(email)) {
                    Toast.makeText(Signup.this, "Invalid email format", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if password length at least 6
                if (password.length() < 6) {
                    Toast.makeText(Signup.this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Create a HelperClass instance with user data
                HelperClass helperClass = new HelperClass(username, email, password);
                // Store user data in Firebase database under the username
                reference.child(username).setValue(helperClass);
                // Show signup success message
                Toast.makeText(Signup.this, "You have signup successfully!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Redirect to Login activity after successful signup
                Intent intent = new Intent(Signup.this, Login.class);
                startActivity(intent);
            }
        });
    }
    // Method to validate email format
    private boolean isValidEmail(String email) {
        return email.contains("@") && email.endsWith(".com");
    }
}