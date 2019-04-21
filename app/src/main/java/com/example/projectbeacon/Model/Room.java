package com.example.projectbeacon.Model;

public class Room {
    private String username;
    private String room_id;
    private String room_type;
    private String room_name;
    private String floor;

    public Room(){}

    public Room(String username, String room_id, String room_type, String room_name, String floor) {
        this.username = username;
        this.room_id = room_id;
        this.room_type = room_type;
        this.room_name = room_name;
        this.floor = floor;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }
}
