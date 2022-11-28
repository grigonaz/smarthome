package devices;

/**
 * Creates device's state for State Machine design pattern.
 */
public abstract class State {

    protected Context context;
    protected DeviceState state;

    public State(Context context) {
        this.context = context;
    }

    abstract protected void changeToNextState();

    protected Context getContext() {
        return context;
    }

    protected void setContext(Context context) {
        this.context = context;
    }

    protected DeviceState getStatus() {
        return state;
    }
}
