package com.sparta.employeecsv;

public class Main {


    public static void main(String[] args) {
        EmployeeList el = CSVReadHandler.readCSV(DisplayHandler.getFilePath());
        DisplayHandler.printEmployeeLists(el);

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
    }
}
