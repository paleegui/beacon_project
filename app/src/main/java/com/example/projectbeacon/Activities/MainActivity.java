package com.example.projectbeacon.Activities;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.projectbeacon.Activities.login.ClinomaniaLogin;
import com.example.projectbeacon.R;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;

public class MainActivity extends AppCompatActivity {
    protected static final String TAG = "Main Check Permission";
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Beacon.setHardwareEqualityEnforced(true); ///--------> this make beacon different
        setContentView(R.layout.activity_main);

        if(BeaconManager.getInstanceForApplication(this).checkAvailability()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //start a new activity
                    startActivity(new Intent(MainActivity.this, Main_navigation.class));
                }
            }, 3000); // 1 second.
        }

    }
}
