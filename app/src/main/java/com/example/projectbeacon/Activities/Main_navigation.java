package com.example.projectbeacon.Activities;

import androidx.annotation.NonNull;

import com.example.projectbeacon.Services.BeaconMonitoringService;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.projectbeacon.Activities.fragment.ListBeacon_fragment;
import com.example.projectbeacon.Activities.fragment.Location_fragment;
import com.example.projectbeacon.R;

import org.altbeacon.beacon.BeaconManager;

public class Main_navigation extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    protected static final String TAG = "Main Check Permission";

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private ListBeacon_fragment listBeacon_fragment;
    private Location_fragment location_fragment;

    private BeaconManager beaconManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_navigation);
        verifyBluetooth(); //------> Verify Bluetooth


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("This app needs location access");
                builder.setMessage("Please grant location access so this app can detect beacons in the background.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                    @TargetApi(23)
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                                PERMISSION_REQUEST_COARSE_LOCATION);
                    }

                });
                builder.show();
            }
        }

        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1234);

        mMainFrame = (FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        listBeacon_fragment = new ListBeacon_fragment();
        location_fragment = new Location_fragment();

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_location :

//                        beaconManager = BeaconManager.getInstanceForApplication(getApplicationContext());
//                        startService(new Intent(getApplicationContext(), BeaconMonitoringService.class));
//                        Log.d(TAG, "MoniterService Start");
                        mMainNav.setItemBackgroundResource(R.color.color_bathroom);
                        setFragment(location_fragment);
                        return true;

                    case R.id.nav_beacon :
                        mMainNav.setItemBackgroundResource(R.color.color_bathroom);
                        setFragment(listBeacon_fragment);
                        return true;

                    case R.id.nav_account :
                        mMainNav.setItemBackgroundResource(R.color.color_bathroom);
                        return true;

                        default:
                            return false;
                }
            }
        });
    }

    private void setFragment(Fragment fragment){

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_COARSE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(TAG, "coarse location permission granted");
                } else {
                    final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Functionality limited");
                    builder.setMessage("Since location access has not been granted, this app will not be able to discover beacons when in the background.");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                        @Override
                        public void onDismiss(DialogInterface dialog) {
                        }

                    });
                    builder.show();
                }
                return;
            }
        }
    }

    private void verifyBluetooth() {
        try {
            if (!BeaconManager.getInstanceForApplication(this).checkAvailability()) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Bluetooth not enabled");
                builder.setMessage("Please enable bluetooth in settings and restart this application.");
                builder.setPositiveButton(android.R.string.ok, null);
                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    @Override
                    public void onDismiss(DialogInterface dialog) {
                        finish();
                        System.exit(0);
                    }
                });
                builder.show();
            }
        }
        catch (RuntimeException e) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Bluetooth LE not available");
            builder.setMessage("Sorry, this device does not support Bluetooth LE.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {

                @Override
                public void onDismiss(DialogInterface dialog) {
                    finish();
                    System.exit(0);
                }

            });
            builder.show();
        }
    }


}
