package com.example.projectbeacon.Services;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.projectbeacon.R;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import static com.example.projectbeacon.Services.App.CHANNEL_ID;

public class NotificationService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        String msg = intent.getStringExtra("message");

//        Intent notificationIntent = new Intent(this, MainActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this,
//                0, notificationIntent, 0);

//        Intent click = new Intent(this,MainActivity.class);
//
//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        String t = prefs.getString("title", "No title defined");
//        String m = prefs.getString("message", "No title defined");
//        boolean isActive = prefs.getBoolean("isActive", false);
//        Log.d("TEST","Noti prefs");
//        Log.d("TEST","-----"+t);
//        Log.d("TEST","-----"+m);
//        Log.d("TEST","-----"+isActive);
//
//
//        click.putExtra("title",t);
//        click.putExtra("message",m);
//        click.putExtra("isActive",isActive);
//
//        try {
//            pendingIntent.send(this,0,click);
//        } catch (PendingIntent.CanceledException e) {
//            e.printStackTrace();
//        }

        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(input)
                .setContentText(msg)
                .setSmallIcon(R.drawable.ic_chat_bubble_outline_green_24dp)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
//                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        startForeground(1, notification);

        //do heavy work on a background thread
//        stopSelf();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        //do something you want
        //stop service
        this.stopSelf();
    }
    
}
