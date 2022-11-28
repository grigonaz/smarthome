package meter;

import alerts.Alert;
import events.Notification;
import events.NotificationType;
import house.House;
import house.Street;
import observer.Observable;
import panel.Controller;

/**
 * Creates and manages temperature sensor objects.
 */
public class TemperatureSensor implements Sensor {

    private House house;
    private int temperature = 20;

    public TemperatureSensor(House house) {
        this.house = house;
        house.getStreet().attach(this);
        setTemperature(house.getStreet().getTemperature());
    }

    /**
     * Updates design pattern, that creates alerts {@link Alert} and device state report.
     *
     * @param observable is instance of Observable interface
     */
    @Override
    public void update(Observable observable) {
        if (observable instanceof Street) {
            newNotification(new Notification(NotificationType.TEMPERATURE_CHANGE,null,null,this,this));
            control(house.getStreet().getTemperature());
        }
    }

    /**
     * Implements temperature control.
     *
     * @param temperature is actual temperature
     */
    private void control(int temperature) {
        if (temperature < 15) {
            Controller.getInstance().coldControl();
        }else if (temperature < 22) {
            Controller.getInstance().warmControl();
        } else {
            Controller.getInstance().hotControl();
        }
    }

    /**
     * Adds new notification {@link Notification} to controller.
     *
     * @param notification is a new notification
     */
    private void newNotification(Notification notification) {
        Controller.getInstance().newNotification(notification);
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "TemperatureSensor";
    }
}
