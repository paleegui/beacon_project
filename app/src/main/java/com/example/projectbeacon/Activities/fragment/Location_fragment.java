package com.example.projectbeacon.Activities.fragment;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectbeacon.R;
import com.example.projectbeacon.Services.BeaconMonitoringService;

import org.altbeacon.beacon.BeaconManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class Location_fragment extends Fragment {

    private BeaconManager beaconManager = null;

    public Location_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_location_fragment, container, false);


        beaconManager =  BeaconManager.getInstanceForApplication(getContext());
        startActivity(new Intent(this.getActivity().getApplicationContext(), BeaconMonitoringService.class));



        return view;
    }

    public BeaconManager getBeaconManager() {
        if (beaconManager == null) {
            beaconManager = BeaconManager.getInstanceForApplication(getContext());
        }
        return beaconManager;
    }

}
