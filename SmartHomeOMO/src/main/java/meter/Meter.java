package meter;

import devices.Consumption;

/**
 * Interface for meters.
 */
public interface Meter {
    void newConsumption(Consumption consumption);
}
