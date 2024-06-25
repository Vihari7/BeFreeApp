package com.viha.befreeapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    private EditText editEmail;
    private Button buttonSubmit;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

           editEmail = findViewById(R.id.editEmail);
           buttonSubmit = findViewById(R.id.buttonSubmit);

           buttonSubmit.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   String email = editEmail.getText().toString().trim();
                   if (TextUtils.isEmpty(email)) {
                       editEmail.setError("Email is required");
                       return;
                   }

                   // Send OTP to email
                   sendOTPToEmail(email);
               }
           });
       }

    private void sendOTPToEmail(String email) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgotPassword.this, "OTP sent to email", Toast.LENGTH_SHORT).show();
                            // Navigate to VerifyOTP activity
                            Intent intent = new Intent(ForgotPassword.this, VerifyOTP.class);
                            intent.putExtra("email", email);
                            startActivity(intent);
                        } else {
                            String errorMessage = task.getException().getMessage();
                            Toast.makeText(ForgotPassword.this, "Failed to send OTP: " + errorMessage, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}