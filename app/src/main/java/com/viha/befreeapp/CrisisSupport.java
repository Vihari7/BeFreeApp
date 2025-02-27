package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.net.Uri;

public class CrisisSupport extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crisis_support);

        Button btnCallHelpline = findViewById(R.id.btnCallHelpline);
        Button btnVisitWebsite = findViewById(R.id.btnVisitWebsite);
        btnCallHelpline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callHelpline("1926"); // National Mental Health Helpline Number
            }
        });


        btnVisitWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite("https://nimh.health.gov.lk/en/1926-national-mental-health-helpline/#:~:text=1926%20%2D%20National%20Mental%20Health%20Helpline,of%20Mental%20Health%2C%20Sri%20Lanka"); // website URL
            }
        });
    }

    private void callHelpline(String phoneNumber) {
        Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:" + phoneNumber));
        startActivity(callIntent);
    }


    private void openWebsite(String url) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}