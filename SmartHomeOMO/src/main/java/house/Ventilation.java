package house;

/**
 * Creates and manages ventilation object.
 */
public class Ventilation {

    private House house;

    public Ventilation(House house) {
        this.house = house;
    }

    /**
     * Controls temperature.
     */
    public void coldControl() {
        house.getTemperatureSensor().setTemperature(house.getTemperatureSensor().getTemperature()+5);
    }

    /**
     * Controls temperature.
     */
    public void warmControl() {
        house.getTemperatureSensor().setTemperature(house.getTemperatureSensor().getTemperature()-5);
    }

    /**
     * Controls temperature.
     */
    public void hotControl() {
        house.getTemperatureSensor().setTemperature(house.getTemperatureSensor().getTemperature()-10);
    }

    @Override
    public String toString() {
        return "Ventilation";
    }
}
