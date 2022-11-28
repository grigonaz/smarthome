package report;

import events.Event;
import devices.Consumption;
import devices.ConsumptionType;
import devices.Device;
import panel.Controller;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Consumption report.
 */
public class ConsumptionReport {

    private static final double ePrice = 5.5;
    private static final double wPrice = 90;

    public ConsumptionReport(){}

    private double countPrice(double num, ConsumptionType consumptionType){
        switch (consumptionType){
            case WATER:
                return num* wPrice;
            case ELECTRICITY:
                return num* ePrice;
        }
        return 0;
    }

    private String getUnitName(ConsumptionType consumptionType){
        switch (consumptionType){
            case WATER:
                return "m^3";
            case ELECTRICITY:
                return "kW";
        }
        return null;
    }


    private String getConsumptionString(double counter, ConsumptionType consumptionType){
        return ("--Used " + counter + " " + getUnitName(consumptionType)+" of " + consumptionType + ". Total price: " + countPrice(counter,consumptionType)+ " Kč");
    }

    /**
     * Generates report for consumption.
     * @param start - number of round for start point
     * @param end - number for round for end point
     * @param writer output print stream for command line
     */
    public void generateReport( int start, int end, PrintStream writer) {

        List<Event> allEvents = Controller.getInstance().getAllEvents();

        writer.println("------Consumption report from round "+start+" to " + end +"------");

        List<Consumption> consumptions = (ArrayList<Consumption>) allEvents
                .stream()
                .filter(Consumption.class::isInstance)
                .map(Consumption.class::cast)
                .filter(consumption -> consumption.getType() == ConsumptionType.ELECTRICITY || consumption.getType() == ConsumptionType.WATER)
                .filter(consumption -> consumption.getRound() >= start && consumption.getRound() < end)
                .sorted(Comparator.comparing((Consumption consumption) -> consumption.getSource().toString())
                        .thenComparing(Consumption::getType))
                .collect(Collectors.toList());

        if (consumptions.size() > 0) {
            Device source = (Device) consumptions.get(0).getSource();
            ConsumptionType consumptionType = consumptions.get(0).getType();
            double counter = 0;
            double totalElectricityConsumption = 0;
            double totalWaterConsumption = 0;

            writer.println("Device: " + source.toString());

            for (Consumption info : consumptions) {
                if (info.getSource() != source) {
                    writer.println(getConsumptionString(counter,consumptionType));
                    consumptionType = info.getType();
                    source = (Device) info.getSource();
                    writer.println("Device: " + source.toString());
                    switch (consumptionType){
                        case WATER:
                            totalWaterConsumption += counter;
                            break;
                        case ELECTRICITY:
                            totalElectricityConsumption += counter;
                            break;
                    }
                    counter = 0;
                }

                if (info.getType() != consumptionType) {
                    writer.println(getConsumptionString(counter,consumptionType));
                    consumptionType = info.getType();
                    counter = info.getConsumption();
                } else {
                    counter += info.getConsumption();
                }
            }
            writer.println(getConsumptionString(counter,consumptionType));
            writer.println();
            writer.println("Total water consumption:");
            writer.println(getConsumptionString(totalWaterConsumption,ConsumptionType.WATER));
            writer.println("Total electricity consumption:");
            writer.println(getConsumptionString(totalElectricityConsumption,ConsumptionType.ELECTRICITY));
        }
    }
}
