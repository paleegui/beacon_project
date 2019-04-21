package com.example.projectbeacon.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.firebaseproject.model.Behavior;

import java.util.ArrayList;

public class BehaviorSqlite extends SQLiteOpenHelper {
//    private String behavior_id;
//    private String username;
//    private boolean is_active;
//    private boolean last_active;
//    private String time_id;
//    private String room_id;
//    private boolean dol;
//    private int freq;
//    private boolean isSleep;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "behaviordb";
    private static final String TABLE_Behaviors = "behaviordetails";
    private static final String KEY_ID = "id";
    private static final String KEY_BEHAVIOR_ID = "behavior_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IS_ACTIVE = "is_active";
    private static final String KEY_LAST_ACTIVE = "last_active";
    private static final String KEY_TIME_ID = "time_id";
    private static final String KEY_ROOM_ID = "room_id";
    private static final String KEY_DOL = "dol";
    private static final String KEY_FREQ = "freq";


    public BehaviorSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Behaviors + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_BEHAVIOR_ID + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_IS_ACTIVE + " INTEGER,"
                + KEY_LAST_ACTIVE + " INTEGER,"
                + KEY_TIME_ID + " TEXT,"
                + KEY_ROOM_ID + " TEXT,"
                + KEY_DOL + " INTEGER,"
                + KEY_FREQ + " INTEGER"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Behaviors);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new Behavior Details
    public void insertActivityDetails(Behavior bh){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_BEHAVIOR_ID, bh.getBehavior_id());
        cValues.put(KEY_USERNAME, bh.getUsername());
        cValues.put(KEY_IS_ACTIVE, bh.isActive());
        cValues.put(KEY_LAST_ACTIVE, bh.isLastActive());
        cValues.put(KEY_TIME_ID, bh.getTime_id());
        cValues.put(KEY_ROOM_ID, bh.getRoom_id());
        cValues.put(KEY_DOL, bh.isDol());
        cValues.put(KEY_FREQ, bh.getFreq());

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Behaviors,null, cValues);
        db.close();
    }
    // Get Behavior Details
//    public ArrayList<HashMap<String, String>> GetBehaviors(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<HashMap<String, String>> behaviorList = new ArrayList<>();
//        String query = "SELECT behavior_id, username, is_active, last_active, time_id, room_id, dol, freq FROM "+ TABLE_Behaviors;
//        Cursor cursor = db.rawQuery(query,null);
//        while (cursor.moveToNext()){
//            HashMap<String,String> behavior = new HashMap<>();
//            behavior.put("behavior_id",cursor.getString(cursor.getColumnIndex(KEY_BEHAVIOR_ID)));
//            behavior.put("username",cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
//            behavior.put("is_active",cursor.getString(cursor.getColumnIndex(KEY_IS_ACTIVE)));
//            behavior.put("last_active",cursor.getString(cursor.getColumnIndex(KEY_LAST_ACTIVE)));
//            behavior.put("time_id",cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
//            behavior.put("room_id",cursor.getString(cursor.getColumnIndex(KEY_ROOM_ID)));
//            behavior.put("dol",cursor.getString(cursor.getColumnIndex(KEY_DOL)));
//            behavior.put("freq",cursor.getString(cursor.getColumnIndex(KEY_FREQ)));
//            behaviorList.add(behavior);
//        }
//        return  behaviorList;
//    }

    // Get Behavior Details
    public ArrayList<Behavior> GetAllBehaviors(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Behavior> behaviorList = new ArrayList<>();
        String query = "SELECT behavior_id, username, is_active, last_active, time_id, room_id, dol, freq FROM " + TABLE_Behaviors;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Behavior behavior = new Behavior();
            behavior.setBehavior_id(cursor.getString(cursor.getColumnIndex(KEY_BEHAVIOR_ID)));
            behavior.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            behavior.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            behavior.setRoom_id(cursor.getString(cursor.getColumnIndex(KEY_ROOM_ID)));
            behavior.setFreq(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_FREQ))));
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IS_ACTIVE))) == 1){
                behavior.setActive(true);
            }else{
                behavior.setActive(false);
            }
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_LAST_ACTIVE))) == 1){
                behavior.setLastActive(true);
            }else{
                behavior.setLastActive(false);
            }
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_DOL))) == 1){
                behavior.setDol(true);
            }else{
                behavior.setDol(false);
            }
            behaviorList.add(behavior);
        }
        return  behaviorList;
    }

    // Get Behavior By Username Details
    public ArrayList<Behavior> GetAllBehaviorsByUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Behavior> behaviorList = new ArrayList<>();
        String query = "SELECT behavior_id, username, is_active, last_active, time_id, room_id, dol, freq FROM "
                + TABLE_Behaviors +" where username = ?";
        Cursor cursor = db.rawQuery(query,new String[] {username});
        while (cursor.moveToNext()){
            Behavior behavior = new Behavior();
            behavior.setBehavior_id(cursor.getString(cursor.getColumnIndex(KEY_BEHAVIOR_ID)));
            behavior.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            behavior.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            behavior.setRoom_id(cursor.getString(cursor.getColumnIndex(KEY_ROOM_ID)));
            behavior.setFreq(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_FREQ))));
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_IS_ACTIVE))) == 1){
                behavior.setActive(true);
            }else{
                behavior.setActive(false);
            }
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_LAST_ACTIVE))) == 1){
                behavior.setLastActive(true);
            }else{
                behavior.setLastActive(false);
            }
            if(Integer.parseInt(cursor.getString(cursor.getColumnIndex(KEY_DOL))) == 1){
                behavior.setDol(true);
            }else{
                behavior.setDol(false);
            }
            behaviorList.add(behavior);
        }
        return  behaviorList;
    }

    // Delete Behavior Details
    public void DeleteBehavior(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Behaviors, KEY_USERNAME+" = ?",new String[]{user});
        db.close();
    }

    // Update Behavior Details
    public int UpdateBehaviorDetails(Behavior bh){
        bh.setActive(false);
        bh.setDol(false);
        bh.setLastActive(false);
        int f = bh.getFreq();
        bh.setFreq(f+1);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_IS_ACTIVE, ""+bh.isActive());
        cVals.put(KEY_LAST_ACTIVE, ""+bh.isLastActive());
        cVals.put(KEY_DOL, ""+bh.isDol());
        cVals.put(KEY_FREQ, ""+bh.getFreq());

        int count = db.update(TABLE_Behaviors, cVals, KEY_BEHAVIOR_ID+" = ?",new String[]{bh.getBehavior_id()});
        return  count;
    }

}
