package com.example.projectbeacon.Beacon;

import org.altbeacon.beacon.Beacon;

import java.io.Serializable;
import java.util.UUID;

public class TheBeacon implements Serializable {
    private String beaconName;
    private String roomName;
    private String floor;
    private String homeName;
    private Beacon beacon;
    private int id;

    public TheBeacon(){

    }

    public TheBeacon(String beaconName, String roomName, String floor, String homeName, Beacon beacon, int id){
        this.beaconName = beaconName;
        this.roomName = roomName;
        this.floor = floor;
        this.homeName = homeName;
        this.beacon = beacon;
        this.id = id;


    }

    public String getBeaconName() {
        return beaconName;
    }

    public void setBeaconName(String beaconName) {
        this.beaconName = beaconName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getHomeName() {
        return homeName;
    }

    public void setHomeName(String homeName) {
        this.homeName = homeName;
    }

    public Beacon getBeacon(){ return beacon; }

    public void setBeacon(Beacon beacon){ this.beacon = beacon; }

    public void setId(int id) { this.id = id; }

    public int getId(){ return id; }
}
