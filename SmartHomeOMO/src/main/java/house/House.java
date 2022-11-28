package house;

import events.EventObject;
import creature.Animal;
import creature.Creature;
import creature.Person;
import devices.Device;
import meter.*;
import panel.Controller;
import panel.Round;
import sport.SportEquipment;

import java.util.ArrayList;
import java.util.List;

public class House implements Round, EventObject {

    private Street street;
    private List<Floor> floors;
    private CircuitBrake circuitBrake;
    private String name;
    private ElectricityMeter electricityMeter;
    private Ventilation ventilation;
    private TemperatureSensor temperatureSensor;
    private WaterMeter waterMeter;
    private FireSensor fireSensor;
    private WindSensor windSensor;

    public House(String name) {
        this.name = name;
        floors = new ArrayList<>();
        this.street = new Street();
        Controller.getInstance().setHouse(this);
    }

    /**
     * Starts new round for house.
     */
    @Override
    public void newRound() {
        Controller.getInstance().newRound();
        street.newRound();
        for (Device device:getDeviceList()) {
            device.newRound();
        }

        for (Person person:getPersonList()) {
            person.newRound();
        }

        for (Animal animal:getAnimalList()) {
            animal.newRound();
        }

        electricityMeter.newRound();

        Controller.getInstance().roundNumber++;
    }

    /**
     * Adds floor to the house.
     *
     * @param floor is the floor that needs to be added
     */
    public void addFloor(Floor floor) {
        if (!floors.contains(floor)) {
            floors.add(floor);
        }
    }

    public List<Person> getPersonList() {
        List<Person> list = new ArrayList<>();
        for (Room r : getRoomList()) {
            for (Creature o: r.getCreatureList()) {
                if (o instanceof Person)
                    list.add((Person) o);
            }
        }
        return list;
    }

    public List<Room> getRoomList() {
        List<Room> list = new ArrayList<>();
        for (Floor f : floors) {
            list.addAll(f.getRoomList());
        }
        return list;
    }

    public List<Animal> getAnimalList() {
        List<Animal> list = new ArrayList<>();
        for (Room room:getRoomList()) {
            for (Creature o: room.getCreatureList()) {
                if (o instanceof Animal)
                    list.add((Animal) o);
            }
        }
        return list;
    }

    public List<Device> getDeviceList() {
        List<Device> list = new ArrayList<>();
        for (Floor floor: floors) {
            List<Room> rooms = floor.getRoomList();
            for (Room room:rooms) {
                list.addAll(room.getDeviceList());
            }
        }
        return list;
    }

    public List<SportEquipment> getSportEquipment() {
        List<SportEquipment> sportEquipment = new ArrayList<>();
        for (Floor floor: floors) {
            List<Room> rooms = floor.getRoomList();
            for (Room room:rooms) {
                sportEquipment.addAll(room.getSportEquipmentList());

            }
        }
        return sportEquipment;
    }

    public List<Auto> getCars() {
        List<Auto> cars = new ArrayList<>();
        for(Room room:getRoomList()){
            if(room instanceof Garage) {
                cars.add(((Garage) room).getAuto());
            }
        }
        return cars;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setCircuitBreakes(CircuitBrake circuitBrake) {
        this.circuitBrake = circuitBrake;
    }

    public CircuitBrake getCircuitBreakes() {
        return circuitBrake;
    }

    public void setElectricityMeter(ElectricityMeter electricityMeter) {
        this.electricityMeter = electricityMeter;
    }

    public Street getStreet() {
        return street;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ventilation getVentilation() {
        return ventilation;
    }

    public void setVentilation(Ventilation ventilation) {
        this.ventilation = ventilation;
    }

    public TemperatureSensor getTemperatureSensor() {
        return temperatureSensor;
    }

    public void setTemperatureSensor(TemperatureSensor temperatureSensor) {
        this.temperatureSensor = temperatureSensor;
    }


    public void setWaterMeter(WaterMeter waterMeter) {
        this.waterMeter = waterMeter;
    }

    public void setFireSensor(FireSensor fireSensor) {
        this.fireSensor = fireSensor;
    }

    public void setWindSensor(WindSensor windSensor) {
        this.windSensor = windSensor;
    }

    @Override
    public String toString() {
        return name;
    }
}
