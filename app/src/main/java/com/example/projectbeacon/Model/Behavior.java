package com.example.projectbeacon.Model;

import android.content.Context;

import com.example.projectbeacon.Activities.MainActivity;
import com.example.projectbeacon.Database.SQLite.BeaconSqlite;
import com.example.projectbeacon.Database.SQLite.BehaviorSqlite;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Behavior {
    private String behavior_id;
    private String username;
    private boolean is_active;
    private boolean last_active;
    private String time_id;
    private String room_id;
    private boolean dol;
    private int freq;
    private boolean isSleep;
    private String pattern = "yyyy/MM/dd HH:mm:ss";

    public Behavior(){}

    public Behavior(String behavior_id, String username, boolean isActive, boolean lastActive, String time_id, String room_id, boolean dol, int freq, boolean isSleep) {
        this.behavior_id = behavior_id;
        this.username = username;
        this.is_active = isActive;
        this.last_active = lastActive;
        this.time_id = time_id;
        this.room_id = room_id;
        this.dol = dol;
        this.freq = freq;
        this.isSleep = isSleep;
    }

    public String getBehavior_id() {
        return behavior_id;
    }

    public void setBehavior_id(String behavior_id) {
        this.behavior_id = behavior_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int isActive() {
        if(is_active)
            return 1;
        else
            return 0;
    }

    public void setActive(boolean active) {
        is_active = active;
    }

    public int isLastActive() {
        Date currentTime = Calendar.getInstance().getTime();
//        BehaviorSqlite behaviorSqlite = new BehaviorSqlite();
//        ArrayList<Behavior> behList =
//

        if(last_active)
            return 1;
        else
            return 0;
    }

    public void setLastActive(boolean lastActive) {
        this.last_active = lastActive;
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public int isDol() {
        if(dol)
            return 1;
        else
            return 0;
    }

    public void setDol(boolean dol) {
        this.dol = dol;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public int isSleep() {
        if(isSleep)
            return 1;
        else
            return 0;
    }

    public void setSleep(boolean sleep) {
        isSleep = sleep;
    }
}
