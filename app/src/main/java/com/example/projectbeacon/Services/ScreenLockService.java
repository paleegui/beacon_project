package com.example.projectbeacon.Services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ScreenLockService extends Service {

    BroadcastReceiver mReceiver;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TEST", "ScreenLockService extends Service---onCreate");
        // register receiver that handles screen on and screen off logic
        IntentFilter filter = new IntentFilter(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        mReceiver = new ScreenLockReceiver();
        registerReceiver(mReceiver, filter);
    }


    @Override
    public void onDestroy() {

        unregisterReceiver(mReceiver);
        Log.d("TEST", "onDestroy Reciever Called");

        super.onDestroy();
    }


    @Override
    public void onStart(Intent intent, int startId) {
        Log.d("TEST", "ScreenLockService extends Service---onStart");

        boolean screenOn = intent.getBooleanExtra("screen_state", false);
        if ( !screenOn) {
            Log.d("TEST", "screenON------------------Called");
//            Toast.makeText(getApplicationContext(), "Awake", Toast.LENGTH_LONG)
//                    .show();
        } else {
            Log.d("TEST", "screenOFF----------------Called");
//            Toast.makeText(getApplicationContext(), "Sleep",
//                    Toast.LENGTH_LONG)
//                    .show();
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TEST", "ScreenLockService extends Service---onStartCommand");
        return Service.START_STICKY;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        //do something you want
        //stop service
        this.stopSelf();
    }
}