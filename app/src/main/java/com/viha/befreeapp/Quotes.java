package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import androidx.cardview.widget.CardView;

public class Quotes extends AppCompatActivity {
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

        lifeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery("Life");
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

    private void openGallery(String category) {
        Intent intent = new Intent(this, Gallery.class);
        intent.putExtra("CATEGORY", category);
        startActivity(intent);
    }
}