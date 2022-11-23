package edu.csc220.navigator;

import java.util.ArrayList;

/**
 * Represents a single room in the RoomNavigator program. Each room keeps track of a name, as well as a list of the
 * names of the other rooms it's connected to.
 */
public class Room {
    private String name;
    private ArrayList<String> connectedRooms;

    public Room(String name) {
        this.name = name;
        this.connectedRooms = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getConnectedRooms() {
        return new ArrayList<>(connectedRooms);
    }

    public void addConnectedRoom(String roomName) {
        connectedRooms.add(roomName);
    }
}
