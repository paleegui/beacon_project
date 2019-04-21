package com.example.projectbeacon.Database.Firebase;

import android.util.Log;

import com.example.projectbeacon.Model.Activity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class ActivityFB {
    FirebaseFirestore db;
    private String TAG = "ActivityFB";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public ActivityFB(){
        initFirestore();
    }

    public void addActivity(final Activity avt){
        db.collection("activitys")
                .whereEqualTo("username", avt.getUsername())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
//                            Log.d(TAG, "size "+task.getResult().size() );
                            //get activity table size
                            int number = task.getResult().size();
                            //assign activity's number
                            avt.setAct_no("acc"+(number+1));
                            //add to cloud
                            db.collection("activitys").document().set(avt);

//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    public ArrayList<Activity> getAllActivity(){
        Log.d(TAG, "getAllActivity()");
        final ArrayList<Activity> all = new ArrayList<>();
        db.collection("activitys")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Activity a = document.toObject(Activity.class);
                                all.add(a);
                                Log.d(TAG, a.getAct_no());
                            }
                        }
                    }
                });
        return all;
    }

    public ArrayList<Activity> getActivityByUsername(String username){
        Log.d(TAG, "getActivityByUsername(Activity avt)");
        final ArrayList<Activity> userAcc = new ArrayList<>();
        db.collection("activitys")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Activity a = document.toObject(Activity.class);
                                userAcc.add(a);
                                Log.d(TAG, a.getAct_no());
                            }
                        }
                    }
                });
        return userAcc;
    }

}
