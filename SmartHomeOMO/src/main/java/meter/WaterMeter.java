package meter;

import alerts.Alert;
import devices.Consumption;
import devices.ConsumptionType;
import devices.Device;
import house.House;
import observer.Observable;
import panel.Controller;

/**
 * Creates and manages water meter objects.
 */
public class WaterMeter implements Meter, Sensor {

    public WaterMeter(House house) {
        for (Device device: house.getDeviceList()) {
            if (device.getConsumptionType() == ConsumptionType.WATER) {
                device.attach(this);
            }
        }
    }

    /**
     * Updates design pattern, that creates alerts {@link Alert} and device state report.
     *
     * @param observable is instance of Observable interface
     */
    @Override
    public void update(Observable observable) {
        if (observable instanceof Device) {
            newConsumption(new Consumption(ConsumptionType.WATER,((Device) observable).getConsumption(), ((Device) observable).getActualFloor(), ((Device) observable).getActualRoom(), (Device) observable,this));
        }
    }

    /**
     * Adds new consumption {@link Consumption} to controller.
     *
     * @param consumption is a new consumption
     */
    @Override
    public void newConsumption(Consumption consumption) {
        Controller.getInstance().updateFromMeter(consumption);
    }
}
