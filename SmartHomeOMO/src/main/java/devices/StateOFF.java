package devices;

/**
 * Implements state OFF form State Machine design pattern.
 */
public class StateOFF extends State{

    public StateOFF(Context context) {
        super(context);
        this.state = DeviceState.OFF;
    }

    /**
     * Changes device's state to the next
     */
    protected void changeToNextState() {
        context.setState(new StateON(context));
    }
}
