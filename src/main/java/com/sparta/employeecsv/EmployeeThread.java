package com.sparta.employeecsv;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.sparta.employeecsv.Main.logger;

public class EmployeeThread implements Runnable {
        private ArrayList<Employee> employeeList;
        private Connection connection;
        private String employee;
        private static final Properties properties = new Properties();

        public EmployeeThread(ArrayList<Employee> employeeList, String employee){
            this.employeeList = employeeList;
            this.employee= employee;

        }

        private static void createProperties(){
            try{
                properties.load(new FileReader("src/main/resources/<Insert fileName.properties> here"));
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            createProperties();
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String url = properties.getProperty("url");
            url = url + "?rewriteBatchedStatements=true";

            System.out.println(Thread.currentThread().getName() + " started");
            long start = 0;
            try {
                connection = DriverManager.getConnection(url,username,password);
                start = System.currentTimeMillis();
                //EmployeeDataAccessObject.insertInBatches(employeeList,employee,connection);
                EmployeeDataAccessObject.dropAndCreateTable(employeeList,employee,connection);

                connection.close();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + " Complete! Took " + (System.currentTimeMillis() - start) + " ms");
            logger.info(Thread.currentThread().getName() + " Complete! Took " + (System.currentTimeMillis() - start) + " ms");
        }
    }


