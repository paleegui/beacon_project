package com.example.projectbeacon.Model;

public class Time {
    private String time_id;
    private String from_time;
    private String to_time;

    public Time(){}

    public Time(String time_id, String from_time, String to_time) {
        this.time_id = time_id;
        this.from_time = from_time;
        this.to_time = to_time;
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }

    public String getFrom_time() {
        return from_time;
    }

    public void setFrom_time(String from_time) {
        this.from_time = from_time;
    }

    public String getTo_time() {
        return to_time;
    }

    public void setTo_time(String to_time) {
        this.to_time = to_time;
    }
}
