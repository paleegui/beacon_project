package com.example.projectbeacon.Services;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Build;
import android.util.Log;

import com.example.projectbeacon.Activities.Main_navigation;
import com.example.projectbeacon.Activities.Select_beacon;
import com.example.projectbeacon.Activities.fragment.ListBeacon_fragment;
import com.example.projectbeacon.Activities.fragment.Location_fragment;
import com.example.projectbeacon.R;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.Region;
import org.altbeacon.beacon.powersave.BackgroundPowerSaver;
import org.altbeacon.beacon.startup.BootstrapNotifier;
import org.altbeacon.beacon.startup.RegionBootstrap;

import java.util.ArrayList;

public class ScannerService extends Application implements BootstrapNotifier {
    private static final String TAG = "BeaconReferenceApp";
    private static final String UUID = "1234567-1234-1234-1234-123456789012";
    private RegionBootstrap regionBootstrap;
    private BackgroundPowerSaver backgroundPowerSaver;
    private boolean haveDetectedBeaconsSinceBoot = false;
    private Select_beacon select_beacon = null;
    private String cumulativeLog = "";

    public void onCreate() {
        super.onCreate();
        Beacon.setHardwareEqualityEnforced(true);
        BeaconManager beaconManager = org.altbeacon.beacon.BeaconManager.getInstanceForApplication(this);
        // By default the AndroidBeaconLibrary will only find AltBeacons.  If you wish to make it
        // find a different type of beacon, you must specify the byte layout for that beacon's
        // advertisement with a line like below.  The example shows how to find a beacon with the
        // same byte layout as AltBeacon but with a beaconTypeCode of 0xaabb.  To find the proper
        // layout expression for other beacon types, do a web search for "setBeaconLayout"
        // including the quotes.

        //****set type for macthing****
        beaconManager.getBeaconParsers().clear();
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24")); //ibeacon
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout("m:0-1=5900,i:2-2,i:3-4,p:5-5"));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.ALTBEACON_LAYOUT));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_UID_LAYOUT));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_TLM_LAYOUT));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.EDDYSTONE_URL_LAYOUT));
        beaconManager.getBeaconParsers().add(new BeaconParser().setBeaconLayout(BeaconParser.URI_BEACON_LAYOUT));
        //**********
        beaconManager.setDebug(true);


        // Uncomment the code below to use a foreground service to scan for beacons. This unlocks
        // the ability to continually scan for long periods of time in the background on Andorid 8+
        // in exchange for showing an icon at the top of the screen and a always-on notification to
        // communicate to users that your app is using resources in the background.


        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.drawable.beacon_icon);
        builder.setContentTitle("Scanning for Beacons");
        Intent intent = new Intent(this, Select_beacon.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("My Notification Channel ID",
                    "My Notification Name", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("My Notification Channel Description");
            NotificationManager notificationManager = (NotificationManager) getSystemService(
                    Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
            builder.setChannelId(channel.getId());
        }
        beaconManager.enableForegroundServiceScanning(builder.build(), 456);

        // For the above foreground scanning service to be useful, you need to disable
        // JobScheduler-based scans (used on Android 8+) and set a fast background scan
        // cycle that would otherwise be disallowed by the operating system.
        //
        beaconManager.setEnableScheduledScanJobs(false);
        beaconManager.setBackgroundBetweenScanPeriod(10000l);
        beaconManager.setBackgroundScanPeriod(1100);


        Log.d(TAG, "setting up background monitoring for beacons and power saving");
        // wake up the app when a beacon is seen
        Region region = new Region("backgroundRegion", null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);


//        final Region Room = new Region("mcd1", Identifier.parse(UUID), Identifier.fromInt(1), null);
//        final Region Kitchen = new Region("mcd2", Identifier.parse(UUID), Identifier.fromInt(2), null);
        //////////
//        Region region1 = new Region("room1",
//                Identifier.parse(UUID),Identifier.fromInt(1) , null);
//        Region region2 = new Region("room2",
//                Identifier.parse(UUID), Identifier.fromInt(2), null);
//        ArrayList<Region> regions = new ArrayList<Region>();
//        regions.add(region1);
//        regions.add(region2);
//        regionBootstrap = new RegionBootstrap(this, regions);
//////////////////




        // simply constructing this class and holding a reference to it in your custom Application
        // class will automatically cause the BeaconLibrary to save battery whenever the application
        // is not visible.  This reduces bluetooth power usage by about 60%
        backgroundPowerSaver = new BackgroundPowerSaver(this);

        // If you wish to test beacon detection in the Android Emulator, you can use code like this:
        // BeaconManager.setBeaconSimulator(new TimedBeaconSimulator() );
        // ((TimedBeaconSimulator) BeaconManager.getBeaconSimulator()).createTimedSimulatedBeacons();

    }

    public void disableMonitoring() {
        if (regionBootstrap != null) {
            regionBootstrap.disable();
            regionBootstrap = null;
        }
    }
    public void enableMonitoring() {
//        Region region1 = new Region("backgroundRegion1",
//                Identifier.parse("5A4BCFCE-174E-4BAC-A814-092E77F6B7E5"), null, null);
//        Region region2 = new Region("backgroundRegion2",
//                Identifier.parse("74278BDA-B644-4520-8F0C-720EAF059935"), null, null);
//        ArrayList<Region> regions = new ArrayList<Region>();
//        regions.add(region1);
//        regions.add(region2);
//
//        regionBootstrap = new RegionBootstrap(this, regions);
//=========================================================================
        Region region = new Region("backgroundRegion",
                null, null, null);
        regionBootstrap = new RegionBootstrap(this, region);
    }

    @Override
    public void didEnterRegion(Region arg0) {
        // In this example, this class sends a notification to the user whenever a Beacon
        // matching a Region (defined above) are first seen.
        Log.d(TAG, "did enter region.");
        if (!haveDetectedBeaconsSinceBoot) {
            Log.d(TAG, "auto launching MainActivity");
            sendNotification();
            // The very first time since boot that we detect an beacon, we launch the
            // MainActivity
//            Intent intent = new Intent(this, Select_beacon.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            // Important:  make sure to add android:launchMode="singleInstance" in the manifest
            // to keep multiple copies of this activity from getting created if the user has
            // already manually launched the app.
//            this.startActivity(intent);
            haveDetectedBeaconsSinceBoot = true;
        } else {
            if (select_beacon != null) {
                // If the Monitoring Activity is visible, we log info about the beacons we have
                // seen on its display
                logToDisplay("I see a beacon again" );
            } else {
                // If we have already seen beacons before, but the monitoring activity is not in
                // the foreground, we send a notification to the user on subsequent detections.
                Log.d(TAG, "Sending notification.");
                sendNotification();
            }
        }


    }

    @Override
    public void didExitRegion(Region region) {
        logToDisplay("I no longer see a beacon.");
    }

    @Override
    public void didDetermineStateForRegion(int state, Region region) {
        logToDisplay("Current region state is: " + (state == 1 ? "INSIDE" : "OUTSIDE ("+state+")"));
    }

    private void sendNotification() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setContentTitle("Beacon Reference Application")
                        .setContentText("An beacon is nearby.")
                        .setSmallIcon(R.drawable.beacon_icon); //icon notification

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntent(new Intent(this, ListBeacon_fragment.class));
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        builder.setContentIntent(resultPendingIntent);
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(1, builder.build());
    }

    public void setMonitoringActivity(Select_beacon activity) {
        this.select_beacon = activity;
    }

    private void logToDisplay(String line) {
        cumulativeLog += (line + "\n");
        if (this.select_beacon != null) {
            //เมื่อมีการเปลี่ยนแปลง
            //this.ListBeaconActivity.updateLog(cumulativeLog);
        }
    }

    public String getLog() {
        return cumulativeLog;
    }

    private void postNotification(String room, String action) {
        Intent notificationIntent = new Intent(ScannerService.this, Main_navigation.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(ScannerService.this, 0,
                notificationIntent, 0);

        Notification noti = new Notification.Builder(ScannerService.this)
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
        LocalBroadcastManager.getInstance(ScannerService.this).sendBroadcast(intent);
    }

}
