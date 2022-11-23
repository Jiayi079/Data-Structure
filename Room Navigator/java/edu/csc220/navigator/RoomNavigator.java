package edu.csc220.navigator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;

public class RoomNavigator {

    /**
     * Returns true if and only if a path exists between the start room and the end room, based on the description of
     * rooms provided by the allRooms list. The start and end rooms are specified by their names.
     */
    public boolean doesPathExist(ArrayList<Room> allRooms, String startRoom, String endRoom) {
        // TODO: Implement. Step 1: create and populate your Room map with everything from the rooms list.

        HashMap<String, ArrayList<String>> roomMap = new HashMap<>();

        // get each roomName and the connectedRoom to the roomMap
        for (int i = 0; i < allRooms.size(); i++) {
            Room room = allRooms.get(i);
            ArrayList<String> connectedRooms = room.getConnectedRooms();
            roomMap.put(room.getName(), connectedRooms);
        }

        // TODO: Implement. Step 2: create your breadth-first search queue and start it off with the initial room.

        Queue<String> room = new ArrayDeque<>();
        room.add(startRoom);

        HashSet<String> visited = new HashSet<>();

        // TODO: Implement. Step 3: in a loop, repeatedly process the next Room in the queue.

        while(!room.isEmpty()) {

            String firstRoomName = room.remove();


            // check if the firstRoomName has already visited
            if (visited.contains(firstRoomName)) {
                continue;
            } else {
                visited.add(firstRoomName);
            }


            // check if the firstName is the endRoom, loop break, return true
            if (firstRoomName.equals(endRoom)) {
                return true;
            } else {// if not, keep going

                // get first room name in the queue
                ArrayList<String> connect = roomMap.get(firstRoomName);

                // put every connect room into queue
                for (int i = 0; i < connect.size(); i++) {
                    room.add(connect.get(i));
                }
            }
        }
        return false;
    }

    /** Program execution begins here. */
    public static void main(String[] args) {
        ArrayList<Room> allRooms = readRooms();
        RoomNavigator navigator = new RoomNavigator();

        // Paths between rooms that are on the same floor (expecting 'true'):
        lookForPath(navigator, allRooms, "Larder", "Gardens");
        lookForPath(navigator, allRooms, "Upper Landing", "Attic");
        lookForPath(navigator, allRooms, "Chapel", "Wine Cellar");

        // Paths between rooms that are on different floors (expecting 'false'):
        lookForPath(navigator, allRooms, "Patio", "Game Room");
        lookForPath(navigator, allRooms, "Tower", "Crypt");
        lookForPath(navigator, allRooms, "Underground Lake", "Library");
    }

    /**
     * Reads the "layout.txt" file and creates a list of Room objects with their connections set up accordingly. You
     * should not modify this method in any way.
     */
    private static ArrayList<Room> readRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("layout.txt"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.strip().isEmpty()) {
                    continue;
                }

                int splitIndex = line.indexOf(';');
                if (splitIndex < 0) {
                    throw new RuntimeException(
                            "Invalid layout file! Missing ';' to separate room from its connected neighbors:\n" + line);
                }

                Room currentRoom = new Room(line.substring(0, splitIndex).strip());
                for (String connectedRoom: line.substring(splitIndex + 1).split(",")) {
                    currentRoom.addConnectedRoom(connectedRoom.strip());
                }
                rooms.add(currentRoom);
            }
            bufferedReader.close();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

        return rooms;
    }

    /**
     * Tests your doesPathExist implementation against the given start and end rooms. You can run your own check by
     * calling lookForPath in main, similar to the existing calls.
     */
    private static void lookForPath(
            RoomNavigator navigator, ArrayList<Room> allRooms, String startRoom, String endRoom) {
        System.out.printf(
                "Path from '%s' to '%s': %s%n",
                startRoom,
                endRoom,
                navigator.doesPathExist(allRooms, startRoom, endRoom));
    }
}
