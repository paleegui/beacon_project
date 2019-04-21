package com.example.projectbeacon.Model;

public class ChangeRoom {
    private String cr_rule_id;
    private String room_type;
    private int time_spend;
    private String time_id;

    public ChangeRoom(){}

    public ChangeRoom(String cr_rule_id, String room_id, int time_spend, String time_id) {
        this.cr_rule_id = cr_rule_id;
        this.room_type = room_id;
        this.time_spend = time_spend;
        this.time_id = time_id;
    }

    public String getCr_rule() {
        return cr_rule_id;
    }

    public void setCr_rule(String cr_rule) {
        this.cr_rule_id = cr_rule;
    }

    public String getRoom_id() {
        return room_type;
    }

    public void setRoom_id(String room_id) {
        this.room_type = room_id;
    }

    public int getTime_spend() {
        return time_spend;
    }

    public void setTime_spend(int time_spend) {
        this.time_spend = time_spend;
    }

    public String getTime_id() {
        return time_id;
    }

    public void setTime_id(String time_id) {
        this.time_id = time_id;
    }
}
