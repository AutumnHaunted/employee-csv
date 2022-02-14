package com.sparta.employeecsv;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.Clock;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Properties;

import static com.sparta.employeecsv.Main.logger;

public class EmployeeThread implements Runnable {
    private final ArrayList<Employee> list;
    private Connection connection;
    private String tableName;



    private static final Properties properties = new Properties();
    public EmployeeThread(ArrayList<Employee> list, String tableName) throws SQLException {
        this.list = list;
        this.connection = EmployeeDataAccessObject.getConnection();
        this.tableName = tableName;

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
            System.out.println(list.size());
            System.out.println("starting" + ZonedDateTime.now());
            populateDatabase();
            System.out.println("finishing"+ ZonedDateTime.now());
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        public void populateDatabase(){

            for (Employee employee : list) {

                EmployeeDataAccessObject.insertData(employee, tableName, connection);


            }
        }

    }



