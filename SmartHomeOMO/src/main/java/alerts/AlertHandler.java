package alerts;

import alerts.Alert;

/**
 * Interface for classes that can affect alerts.
 */
public interface AlertHandler {
    boolean handleAlert(Alert alert);
}
