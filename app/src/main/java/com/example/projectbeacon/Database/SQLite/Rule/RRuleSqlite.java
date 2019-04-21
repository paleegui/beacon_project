package com.example.projectbeacon.Database.SQLite.Rule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.example.projectbeacon.Database.SQLite.TimeSqlite;
import com.example.projectbeacon.Model.Rule.RecommendRule;
import com.example.projectbeacon.Model.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class RRuleSqlite extends SQLiteOpenHelper {
//    private String rec_rule_id;
//    private String room_type;
//    private String time_id;
//    private String rec_text;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "rruledb";
    private static final String TABLE_RRules = "rruledetails";
    private static final String KEY_ID = "id";
    private static final String KEY_RULE_ID = "rec_rule_id";
    private static final String KEY_ROOM_TYPE = "room_type";
    private static final String KEY_TIME_ID = "time_id";
    private static final String KEY_REC_TEXT = "rec_text";

    private TimeSqlite timeSqlite;

    public RRuleSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
        timeSqlite = new TimeSqlite(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "
                + TABLE_RRules + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_RULE_ID + " TEXT,"
                + KEY_ROOM_TYPE + " TEXT,"
                + KEY_TIME_ID + " TEXT,"
                + KEY_REC_TEXT + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RRules);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new reccommend Details
    public void insertRecommendDetails(RecommendRule rule){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_RULE_ID, rule.getRec_rule_id());
        cValues.put(KEY_ROOM_TYPE, rule.getRoom_type());
        cValues.put(KEY_TIME_ID, rule.getTime_id());
        cValues.put(KEY_REC_TEXT, rule.getRec_text());
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_RRules,null, cValues);
        db.close();
    }
    // Get recommend Details
    public ArrayList<RecommendRule> GetRecommendRules(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<RecommendRule> ruleList = new ArrayList<>();
        String query = "SELECT rec_rule_id, room_type, time_id, rec_text FROM "+ TABLE_RRules;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            RecommendRule rule = new RecommendRule();
            rule.setRec_rule_id(cursor.getString(cursor.getColumnIndex(KEY_RULE_ID)));
            rule.setRoom_type(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            rule.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            rule.setRec_text(cursor.getString(cursor.getColumnIndex(KEY_REC_TEXT)));
            ruleList.add(rule);
        }
        return  ruleList;
    }

    // Delete recommend Details
    public void DeleteRecommend(String ruleID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_RRules, KEY_RULE_ID+" = ?",new String[]{ruleID});
        db.close();
    }

    // Update recommend Details
    public int UpdateRecommendDetails(RecommendRule rule){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_ROOM_TYPE, rule.getRoom_type());
        cValues.put(KEY_TIME_ID, rule.getTime_id());
        cValues.put(KEY_REC_TEXT, rule.getRec_text());
        int count = db.update(TABLE_RRules, cValues, KEY_RULE_ID+" = ?",new String[]{rule.getRec_rule_id()});
        return  count;
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

    public ArrayList<RecommendRule> getRecommendText(String roomType, String timeID){
        ArrayList<RecommendRule> ruleList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT rec_rule_id, room_type, time_id, rec_text FROM "+ TABLE_RRules;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            RecommendRule rule = new RecommendRule();
            rule.setRec_rule_id(cursor.getString(cursor.getColumnIndex(KEY_RULE_ID)));
            rule.setRoom_type(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            rule.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            rule.setRec_text(cursor.getString(cursor.getColumnIndex(KEY_REC_TEXT)));

            String[] a = timeID.split("T");
            String[] b = rule.getTime_id().split("T");
            int aInt = Integer.parseInt(a[1]);
            int bInt = Integer.parseInt(b[1]);

            if(aInt<bInt && bInt-aInt == 1){
                ruleList.add(rule);
            }
        }
        return ruleList;
    }

}
