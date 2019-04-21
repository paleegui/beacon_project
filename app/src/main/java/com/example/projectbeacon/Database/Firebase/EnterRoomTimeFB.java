package com.example.projectbeacon.Database.Firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.firebaseproject.model.EnterRoomTime;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class EnterRoomTimeFB {
    FirebaseFirestore db;
    private String TAG = "EnterRoomTimeFB";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public EnterRoomTimeFB(){
        initFirestore();
    }

    public void updateEnterRoomTime(final EnterRoomTime enterRT){
        db.collection("enterRoomTimes")
                .whereEqualTo("username", enterRT.getUsername())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if(task.getResult().size()<1){
                                db.collection("enterRoomTimes").document(enterRT.getUsername()).set(enterRT);
                            } else {
                                db.collection("enterRoomTimes").document(enterRT.getUsername())
                                        .update(
                                                "time", enterRT.getTime()
                                        );
                            }

                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public Date getEnterRoomTime(final String username){
        Log.d(TAG, "getEnterRoomTime(final String username)");
        final ArrayList<Date> d = new ArrayList<>();
        db.collection("enterRoomTimes")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                EnterRoomTime ert = document.toObject(EnterRoomTime.class);
                                if(ert.getUsername().equals(username)){
                                  d.add(ert.getTime()) ;
                                }
                                Log.d(TAG, d.get(0).toString());
                            }
                        }
                    }
                });
        return d.get(0);
    }
}
