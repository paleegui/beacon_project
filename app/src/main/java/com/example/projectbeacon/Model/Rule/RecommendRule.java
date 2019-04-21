package com.example.projectbeacon.Model.Rule;

//recommend text

import com.google.firebase.firestore.FirebaseFirestore;

public class RecommendRule {
    private String rec_rule_id;
    private String room_type;
    private String time_id;
    private String rec_text;

    FirebaseFirestore db;
    private String TAG = "TEST";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }


    public RecommendRule() { }

    public RecommendRule(String rec_rule_id, String room_type, String time_id, String rec_text) {
        this.rec_rule_id = rec_rule_id;
        this.room_type = room_type;
        this.time_id = time_id;
        this.rec_text = rec_text;
    }

    public String getRec_rule_id() {
        return rec_rule_id;
    }

    public void setRec_rule_id(String rec_rule_id) {
        this.rec_rule_id = rec_rule_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }

    public String getRec_text() {
        return rec_text;
    }

    public void setRec_text(String rec_text) {
        this.rec_text = rec_text;
    }

    private void uploadRecommendRule() {
        initFirestore();
        //RecommendRule rec = new RecommendRule(String rec_rule_id, String room_type, String time_id, String rec_text);
        String room[] = {"bedroom","kitchen","living room","working room","toilet"};
        String time[] = {"T1","T2","T3","T4","T5","T6","T7","T8","T9"};
        String bed_text[] = {};
        String kitchen_text[] = {};
        String living_text[] = {};
        String working_text[] = {};
        String toilet_text[] = {};


    }
}
