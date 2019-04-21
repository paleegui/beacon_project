package com.example.projectbeacon.Database.Firebase;

import android.util.Log;

import com.example.projectbeacon.Model.OffScreenTime;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;

public class OffScreenTimeFB {
    FirebaseFirestore db;
    private String TAG = "OffScreenTimeFB";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public OffScreenTimeFB(){
        initFirestore();
    }

    public void updateOffScreenTime(final OffScreenTime offSC){
        db.collection("offScreenTimes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(task.getResult().size()<1){
                                db.collection("offScreenTimes").document(offSC.getUsername()).set(offSC);
                            } else {
                                db.collection("offScreenTimes").document(offSC.getUsername())
                                        .update(
                                                "time", offSC.getTime()
                                        );
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public Date getOffScreenTime(final String username){
        Log.d(TAG, "getOffScreenTime(final String username)");
        final ArrayList<Date> d = new ArrayList<>();
        db.collection("offScreenTimes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                OffScreenTime ost = document.toObject(OffScreenTime.class);
                                if(ost.getUsername().equals(username)){
                                    d.add(ost.getTime()) ;
                                }
                                Log.d(TAG, d.get(0).toString());
                            }
                        }
                    }
                });
        return d.get(0);
    }

}
