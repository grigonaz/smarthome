package report;

import alerts.Alert;
import events.Event;
import events.Notification;
import panel.Controller;

import java.io.PrintStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Event report.
 */
public class EventReport {

    public EventReport(){}

    /**
     * Generates report for events.
     * @param start - number of round for start point
     * @param end - number for round for end point
     * @param writer output print stream for command line
     */
    public void generateReport(int start, int end, PrintStream writer){
        List<Event> allEvents = Controller.getInstance()
                .getAllEvents()
                .stream()
                .filter(event -> event.getRound() >= start && event.getRound() < end)
                .collect(Collectors.toList());

        writer.println("------Event report from round "+start+" to " + end + "------");
        writer.println();
        writer.println("...........Notifications...........");

        List<Notification> notification = allEvents
                .stream()
                .filter(Notification.class::isInstance)
                .map(Notification.class::cast)
                .collect(Collectors.toList());

        for (Notification n : notification){
            writer.println("-" + n.toString());
        }

        writer.println();
        writer.println("...........Alerts...........");

        List<Alert> alerts =  allEvents
                .stream()
                .filter(Alert.class::isInstance)
                .map(Alert.class::cast)
                .collect(Collectors.toList());

        for (Alert alert : alerts){
            writer.println("-" + alert.toString());
        }

        writer.println();
        writer.println();
        writer.println();
    }
}
