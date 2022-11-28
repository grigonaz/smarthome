package devices;

import alerts.Alert;
import alerts.AlertType;
import house.House;
import meter.Meter;
import meter.Sensor;
import observer.Observable;
import panel.Controller;

/**
 * Controls devices' condition.
 */
public class DeviceCondition implements Sensor, Meter {

    public DeviceCondition(House house){
        for (Device device: house.getDeviceList()) {
            device.attach(this);
        }
    }

    /**
     * Updates design pattern, that creates alerts {@link Alert} and device state report
     *
     * @param observable is instance of Observable interface
     *
     * @see Consumption
     */
    @Override
    public void update(Observable observable) {
        if (observable instanceof Device) {
            if (((Device) observable).isBroken()) {
                newAlert(new Alert(AlertType.BROKEN, ((Device) observable).getActualFloor(), ((Device) observable).getActualRoom(), observable, null));
            } else {
                newConsumption(new Consumption(ConsumptionType.CONDITION, ((Device) observable).getConditionOfDevice(), ((Device) observable).getActualFloor(), ((Device) observable).getActualRoom(), this, this));
            }
        }
    }

    /**
     * Creates and updates device's consumption
     *
     * @param consumption is new notification
     */
    @Override
    public void newConsumption(Consumption consumption) {
        Controller.getInstance().updateFromMeter(consumption);
    }

    /**
     * Creates and updates alert from device
     *
     * @param alert is new alert
     */
    private void newAlert(Alert alert) {
        Controller.getInstance().updateFromAlertRoom(alert);
    }
}
