package com.viha.befreeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Notes extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText noteEditText;
    private Button saveButton;
    private Button viewHistoryButton;

    private String selectedDate;
    private DatabaseReference notesRef;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        calendarView = findViewById(R.id.calendarView);
        noteEditText = findViewById(R.id.noteEditText);
        saveButton = findViewById(R.id.saveButton);
        viewHistoryButton = findViewById(R.id.viewHistoryButton);

        // Get username from intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        // Initialize Firebase reference
        notesRef = FirebaseDatabase.getInstance().getReference("notes").child(username);

        // Get selected date from CalendarView
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = year + "-" + (month + 1) + "-" + dayOfMonth;
                loadNote();
            }
        });

        // Save note to Firebase
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveNote();
            }
        });

        // View note history
        viewHistoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notes.this, NoteHistory.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        });
    }

    private void loadNote() {
        notesRef.child(selectedDate).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String note = snapshot.getValue(String.class);
                    noteEditText.setText(note);
                } else {
                    noteEditText.setText("");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Notes.this, "Error loading note", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void saveNote() {
        String note = noteEditText.getText().toString();
        if (!note.isEmpty() && selectedDate != null) {
            notesRef.child(selectedDate).setValue(note);
            Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Please select a date and enter a note", Toast.LENGTH_SHORT).show();
        }
    }
}
