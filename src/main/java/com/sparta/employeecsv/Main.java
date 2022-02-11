package com.sparta.employeecsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    static Logger logger = LogManager.getLogger(DisplayHandler.class);
    public static void main(String[] args) throws SQLException, IOException {

        Main.logger.info("Program started");
        String filePath = DisplayHandler.getFilePath();
        System.out.println("Reading in .csv file...");
        EmployeeList el = CSVReadHandler.readCSV(filePath);

        logger.info("Writing to Employee");
        int numberOfThreads = DisplayHandler.getThreads();
        boolean choice = false; // for questionable

        long startTime = System.currentTimeMillis();
        /*
        Calls to create threads and start connecting to the database
        and inputting data for ****VALID EMPLOYEES****
         */
        long endTime = System.currentTimeMillis();
        DisplayHandler.printTimeTaken(startTime, endTime);
        DisplayHandler.printEmployeeList(el, choice);

        logger.info("Writing to duplicates");
        startTime = System.currentTimeMillis();
        /*
        Calls to create threads and start connecting to the database
        and inputting data for ****DUPLICATES****
         */
        endTime = System.currentTimeMillis();
        DisplayHandler.printTimeTaken(startTime, endTime);
        DisplayHandler.printEmployeeList(el, choice);


        logger.info("Writing to questionables table");
        startTime = System.currentTimeMillis();
        /*
        Calls to create threads and start connecting to the database
        and inputting data for *****QUESTIONABLES*****
         */
        endTime = System.currentTimeMillis();
        DisplayHandler.printTimeTaken(startTime, endTime);
        choice = DisplayHandler.getQuestionableChoice();
        DisplayHandler.printEmployeeList(el, choice);





//        logger.info("Time taken to read in data from csv: " );
//        long startTotal = System.currentTimeMillis();
//        System.out.println("------------------------");
//        EmployeeDataAccessObject.getConnection();
//        System.out.println("------------------------");
    }
}
