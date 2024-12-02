package com.coderscampus;

import java.io.IOException;
import java.util.List;

public class SalesParsingApplication {
        public static void Main(String[] args) {
            try {
                List<SalesTotal> model3Sales = TeslaSalesAnalysis.readCSV("model3.csv");
                List<SalesTotal> modelSSales = TeslaSalesAnalysis.readCSV("modelS.csv");
                List<SalesTotal> modelXSales = TeslaSalesAnalysis.readCSV("modelX.csv");

                System.out.println("~~~~~~~~~~~~~~~~~~~~");
                System.out.println();

                TeslaSalesAnalysis.printYearlySalesReport("Model 3", model3Sales);
                TeslaSalesAnalysis.printYearlySalesReport("Model S", modelSSales);
                TeslaSalesAnalysis.printYearlySalesReport("Model X", modelXSales);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }