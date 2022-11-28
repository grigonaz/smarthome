package house;

import sport.SportEquipment;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and manages garage objects.
 */
public class Garage extends Room {

    private Auto car;
    private List<SportEquipment> sportEquipmentList;

    public Garage(String name, Floor floor, int number_of_windows) {
        super(name, floor, number_of_windows);
        sportEquipmentList = new ArrayList<>();
    }

    /**
     * Adds sport equipment to the garage.
     *
     * @param sportEquipment is an equipment that needs to be added
     */
    public void addSportEquipment(SportEquipment sportEquipment) {
        if(! sportEquipmentList.contains(sportEquipment)) {
            sportEquipmentList.add(sportEquipment);
        }
    }

    /**
     * Removes sport equipment from the garage.
     *
     * @param sportEquipment is an equipment that needs to be removed
     */
    public void deleteSportEquipment(SportEquipment sportEquipment) {
        sportEquipmentList.remove(sportEquipment);
    }

    /**
     * Adds auto to the garage.
     *
     * @param car is car that needs to be added
     */
    public void addAuto(Auto car) {
        this.car = car;
    }

    public Auto getAuto() {
        return car;
    }

    public List<SportEquipment> getSportEquipmentList() {
        return sportEquipmentList;
    }
}
