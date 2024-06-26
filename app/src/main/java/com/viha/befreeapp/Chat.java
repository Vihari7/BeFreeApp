package com.viha.befreeapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Chat extends AppCompatActivity {
    private String userId;
    private String chatPartnerId;
    private DatabaseReference messagesRef;
    private ListView listViewMessages;
    private EditText editTextMessage;
    private Button buttonSend;
    private MessageAdapter messageAdapter;
    private List<ChatMessage> messageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // Initialize views
        listViewMessages = findViewById(R.id.listViewMessages);
        editTextMessage = findViewById(R.id.editTextMessage);
        buttonSend = findViewById(R.id.buttonSend);

        // Initialize message list and adapter
        messageList = new ArrayList<>();
        messageAdapter = new MessageAdapter(this, R.layout.message_item, messageList);
        listViewMessages.setAdapter(messageAdapter);

        // Get user ID and chat partner ID (you should pass these through intents)
        userId = getIntent().getStringExtra("userId");
        chatPartnerId = getIntent().getStringExtra("chatPartnerId");

        // Reference to the messages node in Firebase
        messagesRef = FirebaseDatabase.getInstance().getReference("messages");

        // Load chat history
        loadChatHistory();

        // Send button click listener
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void loadChatHistory() {
        messagesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                ChatMessage message = dataSnapshot.getValue(ChatMessage.class);
                if (message != null && userId != null && chatPartnerId != null) {
                    if ((message.getSenderId().equals(userId) && message.getReceiverId().equals(chatPartnerId)) ||
                            (message.getSenderId().equals(chatPartnerId) && message.getReceiverId().equals(userId))) {
                        messageList.add(message);
                        messageAdapter.notifyDataSetChanged();
                        listViewMessages.smoothScrollToPosition(messageList.size() - 1); // Scroll to the bottom
                    }
                }
            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle changed messages if needed
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                // Handle removed messages if needed
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {
                // Handle moved messages if needed
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }
    private void sendMessage() {
        // Logic to send message to Firebase database
        String messageText = editTextMessage.getText().toString().trim();
        if (!messageText.isEmpty()) {
            long timestamp = System.currentTimeMillis();
            ChatMessage message = new ChatMessage(userId, chatPartnerId, messageText, timestamp);
            messagesRef.push().setValue(message);
            editTextMessage.setText("");
        }
    }
}