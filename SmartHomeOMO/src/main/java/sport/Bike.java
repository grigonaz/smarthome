package sport;

import creature.Person;
import devices.Usable;
import house.Room;

/**
 * Creates and manages bike objects.
 */
public class Bike extends SportEquipment {

    private int actualRidingDuration = 0;
    private final int maxRidingDuration = 3;

    public Bike(String brand, Room location) {
        super(brand, location);
    }

    @Override
    public String toString() {
        return "Ski:" + brand;
    }

    /**
     * Implements equipment using.
     *
     * @param person is a person that use device
     * @return using state
     */
    @Override
    public Usable use(Person person) {
        isBusy = true;
        if (actualRidingDuration < maxRidingDuration) {
            actualRidingDuration++;
            return this;
        }
        actualRidingDuration = 0;
        return null;
    }
}
