package com.example.projectbeacon.Services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.projectbeacon.Database.SQLite.OffScreenTimeSqlite;
import com.example.projectbeacon.Model.OffScreenTime;

import java.util.Calendar;
import java.util.Date;

import androidx.core.content.ContextCompat;

import static android.content.Context.MODE_PRIVATE;


public class ScreenLockReceiver extends BroadcastReceiver {


    private boolean screenOff;
    private OffScreenTimeSqlite offSQL;
    private String username = "noey";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("TEST", "ScreenLockReceiver");
        offSQL = new OffScreenTimeSqlite(context);

        String msg = "";
        switch(intent.getAction()) {
            case Intent.ACTION_SCREEN_OFF:
                screenOff = true;
                msg = "screen lock";
                Log.d("TEST", "screenLog------------screen off");
                break;
            case Intent.ACTION_SCREEN_ON:
                screenOff = false;
                msg = "screen unlock";
                Log.d("TEST", "screenLog------------screen on");
                break;
            case Intent.ACTION_USER_PRESENT:
                screenOff = false;
                msg = "screen user";
                Log.d("TEST", "screenLog------------screen user present");
                break;

            default:
                msg = "not defined";
                Log.d("TEST", "screenLog------------screen not defined");

        }
//        Calendar cal = Calendar.getInstance();
//        Date d = cal.getTime();
//        OffScreenTime time = new OffScreenTime(username, d,screenOff);
//        offSQL.insertOffSTDetails(time);

//        String title = "Screen Status from ScreenLockReceiver";
//
//        SharedPreferences prefs = context.getSharedPreferences("prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//
//        editor.putString("title", title);
//        editor.putString("message", msg);
//        editor.putBoolean("isActive", !screenOff);
//        editor.apply();

//        Intent serviceIntent = new Intent(context, NotificationService.class);
//        serviceIntent.putExtra("inputExtra", title);
//        serviceIntent.putExtra("message", msg);
//        ContextCompat.startForegroundService(context, serviceIntent);

//        SharedPreferences prefs2 = context.getSharedPreferences("prefs", MODE_PRIVATE);
//        String t = prefs2.getString("title","Not defined title");
//        String m = prefs2.getString("message","Not defined message");
//        boolean b = prefs2.getBoolean("isActive", true);
//        Intent i = new Intent();
//        String t = i.getStringExtra("title");
//        String m = i.getStringExtra("message");
//        boolean b;
//        if (i.getBooleanExtra("isActive",false)) b = true;
//        else b = false;
//
//        Log.d("TEST","ScreenLockReceiver onReceive prefs");
//        Log.d("TEST","-----"+t);
//        Log.d("TEST","-----"+m);
//        Log.d("TEST","-----"+b);
    }

    public boolean getScreenOff(){
        return screenOff;
    }
}
