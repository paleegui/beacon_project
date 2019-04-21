package com.example.projectbeacon.Database.SQLite.Rule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectbeacon.Model.Rule.CanSleepRule;

import java.util.ArrayList;

public class CSRuleSqlite extends SQLiteOpenHelper {
//    private String cs_rule_id;
//    private String room_type;
//    private String time_id;
//    private boolean canSleep;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "csruledb";
    private static final String TABLE_CSRules = "csruledetails";
    private static final String KEY_ID = "id";
    private static final String KEY_RULE_ID = "cs_rule_id";
    private static final String KEY_ROOM_TYPE = "room_type";
    private static final String KEY_TIME_ID = "time_id";
    private static final String KEY_CAN_SLEEP = "canSleep";


    public CSRuleSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CSRules + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_RULE_ID + " TEXT,"
                + KEY_ROOM_TYPE + " TEXT,"
                + KEY_TIME_ID + " TEXT,"
                + KEY_CAN_SLEEP + " INTEGER"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CSRules);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new rule Details
    public void insertRule(CanSleepRule csr){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_RULE_ID, csr.getCs_rule());
        cValues.put(KEY_ROOM_TYPE, csr.getRoom_type());
        cValues.put(KEY_TIME_ID, csr.getTime_id());
        cValues.put(KEY_CAN_SLEEP, csr.isCanSleep());
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_CSRules,null, cValues);
        db.close();
    }
    // Get Rule Details
    public ArrayList<CanSleepRule> getRules(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<CanSleepRule> ruleList = new ArrayList<>();
        String query = "SELECT cs_rule_id, room_type, time_id, canSleep FROM "+ TABLE_CSRules;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            CanSleepRule rule = new CanSleepRule();
            rule.setCs_rule(cursor.getString(cursor.getColumnIndex(KEY_RULE_ID)));
            rule.setRoom_type(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            rule.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_CAN_SLEEP))) == 1){
                rule.setCanSleep(true);
            }else{
                rule.setCanSleep(false);
            }
            ruleList.add(rule);
        }
        return  ruleList;
    }

    // Delete Rule Details
    public void deleteRule(String rule_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CSRules, KEY_RULE_ID+" = ?",new String[]{rule_id});
        db.close();
    }

    // Update Rule Details
    public int updateRule(CanSleepRule csr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_ROOM_TYPE, csr.getRoom_type());
        cVals.put(KEY_TIME_ID, csr.getTime_id());
        cVals.put(KEY_CAN_SLEEP, csr.isCanSleep());
        int count = db.update(TABLE_CSRules, cVals, KEY_RULE_ID+" = ?",new String[]{csr.getCs_rule()});
        return  count;
    }

    public void uploadCanSleepRule(){
        String str1[] = {"bedroom","T1","T2","T3","T8","T9"};
        String str2[] = {"bedroom","T4","T5","T6","T7"};
        String str3[] = {"toilet",""};
        String str4[] = {"kitchen",""};
        String str5[] = {"living room","T9","T1","T2","T4","T6","T7","T8"};
        String str6[] = {"living room","T3","T5"};
        String str7[] = {"working room",""};

        CanSleepRule csr = new CanSleepRule();
        int cnt = 1;
        for(int i=1; i<str1.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str1[i]);
            csr.setRoom_type(str1[0]);
            csr.setCanSleep(true);
            insertRule(csr);
            cnt++;
        }
        for(int i=1; i<str2.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str2[i]);
            csr.setRoom_type(str2[0]);
            csr.setCanSleep(false);
            insertRule(csr);
            cnt++;
        }
        for(int i=1; i<str3.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str3[i]);
            csr.setRoom_type(str3[0]);
            csr.setCanSleep(false);
            insertRule(csr);
            cnt++;
        }
        for(int i=1; i<str4.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str4[i]);
            csr.setRoom_type(str4[0]);
            csr.setCanSleep(false);
            insertRule(csr);
            cnt++;
        }
        for(int i=1; i<str5.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str5[i]);
            csr.setRoom_type(str5[0]);
            csr.setCanSleep(false);
            insertRule(csr);
            cnt++;
        }
        for(int i=1; i<str6.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str6[i]);
            csr.setRoom_type(str6[0]);
            csr.setCanSleep(true);
            insertRule(csr);
            cnt++;
        }
        for(int i=1; i<str7.length; i++){
            csr.setCs_rule("csr"+cnt);
            csr.setTime_id(str7[i]);
            csr.setRoom_type(str7[0]);
            csr.setCanSleep(false);
            insertRule(csr);
            cnt++;
        }


    }

    public boolean getCanSleep(final String roomType, String timeID){
//        room_type;
//        time_id;
        final boolean[] canSleep = {false};
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT cs_rule_id, room_type, time_id, canSleep FROM "
                + TABLE_CSRules +" where time_id = ?";
        Cursor cursor = db.rawQuery(query,new String[] {timeID});
        while (cursor.moveToNext()){
            CanSleepRule rule = new CanSleepRule();
            rule.setCs_rule(cursor.getString(cursor.getColumnIndex(KEY_RULE_ID)));
            rule.setRoom_type(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            rule.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_CAN_SLEEP))) == 1){
                rule.setCanSleep(true);
            }else{
                rule.setCanSleep(false);
            }
            if(rule.getRoom_type().equalsIgnoreCase(roomType)){
                canSleep[0] = rule.isCanSleep() == 1 ? true:false;
            }
        }
        return canSleep[0];
    }


}
