package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
public class ForgotPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button submitButton = findViewById(R.id.buttonSubmit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the phone number entered by the user
                EditText phoneNumberEditText = findViewById(R.id.typeNumber);
                String phoneNumber = phoneNumberEditText.getText().toString();

                // Create an intent to start VerifyOTP activity
                Intent intent = new Intent(ForgotPassword.this, VerifyOTP.class);
                // Optionally, pass the phone number to the VerifyOTP activity
                intent.putExtra("PHONE_NUMBER", phoneNumber);
                startActivity(intent);
            }
        });
    }
}