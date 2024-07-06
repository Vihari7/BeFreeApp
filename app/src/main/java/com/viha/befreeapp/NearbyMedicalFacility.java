package com.viha.befreeapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class NearbyMedicalFacility extends AppCompatActivity {

    private EditText editTextStart, editTextEnd;
    private Button btnGetPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_medical_facility);

        editTextStart = findViewById(R.id.editTextStart);
        editTextEnd = findViewById(R.id.editTextEnd);
        btnGetPath = findViewById(R.id.btnGetPath);

        btnGetPath.setOnClickListener(v -> {
            String start = editTextStart.getText().toString();
            String end = editTextEnd.getText().toString();
            Intent intent = new Intent(NearbyMedicalFacility.this, MapActivity.class);
            intent.putExtra("start", start);
            intent.putExtra("end", end);
            startActivity(intent);
        });
    }
}
