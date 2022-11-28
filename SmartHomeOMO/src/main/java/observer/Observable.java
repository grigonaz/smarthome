package observer;

import events.EventObject;

/**
 * Interface for Observer design pattern.
 */
public interface Observable extends EventObject {

    void attach(Observer observer);

    void detach(Observer observer);

    void announce();
}