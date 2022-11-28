package events;

import house.Floor;
import house.Room;

/**
 * Contains information about events' source and target.
 */
public abstract class Event {

    private int round;
    protected Room sourceRoom;
    protected Floor sourceFloor;
    protected EventObject source;
    protected EventObject target;

    public Event() {}

    public int getRound() {
        return round;
    }

    public Room getSourceRoom() {
        return sourceRoom;
    }

    public Floor getSourceFloor() {
        return sourceFloor;
    }

    public Object getSource() {
        return source;
    }

    public Object getTarget() {
        return target;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setTarget(EventObject target) {
        this.target = target;
    }
}
