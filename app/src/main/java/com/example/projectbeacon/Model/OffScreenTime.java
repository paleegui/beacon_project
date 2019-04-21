package com.example.projectbeacon.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OffScreenTime {
    private String username;
    private Date time;
    private boolean isOff;
    private String pattern = "yyyy/MM/dd HH:mm:ss";

    public OffScreenTime(){}

    public OffScreenTime(String username, Date time, boolean isOff) {
        this.username = username;
        SimpleDateFormat inputFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = inputFormat.parse(time.toString());
            this.time = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.isOff = isOff;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = inputFormat.parse(time.toString());
            this.time = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void setIsOff(boolean isoff){
        this.isOff = isoff;
    }
    public int getIsOff() {
        if(isOff){
            return 1;
        }else{
            return 0;
        }
    }


    public long getTimeMillis(){
        long millis = this.time.getTime();
        return millis;
    }
}
