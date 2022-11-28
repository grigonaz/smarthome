package creature;

import alerts.Alert;
import alerts.AlertHandler;
import alerts.AlertType;
import events.Notification;
import events.NotificationType;
import devices.Device;
import devices.Fridge;
import house.Auto;
import house.Room;
import panel.Controller;
import sport.SportEquipment;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Creates and manages persons.
 */
public class Person extends Creature implements AlertHandler {

    private final int foodConsumption = 10;

    protected int deviceUsageNumber = 0;
    protected int sportEquipmentUsageNumber = 5;

    public Person(String name, Role role) {
        super(name,role);
    }

    /**
     * Let the person use device {@link Device}.
     *
     * @param device is device that person wants to use
     */
    public void useDevice(Device device) {
        if (device != null && !busy) {
            newNotification(new Notification(NotificationType.USING_DEVICE,getFloor(),actualRoom,this,device));
            deviceUsageNumber++;
            usingTarget = device.use(this);
        }
    }

    /**
     * Let the person use sport equipment {@link SportEquipment}.
     *
     * @param equipment is sport equipment that person wants to use
     */
    public void useSportEquipment(SportEquipment equipment) {
        if (equipment != null && !busy) {
            newNotification(new Notification(NotificationType.SPORT_EQUIPMENT_USING,  getFloor(), actualRoom, this,equipment));
            sportEquipmentUsageNumber++;
            usingTarget = equipment.use(this);
        }
    }

    /**
     * Controls person's occupation. Starts new round for the person.
     */
    @Override
    public void newRound() {
        if (usingTarget == null) {
            nextAction();
        } else {
            usingTarget = usingTarget.use(this);
        }
        busy = false;
    }

    /**
     * Finds out what is user exactly doing.
     */
    public void nextAction() {
        if (!busy) {
            if (sportEquipmentUsageNumber >= deviceUsageNumber) {
                List<Device> appliances = house
                        .getDeviceList()
                        .stream()
                        .filter(appliance-> !appliance.isBroken() && !appliance.isBusy())
                        .collect(Collectors.toList());

                useDevice(appliances.get(new Random().nextInt(appliances.size())));
            } else {
                List<SportEquipment> sportEquipments = house
                        .getSportEquipment()
                        .stream()
                        .filter(sportEquipment -> !sportEquipment.isBusy())
                        .collect(Collectors.toList());
                try {
                    useSportEquipment(sportEquipments.get(new Random().nextInt(sportEquipments.size())));
                } catch (Exception e) {
                    List<Device> appliances = house
                            .getDeviceList()
                            .stream()
                            .filter(appliance-> !appliance.isBroken() && !appliance.isBusy())
                            .collect(Collectors.toList());

                    useDevice(appliances.get(new Random().nextInt(appliances.size())));
                }
            }
            busy = true;
        }
    }

    /**
     * Function let manage alert {@link Alert} and close the issue, if person has role DAD or MOM {@link Role}.
     *
     * @param alert is an alert that involve following actions
     * @return alert's state (closed or not)
     */
    public boolean handleAlert(Alert alert) {
        if (!busy && (this.role.equals(Role.DAD) || this.role.equals(Role.MOM))) {
            busy = true;
            AlertType t = alert.getAlertType();

            if (t == AlertType.FIRE) {
                List<Room> rooms = house.getRoomList()
                        .stream()
                        .filter(Room::isOnFire)
                        .collect(Collectors.toList());

                Room room = null;

                if (rooms.size() > 0)
                    room = rooms.get(new Random().nextInt(rooms.size()));

                    if (room != null) {
                        changeRoom(room);
                        newNotification(new Notification(NotificationType.PUT_OUT_THE_FIRE, getFloor(), actualRoom, this,room));
                        room.putOutFire();
                    }
                    return true;
            } else if (t == AlertType.BROKEN) {
                List<Device> devices = house
                        .getDeviceList()
                        .stream()
                        .filter(Device::isBroken)
                        .collect(Collectors.toList());

                Device device = devices.get(new Random().nextInt(devices.size()));

                if (devices.size() > 0) {
                    newNotification(new Notification(NotificationType.REPAIRING, getFloor(), actualRoom, this,device));
                    device.getDeviceManual();
                    device.repairDevice();
                }
                return true;
            } else if (t == AlertType.NO_FOOD) {
                List<Auto> cars = house
                        .getCars()
                        .stream()
                        .filter(Auto::isPresent)
                        .collect(Collectors.toList());

                for (Auto car: cars) {
                    if (car.getActualCarStorage() > 0)
                        if (alert.getSource() instanceof Fridge) {
                            ((Fridge) alert.getSource()).fill(car.getActualCarStorage());
                            car.emptyCarStorage();
                        }
                }

                Auto auto = cars.get(new Random().nextInt(cars.size()));

                if (cars.size() > 0) {
                    if (role == Role.DAD ||role == Role.MOM) {
                        newNotification(new Notification(NotificationType.DRIVING_AUTO, getFloor(), actualRoom,this, auto));
                        auto.use(this);
                    }
                }
                return true;
            } else if (t == AlertType.CIRCUIT_BRAKE) {
                if (house.getCircuitBreakes().isAction()) {
                    house.getCircuitBreakes().replace();
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
         return "Person " +role +" " + name;
    }

    /**
     * Adds alert handler to controller
     */
    public void addHandlerToController(){
        Controller.getInstance().addAlertHandler(this);
    }

    public void openDoor(){
        Controller.getInstance().openDoor();
    }

    public void closeDoor(){
        Controller.getInstance().closeDoor();
    }

    public void doNothing() {
        newNotification(new Notification(NotificationType.DO_NOTHING,getFloor(),actualRoom,this,this));
    }

    public int getFoodConsumption() {
        return foodConsumption;
    }
}
