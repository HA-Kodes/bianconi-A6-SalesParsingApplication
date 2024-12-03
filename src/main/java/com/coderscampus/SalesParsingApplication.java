package com.coderscampus;

import java.io.IOException;
import java.util.List;

public class SalesParsingApplication {
    public static void main(String[] args) {
        try {
            System.out.println("Starting to read sales data...");

            List<SalesTotal> model3Sales = TeslaSalesAnalysis.readCSV("src/main/resources/model3.csv");
            List<SalesTotal> modelSSales = TeslaSalesAnalysis.readCSV("src/main/resources/modelS.csv");
            List<SalesTotal> modelXSales = TeslaSalesAnalysis.readCSV("src/main/resources/modelX.csv");

            System.out.println("~~~~~~~~~~~~~~~~~~~~");
            System.out.println();

            TeslaSalesAnalysis.printYearlySalesReport("Model 3", model3Sales);
            TeslaSalesAnalysis.printYearlySalesReport("Model S", modelSSales);
            TeslaSalesAnalysis.printYearlySalesReport("Model X", modelXSales);

            System.out.println("Finished processing sales data.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
