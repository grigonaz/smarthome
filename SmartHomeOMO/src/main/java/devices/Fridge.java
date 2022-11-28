package devices;

import alerts.Alert;
import events.Notification;
import alerts.AlertType;
import events.NotificationType;
import creature.Person;
import house.Room;
import panel.Controller;

/**
 * Creates and manages Fridge.
 */
public class Fridge extends Device {

    private int foodAmount;
    private boolean isEmpty = false;
    private final int MAX_CAPACITY = 20;

    public Fridge(String name, String brand, Room location, ConsumptionType consumptionType, double[] consumption){
        super(name, brand, location, consumptionType, consumption);
        setState(new StateON(this));
        foodAmount = MAX_CAPACITY;
    }

    /**
     * Implements device using.
     *
     * @param person is a person that use device
     * @return using state
     *
     * @see Fridge#eat(int, Person)
     */
    @Override
    public Usable use(Person person) {
        switch (getDeviceState()) {
            case OFF:
            case IDLE:
                changeUsingOfDevice();
                turnON();
            case ON:
                eat(person.getFoodConsumption(), person);
                break;
        }
        return null;
    }

    /**
     * Controls device's condition.
     */
    @Override
    public void changeUsingOfDevice() {
        conditionOfDevice -= 4;
        if(conditionOfDevice < 0){
            conditionOfDevice = 0;
            broken();
        }
        announce();
    }

    /**
     * Controls internal contents of the fridge while using.
     *
     * @param quantity is quantity of products inside the fridge
     * @param person is person who is using fridge
     */
    private void eat(int quantity, Person person) {
        if (getFoodAmount() > quantity && quantity > 0 && quantity < 10) {
            foodAmount -= quantity;
            newNotification(new Notification(NotificationType.EATING_FOOD,getActualFloor(),getActualRoom(),person,this));

            if (foodAmount <= 0) {
                newAlert(new Alert(AlertType.NO_FOOD,getActualFloor(),getActualRoom(),this,null));
                foodAmount = 0;
                changeEmpty();
            }
        } else {
            newAlert(new Alert(AlertType.NO_FOOD,getActualFloor(),getActualRoom(),this,null));
        }
    }

    /**
     * Changes fridge's empty state.
     */
    public void changeEmpty() {
        isEmpty = !isEmpty;
    }

    /**
     * Notify housemates, if fridge needs to be filled and controls internal contents of the fridge while filling.
     *
     * @param quantity is quantity of products inside the fridge
     */
    public void fill(int quantity) {
        if (quantity > 0) {
            newNotification(new Notification(NotificationType.ADDING_FOOD,getActualFloor(),getActualRoom(),this,this));
            if (foodAmount + quantity < MAX_CAPACITY) {
                foodAmount += quantity;
            } else {
                foodAmount = MAX_CAPACITY;
            }
        }
    }

    /**
     * Creates and updates alert {@link Alert} from fridge.
     *
     * @param alert is new alert
     */
    public void newAlert(Alert alert) {
        if (foodAmount <= 0) {
            Controller.getInstance().updateFromAlertRoom(alert);
        }
    }

    @Override
    public String toString() {
        return deviceName;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public int getMAX_CAPACITY() {
        return MAX_CAPACITY;
    }
}
