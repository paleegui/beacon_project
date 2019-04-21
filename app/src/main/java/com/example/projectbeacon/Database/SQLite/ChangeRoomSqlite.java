package com.example.projectbeacon.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.firebaseproject.model.ChangeRoom;
import com.example.firebaseproject.rule.ChangeRoomRule;

import java.util.ArrayList;
import java.util.HashMap;

public class ChangeRoomSqlite extends SQLiteOpenHelper {
//    private String cr_rule_id;
//    private String time_id;
//    private String room_type;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "changeroomruledb";
    private static final String TABLE_ChRRules = "changeroomruledetails";
    private static final String KEY_ID = "id";
    private static final String KEY_CR_RULE_ID = "cr_rule_id";
    private static final String KEY_TIME_ID = "time_id";
    private static final String KEY_ROOM_TYPE = "room_type";

    public ChangeRoomSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_ChRRules + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CR_RULE_ID + " TEXT,"
                + KEY_TIME_ID + " TEXT,"
                + KEY_ROOM_TYPE + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ChRRules);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new Rule Details
    public void insertRuleDetails(ChangeRoomRule rule){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_CR_RULE_ID, rule.getCr_rule_id());
        cValues.put(KEY_TIME_ID, rule.getTime_id());
        cValues.put(KEY_ROOM_TYPE, rule.getRoom_type());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_ChRRules,null, cValues);
        db.close();
    }
    // Get Rule Details
    public ArrayList<HashMap<String, String>> GetRules(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> ruleList = new ArrayList<>();
        String query = "SELECT cr_rule_id, time_id, room_type FROM "+ TABLE_ChRRules;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> rule = new HashMap<>();
            rule.put("cr_rule_id",cursor.getString(cursor.getColumnIndex(KEY_CR_RULE_ID)));
            rule.put("time_id",cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            rule.put("room_type",cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            ruleList.add(rule);
        }
        return  ruleList;
    }

    // Get Rule Details
    public ArrayList<ChangeRoom> GetAllRules(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ChangeRoom> ruleList = new ArrayList<>();
        String query = "SELECT cr_rule_id, time_id, room_type FROM "+ TABLE_ChRRules;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            ChangeRoom rule = new ChangeRoom();
            rule.setCr_rule(cursor.getString(cursor.getColumnIndex(KEY_CR_RULE_ID)));
            rule.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            rule.setRoom_id(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            ruleList.add(rule);
        }
        return  ruleList;
    }

    // Get Rule By Time Details
    public ArrayList<ChangeRoom> GetAllRulesByTime(String time){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ChangeRoom> ruleList = new ArrayList<>();
        String query = "SELECT cr_rule_id, time_id, room_type FROM "
                        + TABLE_ChRRules+" where time_id = ?";
        Cursor cursor = db.rawQuery(query,new String[] {time});
        while (cursor.moveToNext()){
            ChangeRoom rule = new ChangeRoom();
            rule.setCr_rule(cursor.getString(cursor.getColumnIndex(KEY_CR_RULE_ID)));
            rule.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            rule.setRoom_id(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            ruleList.add(rule);
        }
        return  ruleList;
    }

    // Get Rule By Room Details
    public ArrayList<ChangeRoom> GetAllRulesByRoom(String room){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<ChangeRoom> ruleList = new ArrayList<>();
        String query = "SELECT cr_rule_id, time_id, room_type FROM "
                + TABLE_ChRRules+" where room_type = ?";
        Cursor cursor = db.rawQuery(query,new String[] {room});
        while (cursor.moveToNext()){
            ChangeRoom rule = new ChangeRoom();
            rule.setCr_rule(cursor.getString(cursor.getColumnIndex(KEY_CR_RULE_ID)));
            rule.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            rule.setRoom_id(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            ruleList.add(rule);
        }
        return  ruleList;
    }

    // Delete Rule Details
    public void DeleteRule(String cr_rule_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ChRRules, KEY_CR_RULE_ID+" = ?",new String[]{cr_rule_id});
        db.close();
    }

    // Update Rule Details
    public int UpdateRuleDetails(ChangeRoomRule rule){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_TIME_ID, rule.getTime_id());
        cVals.put(KEY_ROOM_TYPE, rule.getRoom_type());
        int count = db.update(TABLE_ChRRules, cVals, KEY_CR_RULE_ID+" = ?",new String[]{rule.getCr_rule_id()});
        return  count;
    }

}
