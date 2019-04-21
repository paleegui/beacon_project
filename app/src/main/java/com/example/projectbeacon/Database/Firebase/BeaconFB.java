package com.example.projectbeacon.Database.Firebase;

import android.util.Log;

import com.example.projectbeacon.Model.Beacon;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class BeaconFB {
    FirebaseFirestore db;
    private String TAG = "BeaconFB";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public BeaconFB(){
        initFirestore();
    }

    public void addBeacon(Beacon bc){
        db.collection("beacons").document().set(bc);
    }

    public ArrayList<Beacon> getAllBeacon(){
        Log.d(TAG, "getAllBeacon()");
        final ArrayList<Beacon> all = new ArrayList<>();
        db.collection("beacons")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Beacon b = document.toObject(Beacon.class);
                                all.add(b);
                                Log.d(TAG, b.getBeacon_id());
                            }
                        }
                    }
                });
        return all;
    }

    public ArrayList<Beacon> getBeaconByUsername(String username){
        Log.d(TAG, "getBeaconByUsername(String username)");
        final ArrayList<Beacon> userBeacon = new ArrayList<>();
        db.collection("beacons")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Beacon b = document.toObject(Beacon.class);
                                userBeacon.add(b);
                                Log.d(TAG, b.getBeacon_id());
                            }
                        }
                    }
                });
        return userBeacon;
    }
}
