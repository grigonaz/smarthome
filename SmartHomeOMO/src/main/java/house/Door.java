package house;

import events.EventObject;
import events.Notification;
import events.NotificationType;
import panel.Controller;

/**
 * Creates and manages door objects.
 */
public class Door implements EventObject {

    private Room room;
    private boolean isOpened = false;

    public Door(Room room) {
        this.room = room;
        Controller.getInstance().setDoor(this);
    }

    /**
     * Opens door.
     */
    public void open() {
        Controller.getInstance().newNotification(new Notification(NotificationType.DOOR_IS_OPENED,room.getFloor(),room,this,this));
        isOpened = true;
    }

    /**
     * Closes door.
     */
    public void close() {
        Controller.getInstance().newNotification(new Notification(NotificationType.DOOR_IS_CLOSED,  room.getFloor(), room, this,this));
        isOpened = false;
    }

    @Override
    public String toString() {
        return "Door";
    }
}
