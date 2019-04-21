package com.example.projectbeacon.Database.SQLite.Rule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.firebaseproject.rule.ChangeRoomRule;

import java.util.ArrayList;

public class CRRuleSqlite extends SQLiteOpenHelper {
//    private String cr_rule_id;
//    private String time_id;
//    private String room_type;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "crruledb";
    private static final String TABLE_CRRules = "crruledetails";
    private static final String KEY_ID = "id";
    private static final String KEY_RULE_ID = "cr_rule_id";
    private static final String KEY_TIME_ID = "time_id";
    private static final String KEY_ROOM_TYPE = "room_type";


    public CRRuleSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CRRules + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_RULE_ID + " TEXT,"
                + KEY_TIME_ID + " TEXT,"
                + KEY_ROOM_TYPE + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CRRules);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new Rule Details
    public void insertRule(ChangeRoomRule crr){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_RULE_ID, crr.getCr_rule_id());
        cValues.put(KEY_TIME_ID, crr.getTime_id());
        cValues.put(KEY_ROOM_TYPE, crr.getRoom_type());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_CRRules,null, cValues);
        db.close();
    }
    // Get Rule Details
    public ArrayList<ChangeRoomRule> getRules(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ChangeRoomRule> ruleList = new ArrayList<>();
        String query = "SELECT cr_rule_id, time_id, room_type FROM "+ TABLE_CRRules;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            ChangeRoomRule rule = new ChangeRoomRule();
            rule.setCr_rule_id(cursor.getString(cursor.getColumnIndex(KEY_RULE_ID)));
            rule.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            rule.setRoom_type(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            ruleList.add(rule);
        }
        return  ruleList;
    }

    // Delete Rule Details
    public void deleteRule(String rule_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CRRules, KEY_RULE_ID+" = ?",new String[]{rule_id});
        db.close();
    }

    // Update Rule Details
    public int updateRule(ChangeRoomRule crr){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_TIME_ID, crr.getTime_id());
        cVals.put(KEY_ROOM_TYPE, crr.getRoom_type());
        int count = db.update(TABLE_CRRules, cVals, KEY_RULE_ID+" = ?",new String[]{crr.getCr_rule_id()});
        return  count;
    }

    public void uploadRule(){
        String str1[] = {"T1","T2","T3","T5","T6","T7","T8"};
        String dest1[] = {"toilet","kitchen","living room","working room"};
        int cnt = 1;
        for(int i=0; i<str1.length; i++){
            for(int j=0; j<dest1.length; j++){
                ChangeRoomRule crr = new ChangeRoomRule("crr"+cnt, str1[i], dest1[j]);
                insertRule(crr);
                cnt++;
            }
        }
        ChangeRoomRule crr = new ChangeRoomRule("crr"+cnt, "T4", "kitchen");
        insertRule(crr);
        cnt++;

        ChangeRoomRule crr2 = new ChangeRoomRule("crr"+cnt, "T9", "bedroom");
        insertRule(crr);
    }

}
