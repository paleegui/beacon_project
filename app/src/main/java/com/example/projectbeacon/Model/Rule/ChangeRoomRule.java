package com.example.projectbeacon.Model.Rule;

//use after can_sleep_rule

import com.google.firebase.firestore.FirebaseFirestore;

public class ChangeRoomRule {
    private String cr_rule_id;
    private String time_id;
    private String room_type;

    FirebaseFirestore db;
    private String TAG = "TEST";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }


    public ChangeRoomRule() {}

    public ChangeRoomRule(String cr_rule_id, String time, String room_type) {
        this.cr_rule_id = cr_rule_id;
        this.time_id = time;
        this.room_type = room_type;
    }

    public String getCr_rule_id() {
        return cr_rule_id;
    }

    public void setCr_rule_id(String cr_rule_id) {
        this.cr_rule_id = cr_rule_id;
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    private void uploadChangeRoomRule(){
        initFirestore();
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
