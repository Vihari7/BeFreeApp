package com.viha.befreeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class NoteHistory extends AppCompatActivity {

    private ListView noteHistoryListView;
    private DatabaseReference notesRef;
    private String username;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> noteHistoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_history);

        noteHistoryListView = findViewById(R.id.noteHistoryListView);

        // Get username from intent
        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        // Initialize Firebase reference
        notesRef = FirebaseDatabase.getInstance().getReference("notes").child(username);

        loadNoteHistory();

        // Register context menu for list view
        registerForContextMenu(noteHistoryListView);

        // Set click listener for note items to edit
        noteHistoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedNote = noteHistoryList.get(position);
                String selectedNoteDate = extractNoteDate(selectedNote);
                String selectedNoteContent = extractNoteContent(selectedNote);

                // Show edit note dialog
                showEditNoteDialog(selectedNoteDate, selectedNoteContent);
            }
        });
    }

    private void loadNoteHistory() {
        notesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                noteHistoryList = new ArrayList<>();
                for (DataSnapshot dateSnapshot : snapshot.getChildren()) {
                    String date = dateSnapshot.getKey();
                    String note = dateSnapshot.getValue(String.class);
                    noteHistoryList.add(date + ": " + note);
                }

                adapter = new ArrayAdapter<>(NoteHistory.this, android.R.layout.simple_list_item_1, noteHistoryList);
                noteHistoryListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(NoteHistory.this, "Error loading note history", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Method to extract note date from note history string
    private String extractNoteDate(String noteHistoryItem) {
        return noteHistoryItem.split(": ")[0]; // Extract date from note history string
    }

    // Method to extract note content from note history string
    private String extractNoteContent(String noteHistoryItem) {
        return noteHistoryItem.split(": ")[1]; // Extract note content from note history string
    }

    // Method to show edit note dialog with custom layout
    private void showEditNoteDialog(final String noteDate, String noteContent) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_edit_note, null);
        builder.setView(dialogView);

        final EditText input = dialogView.findViewById(R.id.editTextNote);
        input.setText(noteContent); // Set initial text to current note content

        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String editedNote = input.getText().toString().trim();
                if (!editedNote.isEmpty()) {
                    editNoteInDatabase(noteDate, editedNote);
                } else {
                    Toast.makeText(NoteHistory.this, "Note cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

        // Apply custom background to the dialog window if needed
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.note_history_back);
    }

    // Method to edit note in Firebase database
    private void editNoteInDatabase(String noteDate, String editedNote) {
        notesRef.child(noteDate).setValue(editedNote);
        Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
    }

    // Create context menu for deleting notes
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.note_context_menu, menu);
    }

    // Handle context menu item selection
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.delete_note:
                String selectedNote = noteHistoryList.get(info.position);
                String selectedNoteDate = extractNoteDate(selectedNote);
                showDeleteConfirmationDialog(selectedNoteDate);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    // Method to show confirmation dialog for note deletion
    private void showDeleteConfirmationDialog(final String noteDate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this note?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteNoteByDate(noteDate);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Method to delete note by date
    private void deleteNoteByDate(String noteDate) {
        notesRef.child(noteDate).removeValue();
        Toast.makeText(this, "Note deleted", Toast.LENGTH_SHORT).show();
    }
}
