package devices;

import events.Event;
import events.EventObject;
import house.Floor;
import house.Room;

/**
 * Creates and manages device's consumption.
 */
public class Consumption extends Event {

    private double consumption;
    private ConsumptionType type;

    public Consumption(ConsumptionType consumptionType, double consumption,
                       Floor floor, Room room, EventObject source, EventObject target) {
        this.type = consumptionType;
        this.sourceFloor = floor;
        this.sourceRoom = room;
        this.consumption = consumption;
        this.source = source;
        this.target = target;
    }

    @Override
    public String toString() {
        return "Consumption: " + consumption + " | " + source.toString() + " --> " + target.toString();
    }

    public double getConsumption() {
        return consumption;
    }

    public ConsumptionType getType() {
        return type;
    }
}
