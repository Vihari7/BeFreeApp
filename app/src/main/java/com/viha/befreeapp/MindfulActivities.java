package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import androidx.cardview.widget.CardView;

public class MindfulActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindful_activities);

        CardView mindfulBreathingCard = findViewById(R.id.breathButton);
        CardView bodyScanMeditationCard = findViewById(R.id.bodyscanButton);
        CardView mindfulnessColoringCard = findViewById(R.id.coloringButton);
        CardView matchingPairsCard = findViewById(R.id.pairButton);
        CardView fiveSensesGameCard = findViewById(R.id.fivesensesButton);

        mindfulBreathingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, BreathMind.class);
                startActivity(intent);
            }
        });

        bodyScanMeditationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, Meditation.class);
                startActivity(intent);
            }
        });

        mindfulnessColoringCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, MindColoring.class);
                startActivity(intent);
            }
        });

        matchingPairsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, MatchPairs.class);
                startActivity(intent);
            }
        });

        fiveSensesGameCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, FiveSensesGame.class);
                startActivity(intent);
            }
        });
    }
}