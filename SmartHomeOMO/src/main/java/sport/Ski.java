package sport;

import creature.Person;
import devices.Usable;
import house.Room;

/**
 * Creates and manages ski objects.
 */
public class Ski extends SportEquipment{

    private int actualSkiingDuration = 0;
    private final int maxSkiingDuration = 3;

    public Ski(String brand, Room location) {
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
        if (actualSkiingDuration < maxSkiingDuration) {
            actualSkiingDuration++;
            return this;
        }
        actualSkiingDuration = 0;
        return null;
    }
}
