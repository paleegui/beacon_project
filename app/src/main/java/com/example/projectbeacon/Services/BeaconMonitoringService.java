package com.example.projectbeacon.Services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.example.projectbeacon.Activities.MainActivity;
import com.example.projectbeacon.Activities.Select_beacon;
import com.example.projectbeacon.Activities.fragment.Location_fragment;
import com.example.projectbeacon.R;

import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.Region;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class BeaconMonitoringService extends Service implements BeaconConsumer {
    private static final String TAG = "BEACON";
    private static final String UUID = "1234567-1234-1234-1234-123456789012";
    private static final Region Room = new Region("mcd1", Identifier.parse(UUID), Identifier.fromInt(1), null);
    private static final Region Kitchen = new Region("mcd2", Identifier.parse(UUID), Identifier.fromInt(2), null);
    private BeaconManager beaconManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "Beacons monitoring service started");
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "Beacons monitoring service destroyed");
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setMonitorNotifier(new MonitorNotifier() {
            @Override
            public void didEnterRegion(Region region) {
                Log.d(TAG, "Entered");
                if (region.getId2() == Identifier.fromInt(1)) {
                    postNotification("Room", "Entered");
                    intent("1-1");
                } else {
                    postNotification("Kitchen", "Entered");
                    intent("2-1");

                }
            }

            @Override
            public void didExitRegion(Region region) {
                Log.d(TAG, "Left");
                if (region.getId2() == Identifier.fromInt(1)) {
                    postNotification("Room", "Left");
                    intent("1-2");
                } else {
                    postNotification("Kitchen", "Left");
                    intent("2-2");
                }

            }

            @Override
            public void didDetermineStateForRegion(int state, Region region) {
                Log.d(TAG, "Don't know what it's useful for" + state);
            }
        });

        try {
            Log.d(TAG, "Service ready");
            beaconManager.startMonitoringBeaconsInRegion(Room);
            beaconManager.startMonitoringBeaconsInRegion(Kitchen);
        } catch (RemoteException e) {
            Log.e(TAG, "Cannot start ranging", e);
        }
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStart Start");

        Location_fragment app = (Location_fragment) getApplication();

        beaconManager = getApplicationContext()
        beaconManager.setBackgroundScanPeriod(1100l);
        beaconManager.setBackgroundBetweenScanPeriod(10000l);

        beaconManager.getBeaconParsers ().add ( new BeaconParser().setBeaconLayout (
                "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24" ) );
        beaconManager.bind(this);

        Log.d(TAG, "onStart End");

        Notification noti = new Notification.Builder(this)
                .setContentTitle("Started")
                .setContentText("Here we go")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(2);
        mNotificationManager.notify(1, noti);


        return START_STICKY;
    }

    private void postNotification(String room, String action) {
        Intent notificationIntent = new Intent(BeaconMonitoringService.this, Location_fragment.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(BeaconMonitoringService.this, 0,
                notificationIntent, 0);

        Notification noti = new Notification.Builder(BeaconMonitoringService.this)
                .setContentTitle(room)
                .setContentText(action)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(intent)
                .build();


        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(2);
        mNotificationManager.notify(1, noti);
    }


    private void intent (String code){
        Intent intent = new Intent("statechanged");
        intent.putExtra("info", code);
        LocalBroadcastManager.getInstance(BeaconMonitoringService.this).sendBroadcast(intent);
    }
}
