package com.example.projectbeacon.Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OffScreenTime {
    private String username;
    private Date time;
    private String pattern = "yyyy/MM/dd HH:mm:ss";

    public OffScreenTime(){}

    public OffScreenTime(String username, Date time) {
        this.username = username;
        SimpleDateFormat inputFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = inputFormat.parse(time.toString());
            this.time = date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public long getTimeMillis(){
        long millis = this.time.getTime();
        return millis;
    }
}
