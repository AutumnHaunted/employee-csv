package com.sparta.employeecsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.sparta.employeecsv.EmployeeDataAccessObject.queryDataBase;

public class Main {
    static Logger logger = LogManager.getLogger(DisplayHandler.class);
    public static void main(String[] args) throws SQLException, IOException {
        boolean choice = false; // for questionable
        long endTime;

        Main.logger.info("Program started");
        String filePath = DisplayHandler.getFilePath();
        System.out.println("Reading in .csv file...");
        EmployeeList el = CSVReadHandler.readCSV(filePath);
        System.out.println("Setting up tables");
        Connection connection = EmployeeDataAccessObject.getConnection();
        EmployeeDataAccessObject.dropAndCreateTable("employee",connection);
        EmployeeDataAccessObject.dropAndCreateTable("duplicates",connection);
        EmployeeDataAccessObject.dropAndCreateTable("questionables",connection);

        DisplayHandler.printEmployeeList(el.getEmployees(), choice);
        DisplayHandler.printEmployeeList(el.getDuplicates(), choice);
        choice = DisplayHandler.getQuestionableChoice();
        DisplayHandler.printEmployeeList(el.getQuestionables(), choice);

        int numberOfThreads = DisplayHandler.getThreads();

        ThreadManager tm = new ThreadManager(el.getEmployees(), numberOfThreads,"employee");
        tm.splitEmployeeList();
        tm.runThreads();



//        ExecutorService pool = Executors.newFixedThreadPool(numberOfThreads);

        logger.info("Writing to Employee");

//        for(Employee employee:el.getEmployees()){
//            Runnable rEmployee =  new EmployeeThread(employee,connection,"employee");
//            pool.execute(rEmployee);
//        }

//        ArrayList<Thread> threads = new ArrayList<>();


//        long startTime = System.currentTimeMillis();
//
////        pool.shutdown();
////        try {
////            pool.awaitTermination(30,TimeUnit.SECONDS);
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
//        try{
//            for(Thread thread: threads){
//                thread.join();
//            }
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
//        endTime = System.currentTimeMillis();
//        DisplayHandler.printTimeTaken(startTime,endTime);
        queryDataBase("SELECT * FROM employee;");

        EmployeeDataAccessObject.closeConnection();

    }
}




       // pool.execute();






        /*
        Calls to create threads and start connecting to the database
        and inputting data for ****VALID EMPLOYEES****
         */


//
//
//        logger.info("Writing to duplicates");
//
//        /*
//        Calls to create threads and start connecting to the database
//        and inputting data for ****DUPLICATES****
//         */
//
//
//
//
//
//        logger.info("Writing to questionables table");
//        /*
//        Calls to create threads and start connecting to the database
//        and inputting data for *****QUESTIONABLES*****
//         */
//
//
//
//
