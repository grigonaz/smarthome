package report;

import creature.Animal;
import creature.Person;
import devices.Device;
import house.*;
import sport.SportEquipment;

import java.io.PrintStream;

/**
 * House configuration report.
 */
public class HouseConfigurationReport {

    public HouseConfigurationReport(){}

    /**
     * Generates report for house configuration.
     * @param house  house to make a config report for
     * @param writer output print stream for command line
     */
    public void generateReport(House house, PrintStream writer) {
        writer.println("------House configuration report------");
        writer.println("House: " + house.toString());
        for (Floor floor : house.getFloors()) {
            writer.println("-Floor: " + floor.toString());
            for (Room room : floor.getRoomList()) {
                writer.println("--Room: " + room.toString());
                int i = 1;
                for (Window window : room.getWindowsList()) {
                    writer.print("---" + window.toString());
                    if (window.getLouver() != null) {
                        writer.println(": " + window.getLouver().toString());
                    }
                    i++;
                }
                if (room instanceof Garage) {
                    for (SportEquipment sportEquipment : ((Garage) room).getSportEquipmentList()) {
                        writer.println("---Sport Equipment: " + sportEquipment.toString());
                    }
                }else {
                    for (Device appliance : (room).getDeviceList()) {
                        writer.println("---Device: " + appliance.toString());
                    }
                }
            }
        }
        writer.println(".....................................");
        writer.println("People: ");
        for (Person person : house.getPersonList()) {
            writer.println("-" + person.toString());
        }
        writer.println("Animals: ");
        for (Animal animal : house.getAnimalList()) {
            writer.println("-" + animal.toString());
        }
        writer.println();
        writer.println();
    }
}
