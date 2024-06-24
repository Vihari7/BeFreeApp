package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText typeEmail;
    private EditText typePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        typeEmail = findViewById(R.id.typeEmail);
        typePassword = findViewById(R.id.typePassword);
        Button buttonLogin = findViewById(R.id.buttonSignup);
        TextView linkToSignup = findViewById(R.id.linkToSignup);
        TextView linkToForgetPW = findViewById(R.id.linktoForgetPW);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInput()) {
                    // Redirect to Dashboard
                    Intent intent = new Intent(Login.this, Dashboard.class);
                    startActivity(intent);
                }
            }
        });

        linkToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Signup
                Intent intent = new Intent(Login.this, Signup.class);
                startActivity(intent);
            }
        });

        linkToForgetPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Forgot Password page
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
            }
        });
    }
    private boolean validateInput() {
        String email = typeEmail.getText().toString().trim();
        String password = typePassword.getText().toString().trim();

        if (email.isEmpty()) {
            typeEmail.setError("Email is required");
            typeEmail.requestFocus();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            typeEmail.setError("Please enter a valid email");
            typeEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            typePassword.setError("Password is required");
            typePassword.requestFocus();
            return false;
        }

        if (password.length() < 8) {
            typePassword.setError("Password must be at least 8 characters");
            typePassword.requestFocus();
            return false;
        }
        return true;
    }
    private void loginUser() {
        String email = typeEmail.getText().toString().trim();
        String password = typePassword.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Dashboard.class));
                    } else {
                        Toast.makeText(Login.this, "Login Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}