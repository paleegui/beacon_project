package com.example.projectbeacon.Database.Firebase.Rule;

import android.util.Log;

import com.example.projectbeacon.Model.Rule.ChangeRoomRule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class ChangeRoomRuleFB {
    private FirebaseFirestore db;
    private String TAG = "TEST";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public ChangeRoomRuleFB(){
        initFirestore();
    }

    private void uploadChangeRoomRule(){
        String str1[] = {"T1","T2","T3","T5","T6","T7","T8"};
        String dest1[] = {"toilet","kitchen","living room","working room"};
        int cnt = 1;
        for(int i=0; i<str1.length; i++){
            for(int j=0; j<dest1.length; j++){
                ChangeRoomRule crr = new ChangeRoomRule("crr"+cnt, str1[i], dest1[j]);
                db.collection("changeRoomRules").document(crr.getCr_rule_id()).set(crr);
                cnt++;
            }
        }
        ChangeRoomRule crr = new ChangeRoomRule("crr"+cnt, "T4", "kitchen");
        db.collection("changeRoomRules").document(crr.getCr_rule_id()).set(crr);
        cnt++;

        ChangeRoomRule crr2 = new ChangeRoomRule("crr"+cnt, "T9", "bedroom");
        db.collection("changeRoomRules").document(crr2.getCr_rule_id()).set(crr2);

    }

    public ArrayList<String> getDestinationRoom(final String roomType, String timeID){
//        room_type;
//        time_id;
        final ArrayList<String> roomTypeList = new ArrayList<>();
        db.collection("changeRoomRules")
                .whereEqualTo("time_id", timeID)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                ChangeRoomRule c = document.toObject(ChangeRoomRule.class);
                                if(!c.getRoom_type().equalsIgnoreCase(roomType)){
                                    roomTypeList.add(c.getRoom_type());
                                    Log.d(TAG, c.getRoom_type());
                                }
                            }
                        }
                    }
                });
        return roomTypeList;
    }
}
