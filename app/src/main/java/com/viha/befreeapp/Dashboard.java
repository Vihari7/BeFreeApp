package com.viha.befreeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
public class Dashboard extends AppCompatActivity {

    private View quotesCard;
    private View mindActCard;
    private View musicCard;
    private View docSupportCard;

    private ImageButton buttonExcited;
    private ImageButton buttonHappy;
    private ImageButton buttonSad;
    private ImageButton buttonTired;
    private ImageButton buttonAngry;

    static final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize card views
        quotesCard = findViewById(R.id.activityButtons);
        mindActCard = findViewById(R.id.mindText);
        musicCard = findViewById(R.id.musicText);
        docSupportCard = findViewById(R.id.docText);

        // Initialize mood buttons
        buttonExcited = findViewById(R.id.button_excited);
        buttonHappy = findViewById(R.id.button_happy);
        buttonSad = findViewById(R.id.button_sad);
        buttonTired = findViewById(R.id.button_tired);
        buttonAngry = findViewById(R.id.button_angry);


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

        // Set onClickListeners for mood buttons
        buttonExcited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGoodMomentsSplash();
            }
        });

        buttonHappy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGoodMomentsSplash();
            }
        });

        buttonSad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBadMomentsSplash();
            }
        });

        buttonTired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBadMomentsSplash();
            }
        });

        buttonAngry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBadMomentsSplash();
            }
        });
        // Initialize BottomNavigationView
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        // Already on the Dashboard
                        return true;
                    case R.id.nav_message:
                        startActivity(new Intent(Dashboard.this, Messages.class));
                        return true;
                    case R.id.nav_notes:
                        startActivity(new Intent(Dashboard.this, Notes.class));
                        return true;
                    case R.id.nav_notification:
                        startActivity(new Intent(Dashboard.this, Notification.class));
                        return true;
                    case R.id.nav_profile:
                        startActivity(new Intent(Dashboard.this, Profile.class));
                        return true;
                    default:
                        return false;
                }
            }
        });

        // Select the home item by default
        bottomNavigationView.setSelectedItemId(R.id.nav_home);
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

    private void showBadMomentsSplash() {
        Intent intent = new Intent(Dashboard.this, badMoments.class);
        startActivity(intent);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Dashboard.this, Breathing.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    private void showGoodMomentsSplash() {
        Intent intent = new Intent(Dashboard.this, goodMoments.class);
        startActivity(intent);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Dashboard.this, Dashboard.class));
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}