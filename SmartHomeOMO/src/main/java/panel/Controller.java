package panel;

import alerts.Alert;
import alerts.AlertHandler;
import events.*;
import devices.Consumption;
import house.Door;
import house.House;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * Controls events, alerts and house objects: doors, louvers etc.
 */
public class Controller implements Round, EventObject {

    private static Controller instance = null;
    private List<AlertHandler> alertHandlers;
    private List<Event> allEvents = new ArrayList<>();
    private Queue<Alert> alerts;
    public int roundNumber;
    private Door door;
    private House house;

    private Controller() {
        alertHandlers = new ArrayList<>();
        alerts = new LinkedList<>();
    }

    /**
     * Singleton design pattern.
     *
     * @return instance of Controller
     */
    public static Controller getInstance() {
        if (instance == null){
            instance = new Controller();
        }
        return instance;
    }

    /**
     * Adds alert {@link Alert} to the list of alerts that need to be handled.
     *
     * @param alert is a new alert
     */
    private void handleAlert(Alert alert) {
        alerts.add(alert);
    }

    /**
     * Adds handler for the alert {@link Alert}.
     *
     * @param alert is alert that will be handled
     */
    public void addAlertHandler(AlertHandler alert) {
        if (!alertHandlers.contains(alert)) {
            alertHandlers.add(alert);
        }
    }

    public void setHouse(House house){
        this.house = house;
    }

    /**
     * Starts new round for controller.
     */
    @Override
    public void newRound() {
        boolean freeAlertHandlers = true;
        Alert alert = alerts.peek();
        while (freeAlertHandlers && alert != null) {
            for (AlertHandler alertHandler : alertHandlers) {
                freeAlertHandlers = alertHandler.handleAlert(alert);
            }
            alerts.remove();
            alert = alerts.peek();
        }
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public House getHouse() {
        return house;
    }

    @Override
    public String toString() {
        return "Controller";
    }

    /**
     * Set new event {@link Event}.
     *
     * @param event is actual event that should be set
     */
    private void newEvent(Event event) {
        event.setRound(roundNumber);
        allEvents.add(event);
    }

    /**
     * Updates event {@link Event}.
     *
     * @param event is actual event {@link Event} that should be updated
     */
    private void updateEvent(Event event) {
        if (allEvents.contains(event)) {
            allEvents.set(allEvents.indexOf(event), event);
        }
    }

    /**
     * Updates alert {@link Alert} state from source.
     *
     * @param alert is actual alert that should be updated
     */
    public void updateFromAlertRoom(Alert alert) {
        alert.setTarget(Controller.getInstance());
        Controller.getInstance().handleAlert(alert);
        if (!allEvents.contains(alert)) {
            newEvent(alert);
        } else {
            updateEvent(alert);
        }
    }

    /**
     * Controls temperature.
     */
    public void coldControl() {
        house.getVentilation().coldControl();
    }

    /**
     * Controls temperature.
     */
    public void warmControl() {
        house.getVentilation().warmControl();
    }

    /**
     * Controls temperature.
     */
    public void hotControl() {
        house.getVentilation().hotControl();
    }

    /**
     * Close the door.
     */
    public void closeDoor() {
        this.door.close();
    }

    /**
     * Opens the door.
     */
    public void openDoor() {
        this.door.open();
    }

    /**
     * Updates consumption {@link Consumption}.
     *
     * @param consumption is actual consumption that should be updated
     */
    public void updateFromMeter(Consumption consumption) {
        newEvent(consumption);
    }

    /**
     * Updates event {@link Event}.
     *
     * @param event is actual event that should be updated
     */
    public void updateFromCreature(Event event) {
        newEvent(event);
    }

    public void newNotification(Notification info) {
        newEvent(info);
    }

    public List<Event> getAllEvents() {
        return allEvents;
    }
}
