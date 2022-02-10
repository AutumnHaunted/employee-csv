package com.sparta.employeecsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static com.sparta.employeecsv.Main.logger;


public class DisplayHandler {

    private static final Properties properties = new Properties();

    private static void createProperties() {
        try {
            properties.load(new FileReader("src/main/resources/<insert.propertiesFileNameHere>"));
        } catch (IOException e) {
            printException(e);
        }



        System.out.println("Writing invalid records to InvalidEmployeeRecords.csv...");
        // employeeList.writeInvalidToFile("src/main/resources/InvalidEmployeeRecords.csv");


    }
    public static void printValidEmployee(EmployeeList employeeList, long startFilter) {
        System.out.println("\tNumber of valid employee records: " + employeeList.getEmployees().size());
        System.out.println("\tTime taken to filter out invalid data: " + (System.currentTimeMillis() - startFilter) + " ms");
    }
    public static void printDuplicateEmployee(EmployeeList employeeList) {
        //employeeList.removeAll(invalidList);
        System.out.println("\tAmount of duplicate records found: " + employeeList.getDuplicates().size());
        logger.info("Amount of duplicate records found: " + employeeList.getDuplicates().size());

    }


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
