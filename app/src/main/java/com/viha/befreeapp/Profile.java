package com.viha.befreeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Profile extends AppCompatActivity {
    private ImageView profilePicture;
    private TextView name;
    private TextView email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePicture = findViewById(R.id.profile_picture);
        name = findViewById(R.id.tvName);
        email = findViewById(R.id.tvEmail);

        View.OnClickListener editProfileListener = view -> showEditProfileDialog();

        profilePicture.setOnClickListener(editProfileListener);
        name.setOnClickListener(editProfileListener);
        email.setOnClickListener(editProfileListener);
    }

    private void showEditProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Profile");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_edit_profile, (ViewGroup)
                findViewById(android.R.id.content), false);
        final ImageView editProfilePicture = viewInflated.findViewById(R.id.edit_profile_picture);
        final EditText editName = viewInflated.findViewById(R.id.edit_name);
        final EditText editEmail = viewInflated.findViewById(R.id.edit_email);
        Button saveButton = viewInflated.findViewById(R.id.save_button);

        // Pre-fill the fields with the current values
        editName.setText(name.getText().toString());
        editEmail.setText(email.getText().toString());

        builder.setView(viewInflated);

        AlertDialog dialog = builder.create();

        saveButton.setOnClickListener(v -> {
            // Save the new values
            name.setText(editName.getText().toString());
            email.setText(editEmail.getText().toString());
            // Handle profile picture update here if needed
            dialog.dismiss();
        });

        dialog.show();
    }
}