package com.sparta.employeecsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    static Logger logger = LogManager.getLogger(DisplayHandler.class);
    public static void main(String[] args) throws SQLException, IOException {

        Main.logger.info("Program started");
        long startTotal = System.currentTimeMillis();
        System.out.println("Reading in .csv file...");
        long startReadFile = System.currentTimeMillis();

        EmployeeList el = CSVReadHandler.readCSV(DisplayHandler.getFilePath());

        // EmployeeList employeeList = CSVReadHandler.readValues("src/main/resources/EmployeeRecordsLarge.csv");
        System.out.println("Time taken to read in data: " + (System.currentTimeMillis() - startReadFile) + " ms");

        logger.info("Time taken to read in data from csv: " + (System.currentTimeMillis() - startReadFile) + " ms");

        System.out.println("Filtering .csv file...");
        long startFilter = System.currentTimeMillis();
        //employeeList.filterEmployees();

        long timeTaken = startFilter - startTotal;
        DisplayHandler.printValidEmployee(el, timeTaken);
        System.out.println("------------------------");
        EmployeeDataAccessObject.getConnection();
        System.out.println("------------------------");
    }
}
