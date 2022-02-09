package com.sparta.employeecsv;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DisplayHandler {
    static Logger logger = LogManager.getLogger(DisplayHandler.class);
    private static final Properties properties = new Properties();

    private static void createProperties(){
        try{
            properties.load(new FileReader("src/main/resources/<insert.propertiesFileNameHere>"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

        logger.info("Program started");
        long startTotal = System.currentTimeMillis();
        System.out.println("Reading in .csv file...");
        long startReadFile = System.currentTimeMillis();

        // EmployeeList employeeList = CSVReadHandler.readValues("src/main/resources/EmployeeRecordsLarge.csv");
        System.out.println("Time taken to read in data: " + (System.currentTimeMillis() - startReadFile) + " ms");

       logger.info("Time taken to read in data from csv: " + (System.currentTimeMillis() - startReadFile) + " ms");

       System.out.println("Filtering .csv file...");
        long startFilter = System.currentTimeMillis();
        //employeeList.filterEmployees();

        System.out.println("\tNumber of valid employee records: " + employeeList.getEmployeeList().size());
        System.out.println("\tTime taken to filter out invalid data: " + (System.currentTimeMillis() - startFilter) + " ms");

        //employeeList.removeAll(invalidList);
        System.out.println("\tAmount of duplicate records found: " + invalidList.size());
        logger.info("Amount of duplicate records found: " + invalidList.size());

        System.out.println("Writing invalid records to InvalidEmployeeRecords.csv...");
       // employeeList.writeInvalidToFile("src/main/resources/InvalidEmployeeRecords.csv");

    public static void printInvalidEmployee(String[] entries) {
        System.out.println("The following record is not a valid employee: ");
        for(String s : entries) {
            System.out.print(s);
            System.out.print(", ");
        }
        System.out.println();
    }
    public static void printException(Exception e) {
        System.out.print("An exception occurred: ");
        System.out.println(e.getClass().getName());
    }

    public static String getFilePath(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a file path: ");
        return scanner.nextLine();
    }
}
