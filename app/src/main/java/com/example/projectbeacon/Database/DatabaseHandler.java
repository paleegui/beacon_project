package com.example.projectbeacon.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.projectbeacon.Beacon.TheBeacon;
import com.example.projectbeacon.Util.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context ctx;

    public DatabaseHandler(Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.ctx = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BEACON_TABLE = "CREATE TABLE "
                + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_BEACON_NAME + " TEXT,"
                + Constants.KEY_HOME_NAME + " TEXT,"
                + Constants.KEY_ROOM_NAME + " TEXT,"
                + Constants.KEY_THE_BEACON + " BLOB,"
                + Constants.KEY_FLOOR + " INTEGER);";
        db.execSQL(CREATE_BEACON_TABLE);

//        String CREATE_TABLE = "CREATE TABLE "
//                + Constants.TABLE_NAME + "("
//                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
//                + Constants.KEY_BEACON_NAME + " TEXT,"
//                + Constants.KEY_HOME_NAME + " TEXT,"
//                + Constants.KEY_ROOM_NAME + " TEXT,"
//                + Constants.KEY_ROOM_NAME + " BLOB,"
//                + Constants.KEY_FLOOR + " INTEGER);";
//        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
    }

    public void addBeacon(TheBeacon beacon){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        Gson gson = new Gson();

        values.put(Constants.KEY_BEACON_NAME,beacon.getBeaconName());
        values.put(Constants.KEY_HOME_NAME, beacon.getHomeName());
        values.put(Constants.KEY_FLOOR, beacon.getFloor());
        values.put(Constants.KEY_ROOM_NAME, beacon.getRoomName());
        values.put(Constants.KEY_THE_BEACON, gson.toJson(beacon).getBytes());

        //insert the row
        db.insert(Constants.TABLE_NAME, null, values);
        Log.d("Save!!", "Saved to DB");

    }

    public List<TheBeacon> getAllbeacons(){
        SQLiteDatabase db = getReadableDatabase();
        List<TheBeacon> beaconList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME, new String[]{
                Constants.KEY_ID, Constants.KEY_BEACON_NAME, Constants.KEY_HOME_NAME,
                Constants.KEY_ROOM_NAME,Constants.KEY_FLOOR,Constants.KEY_THE_BEACON}, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                //convert json to Object
                byte[] blob = cursor.getBlob(cursor.getColumnIndex(Constants.KEY_THE_BEACON));
                String json = new String(blob);
                Gson gson = new Gson();
                TheBeacon beacon = gson.fromJson(json, new TypeToken<TheBeacon>()
                {}.getType());
                beaconList.add(beacon);
            } while (cursor.moveToNext());
        }

        return beaconList;
    }

    public int updateBeacon(){
        return 0;
    }

    public void deleteBeacon(){

    }

    //Get count
    public int getGroceriesCount() {
        String coutQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(coutQuery, null);

        return cursor.getCount();
    }

}
