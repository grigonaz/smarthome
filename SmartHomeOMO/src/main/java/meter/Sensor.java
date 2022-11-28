package meter;

import events.EventObject;
import observer.Observer;

/**
 * Interface for sensors in Observer design pattern.
 */
public interface Sensor extends Observer, EventObject {
}
