package com.example.projectbeacon.Model;

public class Activity {
    private String act_no;
    private String username;
    private String act_name;
    private String time_set;
    private String act_desc;
    private boolean act_state;

    public Activity(){}

    public Activity(String act_no, String username, String act_name, String time_set, String act_desc, boolean act_state) {
        this.act_no = act_no;
        this.username = username;
        this.act_name = act_name;
        this.time_set = time_set;
        this.act_desc = act_desc;
        this.act_state = act_state;
    }

    public String getAct_no() {
        return act_no;
    }

    public void setAct_no(String act_no) {
        this.act_no = act_no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }

    public String getTime_set() {
        return time_set;
    }

    public void setTime_set(String time_set) {
        this.time_set = time_set;
    }

    public String getAct_desc() {
        return act_desc;
    }

    public void setAct_desc(String act_desc) {
        this.act_desc = act_desc;
    }

    public boolean isAct_complete() {
        return act_state;
    }

    public void setAct_state(boolean act_state) {
        this.act_state = act_state;
    }
}
