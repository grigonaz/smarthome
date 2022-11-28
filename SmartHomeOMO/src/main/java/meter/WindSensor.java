package meter;

import alerts.Alert;
import alerts.AlertType;
import house.House;
import house.Street;
import observer.Observable;
import panel.Controller;

/**
 * Creates and manages wind sensor objects.
 */
public class WindSensor implements Sensor {

    public WindSensor(House house) {
        house.getStreet().attach(this);
    }

    /**
     * Updates design pattern, that creates alerts {@link Alert} and device state report.
     *
     * @param observable is instance of Observable interface
     */
    @Override
    public void update(Observable observable) {
        if (observable instanceof Street) {
            if (((Street) observable).isWindy()) {
                newAlert(new Alert(AlertType.STRONG_WIND, null, null, this, null));
            }
        }
    }

    /**
     * Adds new alert {@link Alert} to controller.
     *
     * @param alert is a new alert
     */
    private void newAlert(Alert alert) {
        Controller.getInstance().updateFromAlertRoom(alert);
    }

    @Override
    public String toString() {
        return "WindSensor";
    }
}
