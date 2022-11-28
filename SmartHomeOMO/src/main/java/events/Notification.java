package events;

import house.Floor;
import house.Room;

/**
 * Creates and manages notifications.
 */
public class Notification extends Event{

    private NotificationType type;

    public Notification(NotificationType infoType, Floor floor, Room room, EventObject source,EventObject target) {
        type = infoType;
        this.sourceFloor = floor;
        this.sourceRoom = room;
        this.source = source;
        this.target = target;
    }

    public NotificationType getType() {
        return type;
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }

    @Override
    public String toString() {
        return "Notification: " + type.toString() + " | " + source.toString() + " => " + target.toString();
    }
}
