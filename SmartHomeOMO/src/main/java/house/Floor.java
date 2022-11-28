package house;

import java.util.ArrayList;

import java.util.List;

/**
 * Creates and manages floor objects.
 */
public class Floor {

    private String name;
    private House house;
    private int number_of_rooms;
    private List<Room> roomList;
    public Room room;

    public Floor(String name, House house) {
        this.name = name;
        this.house = house;
        this.roomList = new ArrayList<>();
        house.addFloor(this);
    }

    /**
     * Adds room to the floor.
     *
     * @param room is the room that needs to be added
     */
    public void addRoom(Room room) {
        if(!roomList.contains(room))
            roomList.add(room);
    }

    /**
     * Removes room from the floor.
     *
     * @param room is the room that needs to be removed
     */
    public void deleteRoom(Room room) {
        roomList.remove(room);
    }

    public List<Room> getRoomList() {
        return roomList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    @Override
    public String toString() {
        return name;
    }
}
