package com.example.projectbeacon.Activities.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectbeacon.Activities.Select_beacon;
import com.example.projectbeacon.R;
import com.example.projectbeacon.UI.PageAdapter;
import com.example.projectbeacon.UI.RecycleViewAdapter;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.ArrayList;
import java.util.Collection;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListBeacon_fragment extends Fragment implements BeaconConsumer,ShowRoom_BeaconList.OnFragmentInteractionListener,
ShowFloor_BeaconList.OnFragmentInteractionListener, ShowAll_BeaconList.OnFragmentInteractionListener{
    private RecyclerView recyclerView;
    private RecycleViewAdapter recyeclerViewAdapter;
    private ArrayList<Beacon> listItem;
    private BeaconManager beaconManager;
    private Context context;
    private FragmentActivity myContext;
    private TabLayout myTabs;
    private ViewPager myPages;

    public ListBeacon_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_list_beacon_fragment, container, false);
        context = v.getContext();


        myTabs = (TabLayout)v.findViewById(R.id.myTableList);
        myTabs.addTab(myTabs.newTab().setText("อุปกรณ์ทั้งหมด"));
        myTabs.addTab(myTabs.newTab().setText("ชั้น"));
        myTabs.addTab(myTabs.newTab().setText("ห้อง"));
        myTabs.setTabGravity(TabLayout.GRAVITY_FILL);


        myPages = (ViewPager)v.findViewById(R.id.myPageList);

        final PageAdapter adapter = new PageAdapter(getFragmentManager(),3);
        myPages.setAdapter(adapter);


        myPages.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                myTabs.setScrollPosition(i,0,true);
                myTabs.setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        myPages.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(myTabs));

        myTabs.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                myPages.setCurrentItem(tab.getPosition());
                Log.d("OnTab", "onTabSelected: ");
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Log.d("OnTab", "onTabUnselected: ");
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Log.d("OnTab", "onTabReselected: ");
            }
        });



        /////////////////////////////////////////----work----////////////////////////////////////////////////
        FloatingActionButton fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity().getApplication(), Select_beacon.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });

        //
        beaconManager = BeaconManager.getInstanceForApplication(context);

        //UI Showing
//        recyclerView = (RecyclerView)v.findViewById(R.id.RecyclerViewID);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new GridLayoutManager(context,2));


        //new ArrayList
        listItem = new ArrayList<Beacon>();

        beaconManager.bind(this);

        return v;
    }

    @Override
    public void onBeaconServiceConnect() {
        RangeNotifier rangeNotifier = new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                listItem = (ArrayList<Beacon>) beacons;
                if (beacons.size() > 0) {
                    for(Beacon beacon :beacons){
                        Log.d("Test","The Beacon "+beacon.toString()+ "is about:" + beacon.getDistance());
                        Log.d("Test11",""+beacon.getBluetoothAddress());
                    }
                    Log.d("Test","----------------------------------------------------");
                }

//                recyeclerViewAdapter = new RecycleViewAdapter(context,listItem);
//                recyclerView.setAdapter(recyeclerViewAdapter);
//                recyeclerViewAdapter.notifyDataSetChanged();
            }
        };


        try {
            beaconManager.startRangingBeaconsInRegion(new Region("MyUUID",null, null, null));
            beaconManager.addRangeNotifier(rangeNotifier);
        } catch (RemoteException e) {   }
    }

    @Override
    public Context getApplicationContext() {
        return null;
    }

    @Override
    public void unbindService(ServiceConnection serviceConnection) {

    }

    @Override
    public boolean bindService(Intent intent, ServiceConnection serviceConnection, int i) {
        return false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
