package com.viha.befreeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Notes extends AppCompatActivity {
    private EditText noteEditText;
    private CalendarView calendarView;
    private Button saveButton, viewHistoryButton;
    private Button colorRedButton, colorGreenButton, colorBlueButton;
    private String selectedDate;
    private Map<String, String> notesMap;
    private DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        noteEditText = findViewById(R.id.noteEditText);
        calendarView = findViewById(R.id.calendarView);
        saveButton = findViewById(R.id.saveButton);
        viewHistoryButton = findViewById(R.id.viewHistoryButton);
        colorRedButton = findViewById(R.id.colorRedButton);
        colorGreenButton = findViewById(R.id.colorGreenButton);
        colorBlueButton = findViewById(R.id.colorBlueButton);

        notesMap = new HashMap<>();
        databaseReference = FirebaseDatabase.getInstance().getReference("notes");

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "-" + (month + 1) + "-" + year;
                loadNoteForDate(selectedDate);
            }
        });

        saveButton.setOnClickListener(v -> {
            saveNoteForDate(selectedDate, noteEditText.getText().toString());
        });

        viewHistoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(Notes.this, NoteHistory.class);
            startActivity(intent);
        });

        colorRedButton.setOnClickListener(v -> changeEditTextTextColor(Color.RED));
        colorGreenButton.setOnClickListener(v -> changeEditTextTextColor(Color.GREEN));
        colorBlueButton.setOnClickListener(v -> changeEditTextTextColor(Color.BLUE));
    }

    private void changeEditTextTextColor(int color) {
        Spannable spannable = new SpannableString(noteEditText.getText());
        spannable.setSpan(new ForegroundColorSpan(color), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        noteEditText.setText(spannable);
    }

    private void loadNoteForDate(String date) {
        if (notesMap.containsKey(date)) {
            noteEditText.setText(notesMap.get(date));
        } else {
            noteEditText.setText("");
        }
    }

    private void saveNoteForDate(String date, String note) {
        if (date != null && !date.isEmpty()) {
            if (note != null && !note.trim().isEmpty()) {
                notesMap.put(date, note);
                databaseReference.child(date).setValue(note);
                Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Note cannot be empty", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Please select a date", Toast.LENGTH_SHORT).show();
        }
    }

}