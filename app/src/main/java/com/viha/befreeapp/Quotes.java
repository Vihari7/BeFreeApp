package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import androidx.cardview.widget.CardView;

public class Quotes extends AppCompatActivity {
    // Declare CardView variables for different categories
    private CardView  lifeCard, loveCard, motiveCard, healthCard, eduCard, selfCard;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);

        lifeCard = findViewById(R.id.lifeCard);
        loveCard = findViewById(R.id.loveCard);
        motiveCard = findViewById(R.id.motiveCard);
        healthCard = findViewById(R.id.healthCard);
        eduCard = findViewById(R.id.eduCard);
        selfCard = findViewById(R.id.selfCard);

        // Set onClickListeners for each CardView to open the Gallery
        lifeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Life");// Open gallery with "Life" category
            }
        });

        loveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Love");
            }
        });

        motiveCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Motivation");
            }
        });

        healthCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Health");
            }
        });

        eduCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Education");
            }
        });

        selfCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Self Love");
            }
        });
    }
   // Method to open Gallery with a specific category
    private void openGallery(String category) {
        Intent intent = new Intent(this, Gallery.class);
        intent.putExtra("CATEGORY", category);// Pass the category as an intent extra
        startActivity(intent);// Start the Gallery
    }
}