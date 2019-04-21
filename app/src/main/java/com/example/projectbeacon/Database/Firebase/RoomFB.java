package com.example.projectbeacon.Database.Firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.firebaseproject.model.Room;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RoomFB {
    FirebaseFirestore db;
    private String TAG = "RoomFB";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public RoomFB(){
        initFirestore();
    }

    public void addRoom(final Room room){
        db.collection("rooms")
                .whereEqualTo("username", room.getUsername())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
//                            Log.d(TAG, "size "+task.getResult().size() );
                            //get table size
                            int number = task.getResult().size();
                            //assign number
                            room.setRoom_id("r"+number);
                            //add to cloud
                            db.collection("rooms").document().set(room);

//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public ArrayList<Room> getAllRoom(){
        Log.d(TAG, "getAllRoom()");
        final ArrayList<Room> all = new ArrayList<>();
        db.collection("rooms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Room r = document.toObject(Room.class);
                                all.add(r);
                                Log.d(TAG, r.getRoom_id());
                            }
                        }
                    }
                });
        return all;
    }

    public ArrayList<Room> getRoomByUsername(String username){
        Log.d(TAG, "getRoomByUsername(String username)");
        final ArrayList<Room> userRoom = new ArrayList<>();
        db.collection("rooms")
                .whereEqualTo("username", username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                Room r = document.toObject(Room.class);
                                userRoom.add(r);
                                Log.d(TAG, r.getRoom_id());
                            }
                        }
                    }
                });
        return userRoom;
    }
}
