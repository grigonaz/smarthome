package devices;

/**
 * Implements state IDLE form State Machine design pattern.
 */
public class StateIDLE extends State {

    public StateIDLE(Context context) {
        super(context);
        this.state = DeviceState.IDLE;
    }

    /**
     * Changes device's state to the next
     */
    protected void changeToNextState() {
        context.setState(new StateOFF(context));
    }
}