package com.example.projectbeacon.Database.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.projectbeacon.Model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserSqlite extends SQLiteOpenHelper {
//    private String username;
//    private String password;
//    private String email;
//    private String tel;
//    private Date bday;
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "userdb";
    private static final String TABLE_Users = "userdetails";
    private static final String KEY_ID = "id";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASS = "password";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_TEL = "tel";
    private static final String KEY_BDAY = "bday";

    public UserSqlite(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_USERNAME + " TEXT,"
                + KEY_PASS + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_TEL + " TEXT,"
                + KEY_BDAY + " INTEGER"+ ")";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if exist
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        // Create tables again
        onCreate(db);
    }

    // **** CRUD (Create, Read, Update, Delete) Operations ***** //

    // Adding new User Details
    public void insertUserDetails(User u){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_USERNAME, u.getUsername());
        cValues.put(KEY_PASS, u.getPassword());
        cValues.put(KEY_EMAIL, u.getEmail());
        cValues.put(KEY_TEL, u.getTel());
        cValues.put(KEY_BDAY, u.getTimeMillis());
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users,null, cValues);
        db.close();
    }
    // Get Activity Details
//    public ArrayList<HashMap<String, String>> GetActivitys(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        ArrayList<HashMap<String, String>> activityList = new ArrayList<>();
//        String query = "SELECT act_no, username, act_name, time_set, act_desc, act_state FROM "+ TABLE_Activitys;
//        Cursor cursor = db.rawQuery(query,null);
//        while (cursor.moveToNext()){
//            HashMap<String,String> activity = new HashMap<>();
//            activity.put("act_no",cursor.getString(cursor.getColumnIndex(KEY_ACT_NO)));
//            activity.put("username",cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
//            activity.put("act_name",cursor.getString(cursor.getColumnIndex(KEY_ACT_NAME)));
//            activity.put("time_set",cursor.getString(cursor.getColumnIndex(KEY_TIME_SET)));
//            activity.put("act_desc",cursor.getString(cursor.getColumnIndex(KEY_ACT_DESC)));
//            activity.put("act_state",cursor.getString(cursor.getColumnIndex(KEY_ACT_STATE)));
//            activityList.add(activity);
//        }
//        return  activityList;
//    }

    private Date MilliToDate(long milli){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date d = new Date();
        d.setTime(milli);
        return d;
    }

    // Get User Details
    public ArrayList<User> GetAllUsers(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT username, password, tel, email, bday FROM "+ TABLE_Users;
        Cursor cursor = db.rawQuery(query,null);
        while (cursor.moveToNext()){
            User u = new User();
            u.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            u.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
            u.setTel(cursor.getString(cursor.getColumnIndex(KEY_TEL)));
            u.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            long num = Long.parseLong(cursor.getString(cursor.getColumnIndex(KEY_BDAY)));
            u.setBday(MilliToDate(num));
            userList.add(u);
        }
        return  userList;
    }

    // Get User By username Details
    public ArrayList<User> GetUsers(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT username, password, tel, email, bday FROM "
                       + TABLE_Users +" where username = ?";
        Cursor cursor = db.rawQuery(query,new String[] {username});
        while (cursor.moveToNext()){
            User u = new User();
            u.setUsername(cursor.getString(cursor.getColumnIndex(KEY_USERNAME)));
            u.setPassword(cursor.getString(cursor.getColumnIndex(KEY_PASS)));
            u.setTel(cursor.getString(cursor.getColumnIndex(KEY_TEL)));
            u.setEmail(cursor.getString(cursor.getColumnIndex(KEY_EMAIL)));
            long num = Long.parseLong(cursor.getString(cursor.getColumnIndex(KEY_BDAY)));
            u.setBday(MilliToDate(num));
            userList.add(u);
        }
        return  userList;
    }

    // Delete User Details
    public void DeleteUser(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Users, KEY_USERNAME+" = ?",new String[]{user});
        db.close();
    }

    // Update User Details
    public int UpdateUserDetails(User u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cVals = new ContentValues();
        cVals.put(KEY_PASS, u.getPassword());
        cVals.put(KEY_TEL, u.getTel());
        cVals.put(KEY_EMAIL, u.getEmail());
        cVals.put(KEY_BDAY, u.getTimeMillis());
        int count = db.update(TABLE_Users, cVals, KEY_USERNAME+" = ?",new String[]{u.getUsername()});
        return  count;
    }

}
