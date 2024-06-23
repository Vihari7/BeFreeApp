package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import androidx.cardview.widget.CardView;

public class MindfulActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindful_activities);

        CardView mindfulBreathingCard = findViewById(R.id.breathButton);
        CardView bodyScanMeditationCard = findViewById(R.id.bodyscanButton);
        CardView matchingWalkCard = findViewById(R.id.walkButton);
        CardView kindMedtationCard = findViewById(R.id.kindMedButton);

        // link to pages of mindful activity categories
        mindfulBreathingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this,
                        BreathInstruction_MindAct.class);
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

        kindMedtationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, KindnessMeditation.class);
                startActivity(intent);
            }
        });

        matchingWalkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this,MindfulWalking.class);
                startActivity(intent);
            }
        });

    }
}