package com.viha.befreeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.cardview.widget.CardView;

public class MindfulActivities extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mindful_activities);

        CardView mindfulBreathingCard = findViewById(R.id.breathButton);
        CardView bodyScanMeditationCard = findViewById(R.id.bodyscanButton);
        CardView matchingWalkCard = findViewById(R.id.walkButton);
        CardView kindMedtationCard = findViewById(R.id.kindMedButton);

        // Link to pages of mindful activity categories
        mindfulBreathingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, BreathInstruction_MindAct.class);
                startActivity(intent);
            }
        });

        bodyScanMeditationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, Meditation.class);
                startActivity(intent);
            }
        });

        kindMedtationCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, KindnessMeditation.class);
                startActivity(intent);
            }
        });

        matchingWalkCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MindfulActivities.this, MindfulWalking.class);
                startActivity(intent);
            }
        });

        setupClickListeners(mindfulBreathingCard, BreathInstruction_MindAct.class);
        setupClickListeners(bodyScanMeditationCard, Meditation.class);
        setupClickListeners(matchingWalkCard, MindfulWalking.class);
        setupClickListeners(kindMedtationCard, KindnessMeditation.class);
    }

    private void setupClickListeners(CardView cardView, Class<?> cls) {
        cardView.setOnClickListener(view -> {
            // Start activity when card is clicked
            Intent intent = new Intent(MindfulActivities.this, cls);
            startActivity(intent);

            // Schedule notification
            scheduleNotification(cls);
        });
    }

    private void scheduleNotification(Class<?> cls) {
        long delay = 10 * 1000; // 10 seconds delay (for testing)
        // Get the AlarmManager service to schedule the notification
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        // Create an intent to trigger the NotificationPublisher BroadcastReceiver
        Intent intent = new Intent(this, NotificationPublisher.class);
        intent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        intent.putExtra(NotificationPublisher.NOTIFICATION, createNotification(cls));
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + delay, pendingIntent);
    }

    //create and return a notification
    private Notification createNotification(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        String channelId = "default_channel_id";
        Notification.Builder builder;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create Notification Channel
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            NotificationChannel channel = new NotificationChannel(channelId, "Default Channel", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);

            builder = new Notification.Builder(this, channelId);
        } else {

            builder = new Notification.Builder(this);
        }
        // Build the notification
        builder
                .setSmallIcon(R.drawable.icon_notification)
                .setContentTitle("Mindful Activity Reminder")
                .setContentText("Tap to start " + cls.getSimpleName())
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        return builder.build();
    }
}