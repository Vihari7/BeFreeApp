package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class Quotes extends AppCompatActivity {
    private ImageView lifeImage, loveImage, motiveImage, healthImage, eduImage, selfImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        lifeImage = findViewById(R.id.lifeImage);
        loveImage = findViewById(R.id.loveImage);
        motiveImage = findViewById(R.id.motiveImage);
        healthImage = findViewById(R.id.healthImage);
        eduImage = findViewById(R.id.eduImage);
        selfImage = findViewById(R.id.selfImage);

        lifeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Life");
            }
        });

        loveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Love");
            }
        });

        motiveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Motivation");
            }
        });

        healthImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Health");
            }
        });

        eduImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Education");
            }
        });

        selfImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Self Love");
            }
        });
    }

    private void openGallery(String category) {
        Intent intent = new Intent(this, Gallery.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}