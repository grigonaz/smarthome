package devices;

import events.Notification;
import events.NotificationType;
import creature.Person;
import house.Room;

/**
 * Creates and manages CoffeeMaker device.
 */
public class CoffeeMaker extends Device {

    private int actualUsingDuration = 0;
    private final static int maxUsingDuration = 2;

    public CoffeeMaker(String name, String brand, Room location, ConsumptionType consumptionType, double[] consumption) {
        super(name, brand, location, consumptionType, consumption);
    }

    /**
     * Implements device using.
     *
     * @param person is a person that use device
     * @return using state
     *
     * @see CoffeeMaker#makingCoffeeNotify(Person)
     */
    @Override
    public Usable use(Person person) {
        setBusy(true);

        switch (getDeviceState()) {
            case OFF:
            case IDLE:
                turnON();
                return this;
            case ON:
                return useCoffeeMaker(person);
        }
        return null;
    }

    /**
     * Notify another housemates about person using device.
     *
     * @param person is person that using device
     */
    private void makingCoffeeNotify(Person person) {
        newNotification(new Notification(NotificationType.MAKING_COFFEE, getActualFloor(), getActualRoom(), person,this));
    }

    /**
     * Controls device's condition.
     */
    @Override
    public void changeUsingOfDevice() {
        conditionOfDevice -= 2;
        if (conditionOfDevice < 0) {
            conditionOfDevice = 0;
            broken();
        }
        announce();
    }

    @Override
    public String toString() {
        return deviceName;
    }

    /**
     * Validates using of device when device's state is ON.
     *
     * @param person using device
     * @return using state
     */
    private Usable useCoffeeMaker(Person person){
        if (actualUsingDuration < maxUsingDuration) {
            if (actualUsingDuration == 0)
                makingCoffeeNotify(person);
            actualUsingDuration++;
            return this;
        } else {
            actualUsingDuration = 0;
            setBusy(false);
            turnIDLE();
            return null;
        }
    }
}
