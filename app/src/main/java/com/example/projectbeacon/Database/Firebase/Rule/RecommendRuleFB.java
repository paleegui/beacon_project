package com.example.projectbeacon.Database.Firebase.Rule;

import android.util.Log;

import com.example.projectbeacon.Model.Rule.RecommendRule;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import androidx.annotation.NonNull;

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

    public ArrayList<RecommendRule> getRecommendText(final String roomType, final String timeID){
        final ArrayList<RecommendRule> ruleList = new ArrayList<>();
        db.collection("recommendRules")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                RecommendRule rule = document.toObject(RecommendRule.class);
                                String[] a = timeID.split("T");
                                String[] b = rule.getTime_id().split("T");
                                int aInt = Integer.parseInt(a[1]);
                                int bInt = Integer.parseInt(b[1]);
                                if(aInt<bInt && bInt-aInt == 1){
                                    ruleList.add(rule);
                                }
                            }
                        }
                    }
                });
        return ruleList;
    }



}
