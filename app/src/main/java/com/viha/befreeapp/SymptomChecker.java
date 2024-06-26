package com.viha.befreeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
public class SymptomChecker extends AppCompatActivity {
    private EditText symptomsEditText;
    private Button checkSymptomsButton;
    private TextView resultsTextView;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_checker);

        symptomsEditText = findViewById(R.id.symptoms_edit);
        checkSymptomsButton = findViewById(R.id.button_check_symptoms);
        resultsTextView = findViewById(R.id.results_text);

        databaseReference = FirebaseDatabase.getInstance().getReference("symptoms");

        checkSymptomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSymptoms();
            }
        });
    }

    private void checkSymptoms() {
        String symptoms = symptomsEditText.getText().toString().trim();

        if (TextUtils.isEmpty(symptoms)) {
            Toast.makeText(this, "Please enter your symptoms", Toast.LENGTH_SHORT).show();
            return;
        }

        resultsTextView.setVisibility(View.VISIBLE);
        databaseReference.child(symptoms.toLowerCase()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String advice = snapshot.getValue(String.class);
                    resultsTextView.setText(advice);
                } else {
                    resultsTextView.setText("No matching symptoms found.");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(SymptomChecker.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}