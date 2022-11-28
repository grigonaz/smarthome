package house;

import events.EventObject;
import observer.Observable;
import observer.Observer;
import panel.Round;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implements street as an object.
 */
public class Street implements Observable, Round, EventObject {

    private List<Observer> observers;
    private boolean windy;
    private int temperature = 20;

    public Street() {
        observers = new ArrayList<>();
    }

    /**
     * Starts new round for the street.
     */
    @Override
    public void newRound() {
        windy = new Random().nextInt(50)<10;
        temperature = new Random().nextInt(50)-20;
        announce();
    }

    /**
     * Adds object to design pattern.
     *
     * @param observer is instance of Observer interface
     */
    @Override
    public void attach(Observer observer) {
        if (! observers.contains(observer)) {
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
        for (Observer observer: observers) {
            observer.update(this);
        }
    }

    public boolean isWindy() {
        return windy;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}
