package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class BreathInstruction_MindAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breath_instruction_mind);

        //navigate to breathing.java
        Button navToBreathButton = findViewById(R.id.navToBreathButton);
        navToBreathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BreathInstruction_MindAct.this, Breathing.class);
                startActivity(intent);
            }
        });
    }
}