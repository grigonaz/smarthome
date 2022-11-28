package alerts;

import events.Event;
import events.EventObject;
import house.Floor;
import house.Room;

/**
 * Creates and manages alerts.
 */
public class Alert extends Event {

    private AlertType type;

    public Alert(AlertType alertType, Floor floor, Room room, EventObject source, EventObject target){
        this.type = alertType;
        this.sourceFloor = floor;
        this.sourceRoom = room;
        this.source = source;
        this.target = target;

    }

    @Override
    public String toString() {
        if(target == null){
            return "Alert: " + type.toString() + " | " + source.toString() + " was not handled ";
        } else {
            return "Alert: " + type.toString() + " | " + source.toString() + " => " + target.toString();
        }
    }

    public AlertType getAlertType() {
        return type;
    }
}
