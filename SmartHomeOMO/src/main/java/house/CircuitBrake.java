package house;

import alerts.Alert;
import alerts.AlertType;
import meter.ElectricityMeter;
import meter.Sensor;
import observer.Observable;
import panel.Controller;

/**
 * Creates circuit brake issue.
 */
public class CircuitBrake implements Sensor {

    private boolean action;
    private final static double maxElectricalCapacity = 30;

    public CircuitBrake(House house) {
        house.setCircuitBreakes(this);
    }

    /**
     * Updates design pattern, that creates alerts {@link Alert} and device state report.
     *
     * @param observable is instance of Observable interface
     */
    @Override
    public void update(Observable observable) {
        if (observable instanceof ElectricityMeter && ((ElectricityMeter) observable).getRoundConsumption() > maxElectricalCapacity) {
            action = true;
            newAlert(new Alert(AlertType.CIRCUIT_BRAKE,null,null,this,null));
        }
    }

    /**
     * Creates and updates alert {@link Alert} from device.
     *
     * @param alert is new alert
     */
    private void newAlert(Alert alert) {
        Controller.getInstance().updateFromAlertRoom(alert);
    }

    public void replace() {
        action = false;
    }

    public boolean isAction() {
        return action;
    }
}
