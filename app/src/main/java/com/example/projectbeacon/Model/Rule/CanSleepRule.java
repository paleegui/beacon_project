package com.example.projectbeacon.Model.Rule;

import android.util.Log;

import com.google.firebase.firestore.FirebaseFirestore;

public class CanSleepRule {
    private String cs_rule_id;
    private String room_type;
    private String time_id;
    private boolean canSleep;


    FirebaseFirestore db;
    private String TAG = "TEST";

    private void initFirestore() {
        db = FirebaseFirestore.getInstance();
    }

    public CanSleepRule(){}

    public CanSleepRule(String cs_rule, String room_type, String time_id, boolean canSleep) {
        this.cs_rule_id = cs_rule;
        this.room_type = room_type;
        this.time_id = time_id;
        this.canSleep = canSleep;
    }

    public String getCs_rule() {
        return cs_rule_id;
    }

    public void setCs_rule(String cs_rule) {
        this.cs_rule_id = cs_rule;
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

    public int isCanSleep() {
        if(canSleep){
            return 1;
        }else{
            return 0;
        }
    }

    public void setCanSleep(boolean canSleep) {
        this.canSleep = canSleep;
    }

    private void uploadCanSleepRule(){
        initFirestore();
        String str1[] = {"bedroom","T1","T2","T3","T8","T9"};
        String str2[] = {"bedroom","T4","T5","T6","T7"};
        String str3[] = {"toilet",""};
        String str4[] = {"kitchen",""};
        String str5[] = {"living room","T9","T1","T2","T4","T6","T7","T8"};
        String str6[] = {"living room","T3","T5"};
        String str7[] = {"working room",""};

        Log.d(TAG,"in function");
        CanSleepRule csr = new CanSleepRule();
        int cnt = 1;
//        CanSleepRule csr = new CanSleepRule("csr"+cnt, str1[0], str1[1], true);
//        db.collection("canSleepRule").document().set(csr);


        for(int i=1; i<str1.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str1[i]);
            csr.setRoom_type(str1[0]);
            csr.setCanSleep(true);
            Log.d(TAG,"in loop 1 for "+cnt);
            db.collection("canSleepRules").document(csr.getCs_rule()).set(csr);
            cnt++;
        }
        for(int i=1; i<str2.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str2[i]);
            csr.setRoom_type(str2[0]);
            csr.setCanSleep(false);
            Log.d(TAG,"in loop 2 for "+cnt);
            db.collection("canSleepRules").document(csr.getCs_rule()).set(csr);
            cnt++;
        }
        for(int i=1; i<str3.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str3[i]);
            csr.setRoom_type(str3[0]);
            csr.setCanSleep(false);
            Log.d(TAG,"in loop 3 for "+cnt);
            db.collection("canSleepRules").document(csr.getCs_rule()).set(csr);
            cnt++;
        }
        for(int i=1; i<str4.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str4[i]);
            csr.setRoom_type(str4[0]);
            csr.setCanSleep(false);
            Log.d(TAG,"in loop 4 for "+cnt);
            db.collection("canSleepRules").document(csr.getCs_rule()).set(csr);
            cnt++;
        }
        for(int i=1; i<str5.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str5[i]);
            csr.setRoom_type(str5[0]);
            csr.setCanSleep(false);
            Log.d(TAG,"in loop 5 for "+cnt);
            db.collection("canSleepRules").document(csr.getCs_rule()).set(csr);
            cnt++;
        }
        for(int i=1; i<str6.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str6[i]);
            csr.setRoom_type(str6[0]);
            csr.setCanSleep(true);
            Log.d(TAG,"in loop 6 for "+cnt);
            db.collection("canSleepRules").document(csr.getCs_rule()).set(csr);
            cnt++;
        }
        for(int i=1; i<str7.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str7[i]);
            csr.setRoom_type(str7[0]);
            csr.setCanSleep(false);
            Log.d(TAG,"in loop 7 for "+cnt);
            db.collection("canSleepRules").document(csr.getCs_rule()).set(csr);
            cnt++;
        }


    }
}
