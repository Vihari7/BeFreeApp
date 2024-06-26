package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NearbyMedicalFacility extends AppCompatActivity {
    private EditText editTextStart;
    private EditText editTextEnd;
    private Button btnGetPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_medical_facility);
        editTextStart = findViewById(R.id.editTextStart);
        editTextEnd = findViewById(R.id.editTextEnd);
        btnGetPath = findViewById(R.id.btnGetPath);


        btnGetPath.setOnClickListener(v ->{
            String startingPoint = editTextStart.getText().toString();
            String endPoint =editTextEnd.getText().toString();

            if(startingPoint.equals("")|| endPoint.equals("")){
                Toast.makeText(this,"Please enter starting location $ detination",
                        Toast.LENGTH_SHORT).show();
            }else{
                getPath(startingPoint,endPoint);
            }
        });
    }

    private void getPath(String startingPoint, String endPoint) {
        try{
            Uri uri = Uri.parse("https://www.google.com/maps/dir/" + startingPoint + "&destination=" + endPoint);
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setPackage("com.google.android.apps.maps");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }catch (ActivityNotFoundException exception){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps&hl=en&pli=1");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }


}