package com.sparta.employeecsv;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import static com.sparta.employeecsv.Main.logger;

public class ThreadManager {
    private Thread[] employeeThreads;
    private int size;
    private ArrayList<ArrayList<Employee>> splitList;
    private ArrayList<Employee> employeeList;

    private String tableName;

    public ThreadManager(ArrayList<Employee> employeeList, int size, String tableName){
        this.size = size;
        this.employeeList = employeeList;

        splitList = new ArrayList<>(size);
        this.tableName = tableName;

    }
    // this splits the array into smaller arrays, and then each thread works on 1 smaller array
    public void splitEmployeeList(){
        int startIndexOfSplit = 0;
        int interval = employeeList.size() / size;

        for(int i = 0; i < size ; i ++){
            ArrayList<Employee> splitToAdd = new ArrayList<>(employeeList.subList(startIndexOfSplit, startIndexOfSplit + interval));
            splitList.add(splitToAdd);
            startIndexOfSplit += interval;
        }

        int leftOverRecords = employeeList.size() - (interval * size);

        if(leftOverRecords > 0){
            for(int i = employeeList.size() - leftOverRecords; i < employeeList.size();i++) {
                splitList.get(0).add(employeeList.get(i));
            }
        }
    }
    public void runThreads(){
        logger.info("Creating " + size + " threads");
        try {
            employeeThreads = new Thread[size];

            long start = System.currentTimeMillis();
            for(int i = 0 ; i < employeeThreads.length; i++){
                employeeThreads[i] = new Thread( new EmployeeThread(splitList.get(i),tableName));
                employeeThreads[i].start();

                System.out.println(employeeThreads[i] + " Started");
            }

            for(int i = 0 ; i < employeeThreads.length; i++){
                employeeThreads[i].join();
            }
            System.out.println("Time taken for threads to run: " + (System.currentTimeMillis() - start) + " ms");
            logger.info("Time taken for threads to run: " + (System.currentTimeMillis() - start) + " ms");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


