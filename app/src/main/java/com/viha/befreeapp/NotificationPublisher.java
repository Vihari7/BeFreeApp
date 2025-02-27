package com.viha.befreeapp;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
public class NotificationPublisher  extends BroadcastReceiver {

    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION = "notification";

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        int id = intent.getIntExtra(NOTIFICATION_ID, 0);
        Notification notification = intent.getParcelableExtra(NOTIFICATION);

        if (notification != null) {
            notificationManager.notify(id, notification);
        }
    }
}