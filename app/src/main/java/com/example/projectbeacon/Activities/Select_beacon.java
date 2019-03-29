package com.example.projectbeacon.Activities;

import android.os.Bundle;
import android.os.RemoteException;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;

import com.example.projectbeacon.Beacon.TheBeacon;
import com.example.projectbeacon.R;
import com.example.projectbeacon.UI.RecycleViewAdapter_Select;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;

public class Select_beacon extends AppCompatActivity implements BeaconConsumer {

    BeaconManager beaconManager;
    RecyclerView recyclerView;
    RecycleViewAdapter_Select recycleViewAdapter_select;
    ArrayList<Beacon> beaconList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_beacon);


        beaconManager = BeaconManager.getInstanceForApplication(this);

        //UI Showing
        recyclerView = (RecyclerView)findViewById(R.id.select_recyclerViewID);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        beaconManager.bind(this);

    }


    @Override
    public void onBeaconServiceConnect() {
        RangeNotifier rangeNotifier = new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                beaconList = (ArrayList<Beacon>) beacons;
                if (beacons.size() > 0) {
                    for(Beacon beacon :beacons){
                        Log.d("Test","The Beacon "+beacon.toString()+ "is about:" + beacon.getDistance());
                        Log.d("Test11",""+beacon.getBluetoothAddress());
                    }
                    Log.d("Test","----------------------------------------------------");
                }

                recycleViewAdapter_select = new RecycleViewAdapter_Select(getApplicationContext(),beaconList);
                recyclerView.setAdapter(recycleViewAdapter_select);
                recycleViewAdapter_select.notifyDataSetChanged();
            }
        };

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("MyUUID",null, null, null));
            beaconManager.addRangeNotifier(rangeNotifier);
        } catch (RemoteException e) {   }

    }
}
