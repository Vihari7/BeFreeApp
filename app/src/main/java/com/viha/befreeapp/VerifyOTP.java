package com.viha.befreeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class VerifyOTP extends AppCompatActivity {
    private EditText inputOTP;
    private Button buttonVerify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        inputOTP = findViewById(R.id.inputOTP);
        buttonVerify = findViewById(R.id.buttonVerify);

        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = inputOTP.getText().toString().trim();
                // If OTP is correct, navigate to NewPassword
                Intent intent = new Intent(VerifyOTP.this, NewPassword.class);
                startActivity(intent);
            }
        });
    }
}