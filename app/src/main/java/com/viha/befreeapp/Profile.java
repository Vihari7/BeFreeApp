package com.viha.befreeapp;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    private ImageView profilePicture;
    private TextView name;
    private TextView email;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        profilePicture = findViewById(R.id.profile_picture);
        name = findViewById(R.id.tvName);
        email = findViewById(R.id.tvEmail);

        View.OnClickListener editProfileListener = view -> showEditProfileDialog();

        profilePicture.setOnClickListener(editProfileListener);
        name.setOnClickListener(editProfileListener);
        email.setOnClickListener(editProfileListener);

        TextView settings = findViewById(R.id.settings);
        TextView tvShare = findViewById(R.id.tvShare);
        TextView tvAboutUs = findViewById(R.id.tvAbout_us);
        TextView tvFaqs = findViewById(R.id.tvFaqs);
        TextView tvLogout = findViewById(R.id.tvLogout);

        settings.setOnClickListener(view -> {
            startActivity(new Intent(Profile.this, Settings.class));
        });

        tvShare.setOnClickListener(view -> {
            shareProfile();
        });

        tvAboutUs.setOnClickListener(view -> {
            startActivity(new Intent(Profile.this, AboutUs.class));
        });

        tvFaqs.setOnClickListener(view -> {
            startActivity(new Intent(Profile.this, FAQs.class));
        });

        tvLogout.setOnClickListener(view -> {
            performLogout();
        });
    }

    private void showEditProfileDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Profile");

        View viewInflated = LayoutInflater.from(this).inflate(R.layout.dialog_edit_profile,
                (ViewGroup) findViewById(android.R.id.content), false);
        final ImageView editProfilePicture = viewInflated.findViewById(R.id.edit_profile_picture);
        final EditText editName = viewInflated.findViewById(R.id.edit_name);
        final EditText editEmail = viewInflated.findViewById(R.id.edit_email);
        Button saveButton = viewInflated.findViewById(R.id.save_button);

        editName.setText(name.getText().toString());
        editEmail.setText(email.getText().toString());

        builder.setView(viewInflated);

        AlertDialog dialog = builder.create();

        saveButton.setOnClickListener(v -> {
            // Save the new values
            name.setText(editName.getText().toString());
            email.setText(editEmail.getText().toString());
            // update here if needed
            dialog.dismiss();
        });

        dialog.show();
    }

    private void shareProfile() {
        // Share functionality
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out this relaxing app: https://befree.com");
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }

    private void performLogout() {
        // Logout functionality
        startActivity(new Intent(Profile.this, Login.class));
        finish(); // Optional: Close the Profile activity
    }
}