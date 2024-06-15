package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class VerifyOTP extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        // Retrieve the phone number passed from ForgotPassword activity
        String phoneNumber = getIntent().getStringExtra("PHONE_NUMBER");

        // Use the phone number for your verification process
        TextView verifyOtpText = findViewById(R.id.verifyOtpText);
        verifyOtpText.setText("Verify " + phoneNumber);

        // Initialize the EditText and Button
        EditText otpEditText = findViewById(R.id.textOfCode);
        Button verifyButton = findViewById(R.id.buttonVerify);

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the OTP entered by the user
                String otp = otpEditText.getText().toString();

                // Verify the OTP (this is where you would add your verification logic)
                if (otp.equals("123456")) { // Placeholder for actual OTP verification
                    Toast.makeText(VerifyOTP.this, "OTP Verified", Toast.LENGTH_SHORT).show();

                    // Ask user if they want to change password
                    Intent intent = new Intent(VerifyOTP.this, NewPassword.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(VerifyOTP.this, "Invalid OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Resend OTP logic
        TextView resendText = findViewById(R.id.resendText);
        resendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Logic to resend OTP
                Toast.makeText(VerifyOTP.this, "OTP Resent", Toast.LENGTH_SHORT).show();
            }
        });
    }
}