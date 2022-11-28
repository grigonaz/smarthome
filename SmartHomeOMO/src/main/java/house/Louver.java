package house;

import alerts.Alert;
import alerts.AlertHandler;
import events.EventObject;
import events.Notification;
import alerts.AlertType;
import events.NotificationType;
import panel.Controller;

/**
 * Creates and manages louver objects.
 */
public class Louver implements AlertHandler, EventObject {

    private Window window;
    private boolean isPulled = false;

    public Louver(Window window) {
        this.window = window;
        addHandlerToController();
    }

    /**
     * Closes the louvers.
     */
    private void pull() {
        Controller.getInstance().newNotification(new Notification(NotificationType.LOUVERS_ON, window.getRoom().getFloor(), window.getRoom(),this,this));
        isPulled = !isPulled;
    }

    public boolean isPulled() {
        return isPulled;
    }

    @Override
    public String toString() {
        return "Louver";
    }

    /**
     * Handles the alert {@link Alert}.
     *
     * @param alert is alert that needs to be handled
     * @return handle state
     */
    @Override
    public boolean handleAlert(Alert alert) {
        if (alert.getAlertType() == AlertType.STRONG_WIND && !isPulled) {
            pull();
            return true;
        }
        return false;
    }

    private void addHandlerToController() {
        Controller.getInstance().addAlertHandler(this);
    }
}
