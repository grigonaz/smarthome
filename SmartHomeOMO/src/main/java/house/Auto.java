package house;

import events.EventObject;
import events.Notification;
import events.NotificationType;
import creature.Person;
import devices.Usable;
import panel.Controller;

/**
 * Creates and manages Auto objects.
 */
public class Auto implements Usable, EventObject {

    private String brand;
    private Garage location;
    private boolean isPresent = true;
    private final int shoppingDuration = 5;
    private int actualShoppingDuration = 0;
    private final int carStorage = 25;
    private int actualCarStorage = 0;

    public Auto(String brand, Garage location) {
        this.brand = brand;
        this.location = location;
        location.addAuto(this);
    }

    /**
     * Implements car using.
     *
     * @param person is a person that use device
     * @return using state
     */
    @Override
    public Usable use(Person person) {
        isPresent = false;
        if (actualShoppingDuration < shoppingDuration) {
            actualShoppingDuration++;
            if (person != null) {
                newNotification(new Notification(NotificationType.SHOPPING, location.getFloor(), location,person,this));
            }
            return this;
        } else {
            isPresent = true;
            actualCarStorage = carStorage;
            return null;
        }
    }

    /**
     * Clears car storage.
     */
    public void emptyCarStorage() {
        actualCarStorage = 0;
    }

    /**
     * Adds new notification {@link Notification} to controller.
     *
     * @param notification is a new notification
     */
    private void newNotification(Notification notification) {
        Controller.getInstance().newNotification(notification);
    }

    @Override
    public String toString() {
        return "Car " + brand;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public int getActualCarStorage() {
        return actualCarStorage;
    }
}
