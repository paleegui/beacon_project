package com.example.projectbeacon.Database.Firebase.Rule;

import com.google.firebase.firestore.FirebaseFirestore;

public class RecommendRuleFB {
    private FirebaseFirestore db;
    private String TAG = "TEST";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public RecommendRuleFB(){
        initFirestore();
    }

    private void uploadRecommendRule() {
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
