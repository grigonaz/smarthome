package sport;

import events.EventObject;
import creature.Person;
import devices.Usable;
import house.Room;

/**
 * Abstract class for sport equipment.
 */
public abstract class SportEquipment implements Usable, EventObject {

    protected String brand;
    protected Room location;
    protected boolean isBusy = false;

    public SportEquipment(String brand, Room location) {
        this.brand = brand;
        this.location = location;
    }

    public Room getLocation() {
        return location;
    }

    public void setLocation(Room location) {
        this.location = location;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public abstract String toString();

    @Override
    public abstract Usable use(Person person);

    public boolean isBusy() {
        return isBusy;
    }
}
