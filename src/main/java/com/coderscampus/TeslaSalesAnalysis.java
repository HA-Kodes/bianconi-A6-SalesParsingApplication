package com.coderscampus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.Comparator;

public class TeslaSalesAnalysis {

    private static final Logger LOGGER = Logger.getLogger(TeslaSalesAnalysis.class.getName());

    // Method to read CSV files and convert to list of SalesTotal objects
    public static List<SalesTotal> readCSV(String filePath) throws IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM-yy");
        List<SalesTotal> salesData = new ArrayList<>();

        try (BufferedReader reportReader = new BufferedReader(new FileReader(filePath))) {
            reportReader.readLine();  // Skip header
            String line;
            while ((line = reportReader.readLine()) != null) {
                String[] salesInfo = line.split(",");
                YearMonth date = YearMonth.parse(salesInfo[0], formatter);
                Integer sales = Integer.valueOf(salesInfo[1]);
                SalesTotal salesTotal = new SalesTotal(date, sales);
                salesData.add(salesTotal);
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error reading file: " + filePath, e);
            throw e;
        }

        return salesData;
    }

    // Method to collect yearly sales
    public static Map<Integer, Integer> getYearlySales(List<SalesTotal> salesRecords) {
        return salesRecords.stream()
                .collect(Collectors.groupingBy(record -> record.getYearMonth().getYear(), Collectors.summingInt(SalesTotal::getSales)));
    }

    // Method to extract the best month
    public static Optional<YearMonth> getBestMonth(List<SalesTotal> salesRecords) {
        return salesRecords.stream()
                .max(Comparator.comparingInt(SalesTotal::getSales))
                .map(SalesTotal::getYearMonth);
    }

    // Method to extract the worst month
    public static Optional<YearMonth> getWorstMonth(List<SalesTotal> salesRecords) {
        return salesRecords.stream()
                .min(Comparator.comparingInt(SalesTotal::getSales))
                .map(SalesTotal::getYearMonth);
    }

    // Method to print the yearly sales report
    public static void printYearlySalesReport(String model, List<SalesTotal> salesRecords) {
        if (salesRecords == null || salesRecords.isEmpty()) {
            System.out.println(model + " has no sales data.");
            return;
        }

        System.out.println(model + " Yearly Sales Report");
        System.out.println("---------------------------");

        Map<Integer, Integer> yearlySales = getYearlySales(salesRecords);
        yearlySales.forEach((year, sales) -> System.out.println(year + " -> " + sales));

        String bestMonth = getBestMonth(salesRecords).map(YearMonth::toString).orElse("No data available");
        String worstMonth = getWorstMonth(salesRecords).map(YearMonth::toString).orElse("No data available");

        System.out.println("\nThe best month for " + model + " was: " + bestMonth);
        System.out.println("The worst month for " + model + " was: " + worstMonth);
        System.out.println();
    }
}
