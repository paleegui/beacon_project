package com.example.projectbeacon.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.firebaseproject.model.Room;

import java.util.ArrayList;
import java.util.HashMap;

public class RoomSqlite extends SQLiteOpenHelper {
//    private String username;
//    private String room_id;
//    private String room_type;
//    private String room_name;
//    private String floor;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "roomdb";
    private static final String TABLE_Rooms = "roomdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_ROOM_ID = "room_id";
    private static final String KEY_ROOM_TYPE = "room_type";
    private static final String KEY_ROOM_NAME = "room_name";
    private static final String KEY_FLOOR = "floor";

    public RoomSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Rooms + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_ROOM_ID + " TEXT,"
                + KEY_ROOM_TYPE + " TEXT,"
                + KEY_ROOM_NAME + " TEXT,"
                + KEY_FLOOR + " TEXT"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Rooms);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new room Details
    public void insertRoomDetails(Room room){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_USERNAME, room.getUsername());
        cValues.put(KEY_ROOM_ID, room.getRoom_id());
        cValues.put(KEY_ROOM_TYPE, room.getRoom_type());
        cValues.put(KEY_ROOM_NAME, room.getRoom_name());
        cValues.put(KEY_FLOOR, room.getFloor());
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Rooms,null, cValues);
        db.close();
    }
    // Get room Details
    public ArrayList<HashMap<String, String>> GetRooms(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> roomList = new ArrayList<>();
        String query = "SELECT username, room_id, room_type, room_name, floor FROM "+ TABLE_Rooms;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            HashMap<String,String> room = new HashMap<>();
            room.put("username",cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            room.put("room_id",cursor.getString(cursor.getColumnIndex(KEY_ROOM_ID)));
            room.put("room_type",cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            room.put("room_name",cursor.getString(cursor.getColumnIndex(KEY_ROOM_NAME)));
            room.put("floor",cursor.getString(cursor.getColumnIndex(KEY_FLOOR)));
            roomList.add(room);
        }
        return  roomList;
    }

    // Get Room Details
    public ArrayList<Room> GetAllRooms(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT username, room_id, room_type, room_name, floor FROM "+ TABLE_Rooms;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            Room room = new Room();
            room.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            room.setRoom_id(cursor.getString(cursor.getColumnIndex(KEY_ROOM_ID)));
            room.setRoom_type(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            room.setRoom_name(cursor.getString(cursor.getColumnIndex(KEY_ROOM_NAME)));
            room.setFloor(cursor.getString(cursor.getColumnIndex(KEY_FLOOR)));
            roomList.add(room);
        }
        return  roomList;
    }

    // Get Room By Username Details
    public ArrayList<Room> GetAllRoomsByUsername(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Room> roomList = new ArrayList<>();
        String query = "SELECT username, room_id, room_type, room_name, floor FROM "
                + TABLE_Rooms+" where username = ?";
        Cursor cursor = db.rawQuery(query,new String[] {username});
        while (cursor.moveToNext()){
            Room room = new Room();
            room.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            room.setRoom_id(cursor.getString(cursor.getColumnIndex(KEY_ROOM_ID)));
            room.setRoom_type(cursor.getString(cursor.getColumnIndex(KEY_ROOM_TYPE)));
            room.setRoom_name(cursor.getString(cursor.getColumnIndex(KEY_ROOM_NAME)));
            room.setFloor(cursor.getString(cursor.getColumnIndex(KEY_FLOOR)));
            roomList.add(room);
        }
        return  roomList;
    }

    // Delete room Details
    public void DeleteRoom(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Rooms, KEY_USERNAME+" = ?",new String[]{user});
        db.close();
    }

    // Update room Details
    public int UpdateRoomDetails(Room room){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_ROOM_TYPE, room.getRoom_type());
        cVals.put(KEY_ROOM_NAME, room.getRoom_name());
        cVals.put(KEY_FLOOR, room.getFloor());
        int count = db.update(TABLE_Rooms, cVals, KEY_ROOM_ID + " = ?",new String[]{room.getRoom_id()});
        return  count;
    }

}
