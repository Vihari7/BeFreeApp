package com.viha.befreeapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class NoteHistory extends AppCompatActivity {
    private ListView noteHistoryListView;
    private DatabaseReference databaseReference;
    private List<String> noteHistoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_history);

        noteHistoryListView = findViewById(R.id.noteHistoryListView);
        databaseReference = FirebaseDatabase.getInstance().getReference("notes");
        noteHistoryList = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                noteHistoryList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String date = snapshot.getKey();
                    String note = snapshot.getValue(String.class);
                    noteHistoryList.add(date + ": " + note);
                }
                NotesAdapter adapter = new NotesAdapter(NoteHistory.this, noteHistoryList);
                noteHistoryListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle database error
                Toast.makeText(NoteHistory.this, "Failed to retrieve notes.", Toast.LENGTH_SHORT).show();
            }
        });

        // Handle item click to edit note
        noteHistoryListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = noteHistoryList.get(position);
            String[] parts = selectedItem.split(": ");
            String date = parts[0];
            String noteContent = parts[1];
            showEditNoteDialog(date, noteContent);
        });
    }

    // Custom ArrayAdapter to display notes with different text colors and support editing
    private class NotesAdapter extends ArrayAdapter<String> {

        private List<String> notes;

        NotesAdapter(AppCompatActivity context, List<String> notes) {
            super(context, android.R.layout.simple_list_item_1, notes);
            this.notes = notes;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            // Extract the note text and split it into date and note content
            String note = notes.get(position);
            String[] parts = note.split(": ");
            String date = parts[0];
            String noteContent = parts[1];

            // Create a SpannableString to apply color to the date part
            SpannableString spannable = new SpannableString(date + ": " + noteContent);
            spannable.setSpan(new ForegroundColorSpan(getResources().getColor(android.R.color.black)), 0, date.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            // Set the SpannableString to the TextView
            TextView textView = (TextView) view.findViewById(android.R.id.text1);
            textView.setText(spannable);

            return view;
        }
    }

    // Method to show dialog for editing note
    private void showEditNoteDialog(String date, String currentNote) {
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_edit_note, null);
        EditText editText = dialogView.findViewById(R.id.editText);
        editText.setText(currentNote);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setTitle("Edit Your thoughts as you wish")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String updatedNote = editText.getText().toString();
                        saveEditedNoteToFirebase(date, updatedNote);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    // Method to save edited note to Firebase
    private void saveEditedNoteToFirebase(String date, String updatedNote) {
        databaseReference.child(date).setValue(updatedNote)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(NoteHistory.this, "Note updated successfully", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(NoteHistory.this, "Failed to update note", Toast.LENGTH_SHORT).show();
                });
    }
}