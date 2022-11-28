package config;

import creature.Animal;
import creature.Creature;
import creature.Person;
import creature.Role;
import devices.*;
import house.*;
import meter.*;
import sport.Bike;
import sport.Ski;
import sport.SportEquipment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Creates different house configurations.
 */
public class Configuration {

    public House configurationOne() {

        //House
        House house = new House("Chata");

        //1.floor
        Floor firstFloor = new Floor("firstFloor", house);
        Room hallway = new Room("hallway", firstFloor, 1);
        Door door = new Door(hallway);
        Room kitchen = new Room("kitchen", firstFloor, 2);
        Room livingRoom = new Room("livingRoom", firstFloor, 1);

        //2.floor
        Floor secondFloor = new Floor("secondFloor", house);
        Room bathroom = new Room("bathroom", secondFloor, 1);
        Room firstBedroom = new Room("firstBedroom", secondFloor, 1);
        Room secondBedroom = new Room("secondBedroom", secondFloor, 1);

        //3.floor
        Floor thirdFloor = new Floor("thirdFloor", house);
        Room thirdBedroom = new Room("thirdBedroom", thirdFloor, 1);
        Room forthBedroom = new Room("forthBedroom", thirdFloor, 1);

        //Devices
        TV tv1 = new TV("livingroomtv", "LG", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.3, 0.05});
        TV tv2 = new TV("firstbedroomtv", "Samsung", firstBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.03});
        TV tv3 = new TV("secondbedroomtv", "Samsung", secondBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.03});
        TV tv4 = new TV("thirdbedroomtv", "Samsung", thirdBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.03});
        Lamp lamp1 = new Lamp("biglamp1", "Lindby", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.05, 0.01});
        Lamp lamp2 = new Lamp("biglamp2", "Lindby", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.05, 0.01});
        Lamp lamp3 = new Lamp("parentslamp", "Xiaomi", firstBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.2, 0.01});
        Lamp lamp4 = new Lamp("nazarslamp", "Xiaomi", secondBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.2, 0.01});
        Lamp lamp5 = new Lamp("ivanslamp", "Xiaomi", thirdBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.2, 0.01});
        Lamp lamp6 = new Lamp("mariaslamp", "Xiaomi", forthBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.2, 0.01});
        Fridge fridge = new Fridge("fridge", "Zanussi", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.35, 0.05});
        Turntable turntable = new Turntable("vinyl", "Pro-Ject", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.07, 0.01});
        Loudspeaker ls1 = new Loudspeaker("ls1", "Pioneer DJ", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.01});
        Loudspeaker ls2 = new Loudspeaker("ls2", "Pioneer DJ", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.01});

        Oven oven = new Oven("oven", "BOSCH", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.05});
        Stove stove = new Stove("stove", "AMICA", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.05});
        Microwave microwave = new Microwave("microwave", "Electrolux", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.05, 0.01});
        CoffeeMaker coffeeMaker = new CoffeeMaker("coffee", "Nespresso", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.05, 0.01});

        Shower shower = new Shower("shower", "Zanussi", bathroom, ConsumptionType.WATER,new double[]{8, 0.2, 0.04});
        Toilet toilet = new Toilet("toilet", "Bauhaus", bathroom, ConsumptionType.WATER, new double[]{2, 0.3, 0.0});


        //People and animals
        createPeopleAndAnimalsConf1(house);

        //Garage and sport equipment
        setGarage(firstFloor);

        //Meters
        setMeters(house);

        return house;
    }

    public House configurationTwo() {

        //House
        House house = new House("Chata2");

        //1.floor
        Floor firstFloor = new Floor("firstFloor", house);
        Room hallway = new Room("hallway", firstFloor, 1);
        Door door = new Door(hallway);
        Room kitchen = new Room("kitchen", firstFloor, 2);
        Room livingRoom = new Room("livingRoom", firstFloor, 1);

        //2.floor
        Floor secondFloor = new Floor("secondFloor", house);
        Room bathroom = new Room("bathroom", secondFloor, 1);
        Room firstBedroom = new Room("firstBedroom", secondFloor, 1);
        Room secondBedroom = new Room("secondBedroom", secondFloor, 1);

        //3.floor
        Floor thirdFloor = new Floor("thirdFloor", house);
        Room thirdBedroom = new Room("thirdBedroom", thirdFloor, 1);
        Room forthBedroom = new Room("forthBedroom", thirdFloor, 1);

        //4.floor
        Floor forthFloor = new Floor("forthFloor", house);
        Room fifthBedroom = new Room("thirdBedroom", forthFloor, 1);

        //Devices
        TV tv1 = new TV("livingroomtv", "LG", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.3, 0.05});
        TV tv2 = new TV("firstbedroomtv", "Samsung", firstBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.03});
        TV tv3 = new TV("secondbedroomtv", "Samsung", secondBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.03});
        TV tv4 = new TV("thirdbedroomtv", "Samsung", thirdBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.03});
        TV tv5 = new TV("fifthbedroomtv", "Samsung", forthBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.03});
        Lamp lamp1 = new Lamp("biglamp1", "Lindby", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.05, 0.01});
        Lamp lamp2 = new Lamp("biglamp2", "Lindby", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.05, 0.01});
        Lamp lamp3 = new Lamp("parentslamp", "Xiaomi", firstBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.2, 0.01});
        Lamp lamp4 = new Lamp("nazarslamp", "Xiaomi", secondBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.2, 0.01});
        Lamp lamp5 = new Lamp("ivanslamp", "Xiaomi", thirdBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.2, 0.01});
        Lamp lamp6 = new Lamp("mariaslamp", "Xiaomi", fifthBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.2, 0.01});
        Lamp lamp7 = new Lamp("grandparentslamp", "Xiaomi", forthBedroom, ConsumptionType.ELECTRICITY, new double[]{1, 0.2, 0.01});
        Fridge fridge = new Fridge("fridge", "Zanussi", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.35, 0.05});
        Turntable turntable = new Turntable("vinyl", "Pro-Ject", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.07, 0.01});
        Loudspeaker ls1 = new Loudspeaker("ls1", "Pioneer DJ", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.01});
        Loudspeaker ls2 = new Loudspeaker("ls2", "Pioneer DJ", livingRoom, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.01});

        Oven oven = new Oven("oven", "BOSCH", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.05});
        Stove stove = new Stove("stove", "AMICA", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.1, 0.05});
        Microwave microwave = new Microwave("microwave", "Electrolux", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.05, 0.01});
        CoffeeMaker coffeeMaker = new CoffeeMaker("coffee", "Nespresso", kitchen, ConsumptionType.ELECTRICITY, new double[]{1, 0.05, 0.01});

        Shower shower = new Shower("shower", "Zanussi", bathroom, ConsumptionType.WATER, new double[]{8, 0.2, 0.05});
        Toilet toilet = new Toilet("toilet", "Bauhaus", bathroom, ConsumptionType.WATER, new double[]{2, 0.3, 0.0});


        //People and animals
        createPeopleAndAnimalsConf2(house);

        //Garage and sport equipment
        setGarage(firstFloor);

        //Meters
        setMeters(house);

        return house;
    }


    private void createPeopleAndAnimalsConf1(House house){
        Person father = new Person("Max", Role.DAD);
        Person mother = new Person("Helen", Role.MOM);
        Person firstSon = new Person("Nazar", Role.CHILD);
        Person secondSon = new Person("Ivan", Role.CHILD);
        Person daughter = new Person("Maria", Role.CHILD);
        Animal dog = new Animal("George", Role.DOG);
        Animal cat1 = new Animal("Daria", Role.CAT);
        Animal cat2 = new Animal("Polly", Role.CAT);

        List<Creature> creatureList = new ArrayList<>(Arrays.asList(cat1,cat2,dog,daughter,secondSon,firstSon,mother,father));

        for (Creature c : creatureList) {
            c.moveToHouse(house);
        }
    }
    private void createPeopleAndAnimalsConf2(House house){
        Person grandpa = new Person("Max", Role.GRANDPA);
        Person granny = new Person("Max", Role.GRANDMA);
        Person father = new Person("Max", Role.DAD);
        Person mother = new Person("Helen", Role.MOM);
        Person firstSon = new Person("Nazar", Role.CHILD);
        Person secondSon = new Person("Ivan", Role.CHILD);
        Person daughter = new Person("Maria", Role.CHILD);
        Animal dog = new Animal("George", Role.DOG);
        Animal cat1 = new Animal("Daria", Role.CAT);
        Animal cat2 = new Animal("Polly", Role.CAT);

        List<Creature> creatureList = new ArrayList<>(Arrays.asList(cat1,cat2,dog,daughter,secondSon,firstSon,mother,father,grandpa,granny));

        for (Creature c : creatureList) {
            c.moveToHouse(house);
        }
    }

    private void setGarage(Floor floor){
        Garage garage = new Garage("garage", floor, 1);
        Auto auto = new Auto("Lamborgini", garage);

        Ski fathersSki = new Ski("Salomon", garage);
        Ski mothersSki = new Ski("Salomon", garage);
        Ski nazarsSki = new Ski("Salomon", garage);
        Ski ivansSki = new Ski("Salomon", garage);
        Ski mariasSki = new Ski("Salomon", garage);
        Bike fathersBike = new Bike("ROCKRIDER", garage);
        Bike mothersBike = new Bike("ROCKRIDER", garage);
        Bike nazarsBike = new Bike("ROCKRIDER", garage);
        Bike ivansBike = new Bike("ROCKRIDER", garage);

        List<SportEquipment> sportEquipments = new ArrayList<>(Arrays.asList(fathersBike,mothersBike,nazarsBike,ivansBike,fathersSki,mothersSki,nazarsSki,ivansSki,mariasSki));

        for (SportEquipment s : sportEquipments) {
            garage.addSportEquipment(s);
        }
    }

    private void setMeters(House house){
        FireSensor fireSensor = new FireSensor(house);
        WindSensor windSensor = new WindSensor(house);
        TemperatureSensor temperatureSensor = new TemperatureSensor(house);
        DeviceCondition deviceCondition = new DeviceCondition(house);
        WaterMeter waterMeter = new WaterMeter(house);
        ElectricityMeter electricityMeter = new ElectricityMeter(house);
        Ventilation ventilation = new Ventilation(house);
        CircuitBrake circuitBrake = new CircuitBrake(house);
        house.setVentilation(ventilation);
        house.setTemperatureSensor(temperatureSensor);
        house.setFireSensor(fireSensor);
        house.setElectricityMeter(electricityMeter);
        house.setCircuitBreakes(circuitBrake);
        house.setWaterMeter(waterMeter);
        house.setWindSensor(windSensor);
    }
}
