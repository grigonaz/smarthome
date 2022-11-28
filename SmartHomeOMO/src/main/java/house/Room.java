package house;

import observer.Observable;
import observer.Observer;
import creature.Creature;
import devices.Device;
import meter.Sensor;
import sport.SportEquipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and manages room objects.
 */
public class Room implements Observable {

    private List<Observer> observers;
    private List<Creature> creatureList;
    private String name;
    private Floor floor;
    private boolean isOnFire = false;
    private List<Window> windowsList;
    private List<Device> deviceList;
    private List<SportEquipment> sportEquipmentList;

    public Room() {}

    public Room(String name, Floor floor, int number_of_windows) {
        this.name = name;
        this.floor = floor;
        windowsList = new ArrayList<>();
        deviceList = new ArrayList<>();
        sportEquipmentList = new ArrayList<>();

        for (int i = 1; i <= number_of_windows; i++) {
            windowsList.add(new Window(this,i));
        }

        floor.addRoom(this);
        creatureList = new ArrayList<>();
        observers = new ArrayList<>();
    }

    /**
     * Controls fire safety.
     */
    public void setOnFire(){
        this.isOnFire = true;
        announce();
    }

    /**
     * Controls fire safety.
     */
    public void putOutFire() {
        this.isOnFire = false;

    }

    /**
     * Adds creature {@link Creature} to the room.
     *
     * @param creature is someone that needs to be added
     */
    public void addCreature(Creature creature) {
        if (!creatureList.contains(creature))
            creatureList.add(creature);
    }

    /**
     * Removes creature {@link Creature} from the room.
     *
     * @param creature is someone that needs to be removed
     */
    public void removeCreature(Creature creature) {
        creatureList.remove(creature);
    }

    /**
     * Adds device {@link Device} to the room.
     *
     * @param device is device that needs to be added
     */
    public void addDevice(Device device) {
        if (!deviceList.contains(device))
            deviceList.add(device);
    }

    /**
     * Removes device {@link Device} from the room.
     *
     * @param device is device that needs to be removed
     */
    public void removeDevice(Device device) {
        deviceList.remove(device);
    }

    /**
     * Adds sport equipment {@link SportEquipment} to the room.
     *
     * @param sportEquipment is equipment that needs to be added
     */
    public void addSportEquipment(SportEquipment sportEquipment){
        if (!sportEquipmentList.contains(sportEquipment)) {
            sportEquipmentList.add(sportEquipment);
        }
    }

    /**
     * Removes sport equipment {@link SportEquipment} from the room.
     *
     * @param sportEquipment is equipment that needs to be removed
     */
    public void remove(SportEquipment sportEquipment) {
        sportEquipmentList.remove(sportEquipment);
    }

    public List<Creature> getCreatureList() {
        return creatureList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    public List<Window> getWindowsList() {
        return windowsList;
    }

    public List<SportEquipment> getSportEquipmentList() {
        return sportEquipmentList;
    }

    /**
     * Adds object to design pattern.
     *
     * @param observer is instance of Observer interface
     */
    @Override
    public void attach(Observer observer) {
        if (! observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes object from design pattern.
     *
     * @param observer is instance of Observer interface
     */
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Updates object in Observer design pattern.
     */
    @Override
    public void announce() {
        for (Observer observer:observers) {
            observer.update(this);
        }
    }
    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        return "Room " + name;
    }

    public boolean isOnFire() {
        return isOnFire;
    }
}
