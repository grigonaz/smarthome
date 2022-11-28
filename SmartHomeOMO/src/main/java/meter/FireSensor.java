package meter;

import alerts.Alert;
import alerts.AlertType;
import house.House;
import house.Room;
import observer.Observable;
import panel.Controller;

/**
 * Creates and manages fire sensor objects.
 */
public class FireSensor implements Sensor {

    public FireSensor(House house){
        for (Room room: house.getRoomList()) {
            room.attach(this);
        }
    }

    /**
     * Updates design pattern, that creates alerts {@link Alert} and device state report.
     *
     * @param observable is instance of Observable interface
     */
    @Override
    public void update(Observable observable) {
        if (observable instanceof  Room && ((Room) observable).isOnFire()) {
            newAlert(new Alert(AlertType.FIRE, null, null, this, null));
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
        return "FireSensor";
    }
}
