package com.sparta.employeecsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException,IOException {
        if(connection==null) {
            Properties props = new Properties();
            try {
                props.load(new FileReader("mysql.properties"));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            connection = DriverManager.getConnection(
                    props.getProperty("db.url"),
                    props.getProperty("db.userID"),
                    props.getProperty("db.password"));
            //jdbc: what you are using : ip address or machine : port number : database
            return connection;
        }
        return connection;
    }
    public  static void closeConnection() throws SQLException{
        if (connection!=null){
            connection.close();
        }
    }
}


