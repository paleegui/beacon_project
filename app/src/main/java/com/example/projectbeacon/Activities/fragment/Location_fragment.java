package com.example.projectbeacon.Activities.fragment;


import android.app.Application;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.projectbeacon.Activities.Main_navigation;
import com.example.projectbeacon.R;
import com.example.projectbeacon.Services.BeaconMonitoringService;
import com.example.projectbeacon.Services.BeaconNotification;
import com.example.projectbeacon.Services.BeaconService;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.Identifier;
import org.altbeacon.beacon.MonitorNotifier;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 */
public class Location_fragment extends Fragment implements BeaconConsumer {

    private BeaconManager beaconManager = null;

    private TextView title;
    private TextView time;
    private TextView hours;
    private TextView min;
    private TextView date;

    public Location_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //getting beaconManager instance (object) for Main Activity class
        beaconManager = BeaconManager.getInstanceForApplication(getActivity());

        // To detect proprietary beacons, you must add a line like below corresponding to your beacon
        // type.  Do a web search for "setBeaconLayout" to get the proper expression.
        beaconManager.getBeaconParsers().add(new BeaconParser().
                setBeaconLayout("m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24"));


        //Binding MainActivity to the BeaconService.
        beaconManager.bind(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_location_fragment, container, false);

        init(view);

        return view;
    }

    private void init(View view){
        title = (TextView)view.findViewById(R.id.titile_Room_location);
        time = (TextView)view.findViewById(R.id.time_location);
        hours = (TextView)view.findViewById(R.id.hours_location);
        min = (TextView)view.findViewById(R.id.min_location);
        date = (TextView)view.findViewById(R.id.date_location);
    }



    @Override
    public void onBeaconServiceConnect() {

          beaconManager.addMonitorNotifier(new MonitorNotifier() {
              @Override
              public void didEnterRegion(Region region) {
                  System.out.println("ENTER location ------------------->");
                  try {

                      //Tells the BeaconService to start looking for beacons that match the passed Region object
                      // , and providing updates on the estimated mDistance every seconds while beacons in the Region
                      // are visible.
                      beaconManager.startRangingBeaconsInRegion(region);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }

              @Override
              public void didExitRegion(Region region) {
                  System.out.println("EXIT location ----------------------->");
                  try {

                      //Tells the BeaconService to stop looking for beacons
                      // that match the passed Region object and providing mDistance
                      // information for them.
                      beaconManager.stopRangingBeaconsInRegion(region);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }
              /*
                 This override method will Determine the state for the device , whether device is in range
                of beacon or not , if yes then i = 1 and if no then i = 0
              */
              @Override
              public void didDetermineStateForRegion(int i, Region region) {
                  System.out.println( "I have just switched from seeing/not seeing beacons: "+i);
              }
          });

        //Specifies a class that should be called each time the BeaconService gets ranging data,
        // which is nominally once per second when beacons are detected.
          beaconManager.addRangeNotifier(new RangeNotifier() {
              /*
               This Override method tells us all the collections of beacons and their details that
               are detected within the range by device
             */
              @Override
              public void didRangeBeaconsInRegion(Collection<Beacon> collection, Region region) {
                  if (region.getId2() == Identifier.fromInt(1)) {
                      postNotification("Room", "Entered");
                      Log.d("TestRoom", "Room11111: ");
                      //intent("1-1");
                  } else {
                      postNotification("Kitchen", "Entered");
                      //intent("2-1");
                  }


                  try {
                      beaconManager.startMonitoringBeaconsInRegion(region);
                  } catch (RemoteException e) {
                      e.printStackTrace();
                  }
              }
          });

    }
    private void postNotification(String room, String action) {
        Intent notificationIntent = new Intent(getApplicationContext(), Main_navigation.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent = PendingIntent.getActivity(getApplicationContext(), 0,
                notificationIntent, 0);

        Notification noti = new Notification.Builder(getApplicationContext())
                .setContentTitle(room)
                .setContentText(action)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(intent)
                .build();
    }
    @Override
    public Context getApplicationContext() {
        return getActivity().getApplicationContext();
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {
        getActivity().unbindService(serviceConnection);
    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return getActivity().bindService(intent, serviceConnection, i);
    }
}
