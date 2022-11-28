package report;

import events.Event;
import events.Notification;
import events.NotificationType;
import creature.Animal;
import creature.Creature;
import creature.Person;
import devices.Device;
import panel.Controller;
import sport.SportEquipment;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Activity and usage report.
 */
public class ActivityAndUsageReport {

    public ActivityAndUsageReport(){}

    /**
     * Generates report for activity and usages.
     * @param start - number of round for start point
     * @param end - number for round for end point
     * @param writer output print stream for command line
     */
    public void generateReport(int start, int end, PrintStream writer) {

        List<Event> allEvents = Controller.getInstance()
                .getAllEvents()
                .stream()
                .filter(event -> event.getRound() >= start && event.getRound() < end)
                .collect(Collectors.toList());

        writer.println("------Activity and usage report from round " + start + " to " + end + "------");

        List<Notification> usages = (ArrayList<Notification>) allEvents
                .stream()
                .filter(Notification.class::isInstance)
                .map(Notification.class::cast)
                .filter(i -> i.getSource() instanceof Creature)
                .filter(i -> i.getType().equals(NotificationType.USING_DEVICE))
                .sorted(Comparator.comparing((Notification i) -> i.getSource().toString())
                        .thenComparing(info -> info.getTarget().toString()))
                .collect(Collectors.toList());

        if(usages.size() > 0){
            writer.println();
            writer.println("...........Usages...........");

            Creature source = (Creature) usages.get(0).getSource();
            Device targetDevice = (Device) usages.get(0).getTarget();
            int counter = 0;

            writer.println("Person: " + source.toString());
            for (Notification i:usages) {
                if(i.getSource() != source) {
                    writer.println("---Used device: " + targetDevice.toString() + " " + counter + " times");
                    targetDevice = (Device) i.getTarget();
                    source = (Creature) i.getSource();
                    writer.println("Person: " + source.toString());
                    counter = 0;
                }

                if(i.getTarget() != targetDevice){
                    writer.println("---Used device: " + targetDevice.toString() + " " + counter + " times");
                    targetDevice = (Device) i.getTarget();
                    counter = 1;
                }
                else counter++;
            }
            writer.println("---Used device: " + targetDevice.toString() + " " + counter + " times");

            writer.println();
        }

        List<Notification> sportActivities = (ArrayList<Notification>) allEvents
                .stream()
                .filter(Notification.class::isInstance)
                .map(Notification.class::cast)
                .filter(i -> i.getSource() instanceof Person)
                .filter(i -> i.getType().equals(NotificationType.SPORT_EQUIPMENT_USING))
                .sorted(Comparator.comparing((Notification i) -> i.getSource().toString())
                        .thenComparing(info -> info.getTarget().toString()))
                .collect(Collectors.toList());

        if(sportActivities.size() > 0){
            writer.println("...........Sport activities...........");
            Creature source = (Creature) sportActivities.get(0).getSource();
            SportEquipment targetSport = (SportEquipment) sportActivities.get(0).getTarget();
            int counter = 0;

            writer.println("Person: " + source.toString());
            for (Notification info:sportActivities) {
                if(info.getSource() != source) {
                    writer.println("---Used sport equipment: " + targetSport.toString() + " " + counter + " times");
                    targetSport = (SportEquipment) info.getTarget();
                    source = (Creature) info.getSource();
                    writer.println("Person: " + source.toString());
                    counter = 0;
                }

                if(info.getTarget() != targetSport){
                    writer.println("---Used sport equipment: " + targetSport.toString() + " " + counter + " times");
                    targetSport = (SportEquipment) info.getTarget();
                    counter = 1;
                }
                else counter++;
            }
            writer.println();

        }

        List<Notification> animalActivities = (ArrayList<Notification>) allEvents
                .stream()
                .filter(Notification.class::isInstance)
                .map(Notification.class::cast)
                .filter(info -> info.getSource() instanceof Animal)
                .sorted(Comparator.comparing((Notification i) -> i.getSource().toString())
                        .thenComparing(info -> info.getType().toString()))
                .collect(Collectors.toList());

        if(animalActivities.size() > 0){
            writer.println("...........Animal activities...........");
            Creature source = (Creature) animalActivities.get(0).getSource();
            NotificationType notificationType = animalActivities.get(0).getType();
            int counter = 0;


            writer.println("Animal: " + source.toString());
            for (Notification notification:animalActivities) {
                if(notification.getSource() != source) {
                    writer.println("---Did: " + notificationType.toString() + " " + counter + " times");
                    notificationType = notification.getType();
                    source = (Creature) notification.getSource();
                    writer.println("Animal: " + source.toString());
                    counter = 0;
                }

                if(notification.getType() != notificationType){
                    writer.println("---Did: " + notificationType.toString() + " " + counter + " times");
                    notificationType = notification.getType();
                    counter = 1;
                }
                else counter++;
            }
            writer.println("---Did: " + notificationType.toString() + " " + counter + " times");

            writer.println();
            writer.println();
            writer.println();
        }
    }



}
