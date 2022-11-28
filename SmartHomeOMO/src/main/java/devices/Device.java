package devices;

import alerts.Alert;
import alerts.AlertHandler;
import events.EventObject;
import events.Notification;
import alerts.AlertType;
import events.NotificationType;
import observer.Observable;
import observer.Observer;
import creature.Person;
import devices.manuals.Manual;
import house.Floor;
import house.Room;
import panel.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static devices.DeviceState.*;

/**
 * Creates and manages devices.
 */
public abstract class Device implements Usable, Observable, Context, AlertHandler, EventObject {

    private List<Observer> observersList;
    private Floor actualFloor = null;
    protected Room actualRoom;
    private ConsumptionType consumptionType;
    private boolean isBroken = false;
    private boolean isBusy = false;
    protected String deviceName;
    private String brand;
    private State state = new StateOFF(this);
    protected int conditionOfDevice;
    protected double[] consumption;
    private Manual manual;

    public Device(String deviceName, String brand, Room location, ConsumptionType consumptionType, double[] consumption) {
        this.observersList = new ArrayList<>();
        this.deviceName = deviceName;
        this.brand = brand;
        this.actualRoom = location;
        this.consumptionType = consumptionType;
        setConsuption(consumption);
        this.manual = new Manual();
        conditionOfDevice = 70;
        location.addDevice(this);
        addHandlerToController();
    }

    /**
     * Controls device's usage. Starts new round for device
     */
    public void newRound() {
        if (new Random().nextInt(80) < 1)
            setOnFire();
        announce();
    }

    /**
     * Function let manage circuit brake issue {@link house.CircuitBrake}
     *
     * @param alert is an alert that involve following actions
     * @return alert's state (closed or not)
     */
    @Override
    public boolean handleAlert(Alert alert) {
        if (alert.getAlertType() == AlertType.CIRCUIT_BRAKE) {
            newNotification(new Notification(NotificationType.TURN_OFF_DEVICE,actualFloor, actualRoom, this,this));
            turnOFF();
            announce();
            return true;
        }
        return false;
    }



    /**
     * Adds alert handler to controller
     */
    protected void addHandlerToController() {
        Controller.getInstance().addAlertHandler(this);
    }

    /**
     * Adds new notification {@link Notification} to controller
     *
     * @param notification is a new notification
     */
    protected void newNotification(Notification notification) {
        Controller.getInstance().newNotification(notification);
    }

    public abstract Usable use(Person person);

    public abstract void changeUsingOfDevice();

    /**
     * Turns on the device
     */
    protected void turnON() {
        Controller.getInstance().newNotification(new Notification(NotificationType.TURN_ON_DEVICE,actualFloor,actualRoom,this,this));
        setState(new StateON(this));
    }

    /**
     * Turns idle the device
     */
    protected void turnIDLE() {
        Controller.getInstance().newNotification(new Notification(NotificationType.TURN_IDLE_DEVICE,actualFloor,actualRoom,this,this));
        setState(new StateIDLE(this));
    }

    /**
     * Turns off the device
     */
    protected void turnOFF() {
        Controller.getInstance().newNotification(new Notification(NotificationType.TURN_OFF_DEVICE,actualFloor,actualRoom,this,this));
        setState(new StateOFF(this));
    }

    /**
     * Sets the biggest condition number to the device
     */
    public void repairDevice() {
        conditionOfDevice = 100;
    }

    /**
     * Adds object to design pattern
     *
     * @param observer is instance of Observer interface
     */
    public void attach(Observer observer) {
        if(!observersList.contains(observer))
            observersList.add(observer);
    }

    /**
     * Removes object from design pattern
     *
     * @param observer is instance of Observer interface
     */
    public void detach(Observer observer) {
        observersList.remove(observer);

    }

    /**
     * Updates object in Observer design pattern
     */
    public void announce() {
        for (Observer observer:observersList) {
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        return brand + " of " + getClass();
    }

    public boolean isBusy() {
        return isBusy;
    }

    public void broken() {
        isBroken = true;
    }

    public boolean isBroken() {
        return isBroken;
    }

    public Floor getActualFloor() {
        return actualFloor;
    }

    public Room getActualRoom() {
        return actualRoom;
    }

    public ConsumptionType getConsumptionType() {
        return consumptionType;
    }

    public DeviceState getDeviceState() {
        return state.getStatus();
    }

    public State getState() {
        return state;
    }

    public Floor getFloor() {
        return actualRoom.getFloor();
    }

    public String getBrand() {
        return brand;
    }

    public Manual getDeviceManual() {
        return manual;
    }

    public int getConditionOfDevice() {
        return conditionOfDevice;
    }

    public double getConsumption() {
        if (this.state.getStatus() == ON) {
            return this.consumption[0];
        } else if (this.state.getStatus() == IDLE) {
            return this.consumption[1];
        } else if (this.state.getStatus() == OFF) {
            return this.consumption[2];
        } else {
            return 0;
        }
    }

    @Override
    public void setState(State state) {
        this.state = state;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    public void setOnFire() {
        actualRoom.setOnFire();
    }

    private void setConsuption(double [] consumption) {
        if (consumption.length == 3) {
            this.consumption = consumption;
        } else {
            System.out.println("Wrong array size");
        }
    }
}