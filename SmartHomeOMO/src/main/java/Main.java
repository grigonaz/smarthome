import config.Configuration;
import house.House;
import report.ActivityAndUsageReport;
import report.ConsumptionReport;
import report.EventReport;
import report.HouseConfigurationReport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int roundNum = 50;
        int from = 0;
        int to = 50;
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm");
        PrintStream printStream = new PrintStream(new File("../SmartHomeOMO/genereatedReports/reportsConfig2_"+time.format(dtf)+".txt"));
        System.setOut(printStream);

        Configuration conf = new Configuration();
        House house = conf.configurationTwo();

        HouseConfigurationReport houseConfigurationReport = new HouseConfigurationReport();
        ActivityAndUsageReport activityAndUsageReport = new ActivityAndUsageReport();
        EventReport eventReport = new EventReport();
        ConsumptionReport consumptionReport = new ConsumptionReport();

        houseConfigurationReport.generateReport(house, printStream);
        for (int i = 0; i < roundNum; i++){
            house.newRound();
        }

        activityAndUsageReport.generateReport(from,to,printStream);
        eventReport.generateReport(from,to,printStream);
        consumptionReport.generateReport(from,to,printStream);
    }
}
