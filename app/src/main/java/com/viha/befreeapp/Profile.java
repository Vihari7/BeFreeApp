package com.viha.befreeapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

        profilePicture.setOnClickListener(view -> editProfilePicture());
        name.setOnClickListener(view -> editName());
        email.setOnClickListener(view -> editEmail());
    }

    private void editProfilePicture() {
        // for profile picture editing
        Toast.makeText(this, "Edit Profile Picture Clicked", Toast.LENGTH_SHORT).show();
    }

    private void editName() {
        showEditDialog("Edit Name", name);
    }

    private void editEmail() {
        showEditDialog("Edit Email", email);
    }

    private void showEditDialog(String title, TextView textView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_edit_profile, (ViewGroup) findViewById(android.R.id.content), false);
        final EditText input = viewInflated.findViewById(R.id.etInput);
        input.setText(textView.getText().toString());

        builder.setView(viewInflated);

        builder.setPositiveButton(android.R.string.ok, (dialog, which) -> {
            dialog.dismiss();
            textView.setText(input.getText().toString());
        });
        builder.setNegativeButton(android.R.string.cancel, (dialog, which) -> dialog.cancel());

        builder.show();
    }
}