package devices;

/**
 * Implements state ON form State Machine design pattern.
 */
public class StateON extends State {

    public StateON(Context context) {
        super(context);
        this.state = DeviceState.ON;
    }

    /**
     * Changes device's state to the next
     */
    protected void changeToNextState() {
        context.setState(new StateIDLE(context));
    }
}
