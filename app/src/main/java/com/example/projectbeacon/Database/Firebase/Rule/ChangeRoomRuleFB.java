package com.example.projectbeacon.Database.Firebase.Rule;

import com.example.projectbeacon.Model.Rule.ChangeRoomRule;
import com.google.firebase.firestore.FirebaseFirestore;

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
}
