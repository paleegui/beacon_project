package com.example.projectbeacon.Database.SQLite.Rule;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.firebaseproject.model.Activity;

import java.util.ArrayList;
import java.util.HashMap;

public class ISRuleSqlite extends SQLiteOpenHelper {
//    private String act_no;
//    private String username;
//    private String act_name;
//    private String time_set;
//    private String act_desc;
//    private boolean act_state;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "activitydb";
    private static final String TABLE_Activitys = "activitydetails";
    private static final String KEY_ID = "id";
    private static final String KEY_ACT_NO = "act_no";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ACT_NAME = "act_name";
    private static final String KEY_TIME_SET = "time_set";
    private static final String KEY_ACT_DESC = "act_desc";
    private static final String KEY_ACT_STATE = "act_state";

    public ISRuleSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Activitys + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ACT_NO + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_ACT_NAME + " TEXT,"
                + KEY_TIME_SET + " TEXT,"
                + KEY_ACT_DESC + " TEXT,"
                + KEY_ACT_STATE + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Activitys);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    public void insertActivityDetails(Activity ac){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_ACT_NO, ac.getAct_no());
        cValues.put(KEY_USERNAME, ac.getUsername());
        cValues.put(KEY_ACT_NAME, ac.getAct_name());
        cValues.put(KEY_TIME_SET, ac.getTime_set());
        cValues.put(KEY_ACT_DESC, ac.getAct_desc());
        cValues.put(KEY_ACT_STATE, ""+ac.isAct_complete());
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Activitys,null, cValues);
        db.close();
    }
    // Get Activity Details
    public ArrayList<HashMap<String, String>> GetActivitys(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> activityList = new ArrayList<>();
        String query = "SELECT act_no, username, act_name, time_set, act_desc, act_state FROM "+ TABLE_Activitys;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> activity = new HashMap<>();
            activity.put("act_no",cursor.getString(cursor.getColumnIndex(KEY_ACT_NO)));
            activity.put("username",cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            activity.put("act_name",cursor.getString(cursor.getColumnIndex(KEY_ACT_NAME)));
            activity.put("time_set",cursor.getString(cursor.getColumnIndex(KEY_TIME_SET)));
            activity.put("act_desc",cursor.getString(cursor.getColumnIndex(KEY_ACT_DESC)));
            activity.put("act_state",cursor.getString(cursor.getColumnIndex(KEY_ACT_STATE)));
            activityList.add(activity);
        }
        return  activityList;
    }

    // Delete Activity Details
    public void DeleteActivity(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Activitys, KEY_USERNAME+" = ?",new String[]{user});
        db.close();
    }

    // Update Activity Details
    public int UpdateActivityDetails(Activity act){
        act.setAct_state(true);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_ACT_STATE, ""+act.isAct_complete());
        int count = db.update(TABLE_Activitys, cVals, KEY_ACT_NO+" = ?",new String[]{act.getAct_no()});
        return  count;
    }

}
