package com.example.projectbeacon.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.firebaseproject.model.EnterRoomTime;

public class EnterRoomTimeSqlite extends SQLiteOpenHelper {
//    private String username;
//    private Date time;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "enterrtdb";
    private static final String TABLE_EnterRTs = "enterrtdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_DATE_MILLIS = "date_millis";




    public EnterRoomTimeSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE "
                + TABLE_EnterRTs + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_DATE_MILLIS + " INTEGER"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EnterRTs);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new time Details
    public void insertEnterRTDetails(EnterRoomTime time){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_USERNAME, time.getUsername());
        cValues.put(KEY_DATE_MILLIS, time.getTimeMillis());
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_EnterRTs,null, cValues);
        db.close();
    }

    // Get time Details
    public long GetEnterRoomTime(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT date_millis FROM "+ TABLE_EnterRTs +" where username = ?";
        Cursor cursor = db.rawQuery(query,new String[] {user});
        String str = "";
        while (cursor.moveToNext()){
            str = cursor.getString(cursor.getColumnIndex(KEY_DATE_MILLIS));
        }
        return  Long.parseLong(str);
    }

    // Delete time Details
    public void DeleteEnterRoomTime(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EnterRTs, KEY_USERNAME+" = ?",new String[]{user});
        db.close();
    }

    // Update time Details
    public int UpdateEnterRTDetails(EnterRoomTime time){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_DATE_MILLIS, time.getTimeMillis());
        int count = db.update(TABLE_EnterRTs, cVals, KEY_USERNAME+" = ?",new String[]{time.getUsername()});
        return  count;
    }

}
