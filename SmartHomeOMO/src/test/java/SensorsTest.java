import creature.Animal;
import creature.Person;
import creature.Role;
import house.*;
import meter.FireSensor;
import meter.TemperatureSensor;
import meter.WindSensor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import panel.Controller;
import report.HouseConfigurationReport;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SensorsTest {

    private House house;
    private Street street;
    private Floor floor;
    private Room room;
    private Person dad;
    private Animal dog;
    private HouseConfigurationReport houseConfigurationReport;
    private Louver louver;
    private WindSensor windSensor;
    private FireSensor fireSensor;
    private TemperatureSensor temperatureSensor;


    @BeforeEach
    void setUp() {
        house = new House("house1");

        floor = new Floor("1",house);
        room = new Room("pokoj",floor,1);
        house.addFloor(floor);
        floor.addRoom(room);
        dad = new Person("Tata", Role.DAD);
        dad.moveToHouse(house);
        dog = new Animal("Rex",Role.DOG);
        dog.moveToHouse(house);

        louver = room.getWindowsList().get(0).getLouver();
        street = house.getStreet();

        Controller.getInstance().addAlertHandler(louver);

        windSensor = new WindSensor(house);
        fireSensor = new FireSensor(house);


        Controller.getInstance().addAlertHandler(dad);
    }

    @Test
    public void fireSensorTest(){
        assertFalse(room.isOnFire());

        room.setOnFire();
        assertTrue(room.isOnFire());

        Controller.getInstance().newRound();

        assertFalse(room.isOnFire());
    }

    @Test
    public void windSensorTest(){
        assertFalse(louver.isPulled());
        while(!street.isWindy()) {
            street.newRound();
        }

        assertTrue(street.isWindy());
        Controller.getInstance().newRound();
        assertTrue(louver.isPulled());
    }

    @Test
    public void temperatureSensorTest(){
        assertTrue(house.getTemperatureSensor().getTemperature()<22 && house.getTemperatureSensor().getTemperature()>15);
        street.setTemperature(-20);
        while(house.getTemperatureSensor().getTemperature() < 15){
            house.getTemperatureSensor().update(street);
        }
        assertTrue(house.getTemperatureSensor().getTemperature()<22 && house.getTemperatureSensor().getTemperature()>15);
    }
}
