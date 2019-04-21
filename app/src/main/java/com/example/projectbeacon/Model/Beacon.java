package com.example.projectbeacon.Model;

public class Beacon {
    private String beacon_id;
    private String username;
    private String home_name;
    private String room_id;

    public Beacon() {}

    public Beacon(String username, String beacon_id, String home_name, String room_id) {
        this.username = username;
        this.beacon_id = beacon_id;
        this.home_name = home_name;
        this.room_id = room_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBeacon_id() {
        return beacon_id;
    }

    public void setBeacon_id(String beacon_id) {
        this.beacon_id = beacon_id;
    }

    public String getHome_name() {
        return home_name;
    }

    public void setHome_name(String home_name) {
        this.home_name = home_name;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }
}
