package meter;

import alerts.Alert;
import devices.Consumption;
import devices.ConsumptionType;
import devices.Device;
import house.House;
import observer.Observable;
import observer.Observer;
import panel.Controller;
import panel.Round;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates and manages electricity meter objects.
 */
public class ElectricityMeter implements Sensor,Observable,Round,Meter {

    private double roundConsumption;
    private List<Observer> observers;

    public ElectricityMeter(House house) {
        observers = new ArrayList<>();

        house.setElectricityMeter(this);
        if (house.getCircuitBreakes() != null) {
            attach(house.getCircuitBreakes());
        }

        for (Device device: house.getDeviceList()) {
            if (device.getConsumptionType() == ConsumptionType.ELECTRICITY) {
                device.attach(this);
            }
        }
    }

    /**
     * Starts new round for electricity meter.
     */
    @Override
    public void newRound() {
        roundConsumption = 0;
    }

    /**
     * Updates design pattern, that creates alerts {@link Alert} and device state report.
     *
     * @param observable is instance of Observable interface
     */
    @Override
    public void update(Observable observable) {
        if (observable instanceof Device) {
            newConsumption(new Consumption(ConsumptionType.ELECTRICITY,((Device) observable).getConsumption(), ((Device) observable).getActualFloor(), ((Device) observable).getActualRoom(), observable,this));
            roundConsumption += ((Device) observable).getConsumption();
            announce();
        }
    }

    /**
     * Adds object to design pattern.
     *
     * @param observer is instance of Observer interface
     */
    @Override
    public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Removes object from design pattern.
     *
     * @param observer is instance of Observer interface
     */
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Updates object in Observer design pattern.
     */
    @Override
    public void announce() {
        for (Observer observer:observers) {
            observer.update(this);
        }
    }

    @Override
    public String toString() {
        return "Electricity meter";
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

    public double getRoundConsumption() {
        return roundConsumption;
    }
}
