package com.example.projectbeacon.Database.Firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.firebaseproject.model.ChangeRoom;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ChangeRoomFB {
    FirebaseFirestore db;
    private String TAG = "ChangeRoomFB";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public ChangeRoomFB(){
        initFirestore();
    }

    public ArrayList<ChangeRoom> getAllChangeRoom(){
        Log.d(TAG, "getAllChangeRoom()");
        final ArrayList<ChangeRoom> all = new ArrayList<>();
        db.collection("changerooms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                ChangeRoom cr = document.toObject(ChangeRoom.class);
                                all.add(cr);
                                Log.d(TAG, cr.getCr_rule());
                            }
                        }
                    }
                });
        return all;
    }

}
