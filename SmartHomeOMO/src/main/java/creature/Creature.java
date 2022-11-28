package creature;

import events.EventObject;
import events.Notification;
import devices.Usable;
import house.Floor;
import house.House;
import house.Room;
import panel.Controller;
import panel.Round;

import java.util.Random;

/**
 * Abstract class for creatures.
 */
public abstract class Creature implements Round, EventObject {

    protected Usable usingTarget = null;
    protected Room actualRoom = null;
    protected boolean busy = false;
    protected House house = null;
    protected String name;
    protected Role role;

    public Creature(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    /**
     * Changes housemate's or pet's room.
     *
     * @param room is new room
     */
    public void changeRoom(Room room) {
        actualRoom.removeCreature(this);
        actualRoom = room;
        actualRoom.addCreature(this);
    }

    /**
     * Adding new creature to the house.
     * If creature has role DAD or MAM {@link Role}, function adds creature as alert handler.
     *
     * @param house is an actual creature's house
     */
    public void moveToHouse(House house) {
        this.house = house;
        actualRoom = house.getRoomList().get(new Random().nextInt(house.getRoomList().size()));
        actualRoom.addCreature(this);

        if (role == Role.DAD || role == Role.MOM) {
            addAlertHandler(this);
        }
    }

    /**
     * Sets up creature {@link Creature} as an alert handler.
     *
     * @param per is a creature
     */
    protected void addAlertHandler(Creature per) {
        if (per instanceof Person) {
            ((Person) per).addHandlerToController();
        }
    }

    /**
     * Creates and updates person's notification {@link Notification}.
     *
     * @param notification is new notification
     */
    public void newNotification(Notification notification) {
        Controller.getInstance().updateFromCreature(notification);
    }

    @Override
    public abstract void newRound();

    @Override
    public String toString() {
        return name;
    }

    public boolean isBusy() {
        return busy;
    }

    public Floor getFloor() {
        return actualRoom.getFloor();
    }

    public void setOnFire() {
        actualRoom.setOnFire();
    }
}
