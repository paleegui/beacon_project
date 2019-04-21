package com.example.projectbeacon.Database.Firebase;

import com.example.firebaseproject.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserFB {
    FirebaseFirestore db;
    private String TAG = "ActivityFB";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public UserFB(){
        initFirestore();
    }

    public void addUser(User user){
        db.collection("user").document(user.getUsername()).set(user);
    }


}
