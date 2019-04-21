package com.example.projectbeacon.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectbeacon.Model.Beacon;

import java.util.ArrayList;

public class BeaconSqlite extends SQLiteOpenHelper {
//    private String beacon_id;
//    private String username;
//    private String home_name;
//    private String room_id;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "beacondb";
    private static final String TABLE_Beacons = "beacondetails";
    private static final String KEY_ID = "id";
    private static final String KEY_BEACONE_ID = "beacon_id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_HOME_NAME = "home_name";
    private static final String KEY_ROOM_ID = "room_id";

    public BeaconSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Beacons + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_BEACONE_ID + " TEXT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_HOME_NAME + " TEXT,"
                + KEY_ROOM_ID + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Beacons);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new Beacon Details
    public void insertBeaconDetails(Beacon bc){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_BEACONE_ID, bc.getBeacon_id());
        cValues.put(KEY_USERNAME, bc.getUsername());
        cValues.put(KEY_HOME_NAME, bc.getHome_name());
        cValues.put(KEY_ROOM_ID, bc.getRoom_id());
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Beacons,null, cValues);
        db.close();
    }
    // Get Beacon Details
//    public ArrayList<HashMap<String, String>> GetBeacons(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<HashMap<String, String>> beaconList = new ArrayList<>();
//        String query = "SELECT beacon_id, username, home_name, room_id FROM "+ TABLE_Beacons;
//        Cursor cursor = db.rawQuery(query,null);
//        while (cursor.moveToNext()){
//            HashMap<String,String> beacon = new HashMap<>();
//            beacon.put("beacon_id",cursor.getString(cursor.getColumnIndex(KEY_BEACONE_ID)));
//            beacon.put("username",cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
//            beacon.put("home_name",cursor.getString(cursor.getColumnIndex(KEY_HOME_NAME)));
//            beacon.put("room_id",cursor.getString(cursor.getColumnIndex(KEY_ROOM_ID)));
//            beaconList.add(beacon);
//        }
//        return  beaconList;
//    }

    //Get All Beacon Details
    public ArrayList<Beacon> GetAllBeacons(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Beacon> beaconList = new ArrayList<>();
        String query = "SELECT beacon_id, username, home_name, room_id FROM "+ TABLE_Beacons;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Beacon beacon = new Beacon();
            beacon.setBeacon_id(cursor.getString(cursor.getColumnIndex(KEY_BEACONE_ID)));
            beacon.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            beacon.setHome_name(cursor.getString(cursor.getColumnIndex(KEY_HOME_NAME)));
            beacon.setRoom_id(cursor.getString(cursor.getColumnIndex(KEY_ROOM_ID)));
            beaconList.add(beacon);
        }
        return  beaconList;
    }

    //Get All Beacon By Username Details
    public ArrayList<Beacon> GetAllBeaconsByUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Beacon> beaconList = new ArrayList<>();
        String query = "SELECT beacon_id, username, home_name, room_id FROM "
                + TABLE_Beacons +" where username = ?";
        Cursor cursor = db.rawQuery(query,new String[] {username});
        while (cursor.moveToNext()){
            Beacon beacon = new Beacon();
            beacon.setBeacon_id(cursor.getString(cursor.getColumnIndex(KEY_BEACONE_ID)));
            beacon.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            beacon.setHome_name(cursor.getString(cursor.getColumnIndex(KEY_HOME_NAME)));
            beacon.setRoom_id(cursor.getString(cursor.getColumnIndex(KEY_ROOM_ID)));
            beaconList.add(beacon);
        }
        return  beaconList;
    }

    // Delete Beacon Details
    public void DeleteBeacon(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Beacons, KEY_USERNAME+" = ?",new String[]{user});
        db.close();
    }

    // Update Beacon Details
    public int UpdateActivityDetails(Beacon bc){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_USERNAME, bc.getUsername());
        cVals.put(KEY_HOME_NAME, bc.getHome_name());
        cVals.put(KEY_ROOM_ID, bc.getRoom_id());
        int count = db.update(TABLE_Beacons, cVals, KEY_BEACONE_ID+" = ?",new String[]{bc.getBeacon_id()});
        return  count;
    }

}
