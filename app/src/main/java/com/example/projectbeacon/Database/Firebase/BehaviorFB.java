package com.example.projectbeacon.Database.Firebase;

import android.util.Log;

import com.example.projectbeacon.Model.Behavior;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class BehaviorFB {
    FirebaseFirestore db;
    private String TAG = "BehaviorFB";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public BehaviorFB(){
        initFirestore();
    }

    public void addBehavior(final Behavior beh){
        db.collection("behaviors")
                .whereEqualTo("username", beh.getUsername())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
//                            Log.d(TAG, "size "+task.getResult().size() );
                            //get table size
                            int number = task.getResult().size();
                            //assign number
                            beh.setBehavior_id("bh"+(number+1));
                            //add to cloud
                            db.collection("behaviors").document().set(beh);
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public ArrayList<Behavior> getAllBehavior(){
        Log.d(TAG, "getAllBehavior()");
        final ArrayList<Behavior> all = new ArrayList<>();
        db.collection("behaviors")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Behavior b = document.toObject(Behavior.class);
                                all.add(b);
                                Log.d(TAG, b.getBehavior_id());
                            }
                        }
                    }
                });
        return all;
    }

    public ArrayList<Behavior> getBehaviorByUsername(String username){
        Log.d(TAG, "getBehaviorByUsername(String username)");
        final ArrayList<Behavior> userBehavior = new ArrayList<>();
        db.collection("beacons")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Behavior b = document.toObject(Behavior.class);
                                userBehavior.add(b);
                                Log.d(TAG, b.getBehavior_id());
                            }
                        }
                    }
                });
        return userBehavior;
    }
}
