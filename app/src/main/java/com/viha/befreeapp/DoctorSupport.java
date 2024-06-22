package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import androidx.cardview.widget.CardView;

public class DoctorSupport extends AppCompatActivity {
    private CardView nearbyMedicalFacilitiesCard, symptomCheckerCard, appointmentsCard, crisisSupportCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_support);
        // Initialize the CardView elements
        nearbyMedicalFacilitiesCard = findViewById(R.id.Nearby_Medical_Faci);
        symptomCheckerCard = findViewById(R.id.Symptom_Checker);
        appointmentsCard = findViewById(R.id.Appointments);
        crisisSupportCard = findViewById(R.id.Crisis_Support);

        // Set click listeners for each CardView
        nearbyMedicalFacilitiesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNearbyMedicalFacilities();
            }
        });

        symptomCheckerCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSymptomChecker();
            }
        });

        appointmentsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppointments();
            }
        });

        crisisSupportCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCrisisSupport();
            }
        });
    }

    public void openNearbyMedicalFacilities() {
        Intent intent = new Intent(this, NearbyMedicalFacilities.class);
        startActivity(intent);
    }

    public void openSymptomChecker() {
        Intent intent = new Intent(this, SymptomChecker.class);
        startActivity(intent);
    }

    public void openAppointments() {
        Intent intent = new Intent(this, DoctorAppointments.class);
        startActivity(intent);
    }

    public void openCrisisSupport() {
        Intent intent = new Intent(this, CrisisSupport.class);
        startActivity(intent);
    }

}