package com.example.projectbeacon.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.example.projectbeacon.Database.SQLite.OffScreenTimeSqlite;
import com.example.projectbeacon.Model.OffScreenTime;
import com.example.projectbeacon.R;
import com.example.projectbeacon.Services.NotificationService;
import com.example.projectbeacon.Services.ScreenLockService;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class GetInput extends AppCompatActivity {
    private String title = "Phone Status";
    private String msg = "init";
    private Intent service;
    private OffScreenTimeSqlite offSQL;

    private void startScreenService(){
        Context context = getApplicationContext();
        service = new Intent(context, ScreenLockService.class);
        context.startService(service);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//
//        editor.putString("title", "NULL");
//        editor.putString("message", "NULL");
//        editor.putBoolean("isActive", false);
    }

    public void startService() {
        Log.d("TEST", "MainActivity extends AppCompatActivity---startService");
        String title = "Screen Status from MainActivity";

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("title", title);
        editor.putString("message", msg);
        editor.putBoolean("isActive", true);
        editor.apply();

        Intent serviceIntent = new Intent(this, NotificationService.class);
        serviceIntent.putExtra("inputExtra", title);
        serviceIntent.putExtra("message", msg);
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    public void stopService() {
        Log.d("TEST", "MainActivity extends AppCompatActivity---stopService");
        Intent serviceIntent = new Intent(this, NotificationService.class);
        stopService(serviceIntent);
        if(service != null)
            stopService(service);

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String t = prefs.getString("title","Not defined title");
        String m = prefs.getString("message","Not defined message");
        boolean b = prefs.getBoolean("isActive", true);

        Log.d("TEST","MainActivity onDestroy prefs");
        Log.d("TEST","-----"+t);
        Log.d("TEST","-----"+m);
        Log.d("TEST","-----"+b);
    }

    @Override
    public void onDestroy() {
        Log.d("TEST", "MainActivity extends AppCompatActivity---onDestroy");

        super.onDestroy();
        msg = this.getIntent().getStringExtra("message");
//        startService();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String t = prefs.getString("title","Not defined title");
        String m = prefs.getString("message","Not defined message");
        boolean b = prefs.getBoolean("isActive", true);

        if(!t.equalsIgnoreCase("NULL") && !m.equalsIgnoreCase("NULL")){
            startScreenService();
        }

        Log.d("TEST","MainActivity onDestroy prefs");
        Log.d("TEST","-----"+t);
        Log.d("TEST","-----"+m);
        Log.d("TEST","-----"+b);
    }


    @Override
    public void onResume() {
        Log.d("TEST", "MainActivity extends AppCompatActivity---onResume");

        super.onResume();
//        startService();

        SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
        String t = prefs.getString("title","Not defined title");
        String m = prefs.getString("message","Not defined message");
        boolean b = prefs.getBoolean("isActive", true);

        Log.d("TEST","MainActivity onResume prefs");
        Log.d("TEST","-----"+t);
        Log.d("TEST","-----"+m);
        Log.d("TEST","-----"+b);

    }

}
