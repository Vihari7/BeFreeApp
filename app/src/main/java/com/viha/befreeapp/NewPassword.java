package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class NewPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);

        EditText newPasswordEditText = findViewById(R.id.typeNewPw);
        EditText confirmPasswordEditText = findViewById(R.id.confirmPassword);
        Button saveButton = findViewById(R.id.buttonSave);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = newPasswordEditText.getText().toString();
                String confirmPassword = confirmPasswordEditText.getText().toString();

                if (newPassword.equals(confirmPassword)) {
                    // Save the new password (implement your saving logic here)
                    Toast.makeText(NewPassword.this, "Password Changed Successfully", Toast.LENGTH_SHORT).show();

                    // Navigate to Dashboard activity after saving the password
                    Intent intent = new Intent(NewPassword.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(NewPassword.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}