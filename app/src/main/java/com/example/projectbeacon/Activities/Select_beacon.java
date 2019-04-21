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
                        Log.d("Test1111", "id1:"+beacon.getId1()+" ");
                        Log.d("Test1111", "id2:"+beacon.getId2()+" ");
                        Log.d("Test1111", "id3:"+beacon.getId3()+" ");
                        Log.d("Test1111", "id4:"+beacon.getParserIdentifier()+" ");
                    }
                    Log.d("Test","----------------------------------------------------");
                }

                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            // Setting Up the Adapter for Recycler View
                            recycleViewAdapter_select = new RecycleViewAdapter_Select(getApplicationContext(),beaconList);
                            recyclerView.setAdapter(recycleViewAdapter_select);
                            recycleViewAdapter_select.notifyDataSetChanged();
                        }
                    });
                }catch(Exception e){

                }
            }
        };

        try {
            Region region = new Region("MyUUID",null, null, null);
            beaconManager.startRangingBeaconsInRegion(region);
            beaconManager.addRangeNotifier(rangeNotifier);
            beaconManager.startMonitoringBeaconsInRegion(region);
        } catch (RemoteException e) {   }

    }

    // Override onDestroy Method
    @Override
    public void onDestroy() {
        super.onDestroy();
        //Unbinds an Android Activity or Service to the BeaconService to avoid leak.
        beaconManager.unbind(this);
    }
}
