package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;

public class Dashboard extends AppCompatActivity {

    private View quotesCard;
    private View mindActCard;
    private View musicCard;
    private View docSupportCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize card views
        quotesCard = findViewById(R.id.activityButtons);
        mindActCard = findViewById(R.id.mindText);
        musicCard = findViewById(R.id.musicText);
        docSupportCard = findViewById(R.id.docText);

        // Set onClickListeners for activity cards
        quotesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openQuotesActivity();
            }
        });

        mindActCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMindfulActivity();
            }
        });

        musicCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMusicActivity();
            }
        });

        docSupportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDocSupportActivity();
            }
        });
    }

    private void openQuotesActivity() {
        Intent intent = new Intent(Dashboard.this, Quotes.class);
        startActivity(intent);
    }

    private void openMindfulActivity() {
        Intent intent = new Intent(Dashboard.this, MindfulActivities.class);
        startActivity(intent);
    }

    private void openMusicActivity() {
        Intent intent = new Intent(Dashboard.this,RelaxingMusic.class);
        startActivity(intent);
    }

    private void openDocSupportActivity() {
        Intent intent = new Intent(Dashboard.this, DoctorSupport.class);
        startActivity(intent);
    }
}