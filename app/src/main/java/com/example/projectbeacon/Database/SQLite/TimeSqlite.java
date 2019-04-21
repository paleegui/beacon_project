package com.example.projectbeacon.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectbeacon.Model.Time;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class TimeSqlite extends SQLiteOpenHelper {
//    private String time_id;
//    private String from_time;
//    private String to_time;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "timedb";
    private static final String TABLE_Times = "timedetails";
    private static final String KEY_ID = "id";
    private static final String KEY_TIME_ID = "time_id";
    private static final String KEY_TIME_FROM = "from_time";
    private static final String KEY_TIME_TO = "to_time";

    public TimeSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Times + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_TIME_ID + " TEXT,"
                + KEY_TIME_FROM + " TEXT,"
                + KEY_TIME_TO + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Times);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    public void insertTimeDetails(){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();

        Date date1 = new Date();
        Date date2 = new Date();
        int front[] = {5,6,9,12,13,16,18,20,21};
        int back[] = {6,9,12,13,16,18,20,21,5};

        for(int i=0; i<front.length; i++){
            if(i==10){

            } else {
                date1.setHours(front[i]);
                date1.setMinutes(1);
                date2.setHours(back[i]);
                date2.setMinutes(1);
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
                String str1 = formatter.format(date1);
                String str2 = formatter.format(date2);

                ContentValues cValues = new ContentValues();
                cValues.put(KEY_TIME_ID, "T"+i);
                cValues.put(KEY_TIME_FROM, ""+str1);
                cValues.put(KEY_TIME_TO, ""+str2);

                // Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(TABLE_Times,null, cValues);

            }
            ContentValues cValues = new ContentValues();
            cValues.put(KEY_TIME_ID, "T9");
            cValues.put(KEY_TIME_FROM, "25:00");
            cValues.put(KEY_TIME_TO, "25:00");
            long newRowId = db.insert(TABLE_Times,null, cValues);
        }

        db.close();
    }
    // Get time Details
    public ArrayList<Time> GetTimes(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Time> timeList = new ArrayList<>();
        String query = "SELECT time_id, from_time, to_time FROM "+ TABLE_Times;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Time t = new Time();
            t.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            t.setFrom_time(cursor.getString(cursor.getColumnIndex(KEY_TIME_FROM)));
            t.setTo_time(cursor.getString(cursor.getColumnIndex(KEY_TIME_TO)));
            timeList.add(t);
        }
        return  timeList;
    }

    // Get time Details
    public Time GetTime(String timeID){
        SQLiteDatabase db = this.getWritableDatabase();
        Time timeList[] = new Time[1];
        String query = "SELECT time_id, from_time, to_time FROM "
                + TABLE_Times +" where time_id = ?";
        Cursor cursor = db.rawQuery(query,new String[] {timeID});
        while (cursor.moveToNext()){
            Time t = new Time();
            t.setTime_id(cursor.getString(cursor.getColumnIndex(KEY_TIME_ID)));
            t.setFrom_time(cursor.getString(cursor.getColumnIndex(KEY_TIME_FROM)));
            t.setTo_time(cursor.getString(cursor.getColumnIndex(KEY_TIME_TO)));

            if(t.getTime_id().equalsIgnoreCase(timeID)){
                timeList[0] = t;
            }
        }
        return  timeList[0];
    }

    // Delete Time Details
    public void DeleteTime(String time_id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Times, KEY_TIME_ID+" = ?",new String[]{time_id});
        db.close();
    }


}
