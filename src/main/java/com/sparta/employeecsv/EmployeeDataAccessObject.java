package com.sparta.employeecsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import java.sql.SQLException;

public class EmployeeDataAccessObject {
    private static final Logger logger = LogManager.getLogger("EDAO logger:");
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
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
            logger.info("Database credentials fetched");
            return connection;
        }
        return connection;
    }



    public  static void closeConnection() throws SQLException{
        if (connection!=null){
            connection.close();
        }
        logger.info("Connection closed");
    }


    public static String queryDataBase(String query){
        StringBuilder sb = new StringBuilder();
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                sb.append(resultSet.getString(1));
                sb.append(resultSet.getString(2));
                sb.append(resultSet.getString(3));
                sb.append(resultSet.getString(4));
                sb.append(resultSet.getString(5));
                sb.append(resultSet.getString(6));
                sb.append(resultSet.getString(7));
                sb.append(resultSet.getDate(8));
                sb.append(resultSet.getDate(9));
                sb.append(resultSet.getInt(10));
                sb.append("\n");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(sb.toString());
        logger.info("Query returned from Database");
        return sb.toString();
    }
    public static void insertData(Employee e, String listName, Connection thisConnection){
        try {
            PreparedStatement preparedStatement = thisConnection.prepareStatement(
//          Insert SQL Statement Here
          "insert INTO `employeelist`.`"+listName+"` (`EmpID`,`Prefix`,`First_Name`, `Middle_Initial`, " +
                            "`Last_Name`, `Gender`,`Email`, `Date_Of_Birth`, `Date_Of_Joining`, `Salary`) " +
                            "values (?,?,?,?,?,?,?,?,?,?)");


            preparedStatement = setEmployeeVars(preparedStatement, e);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static PreparedStatement setEmployeeVars(PreparedStatement preparedStatement, Employee e) throws SQLException {
        preparedStatement.setInt(1, e.getEmpID());
        preparedStatement.setString(2, e.getPrefix());
        preparedStatement.setString(3, e.getFirstName());
        preparedStatement.setString(4, String.valueOf(e.getMiddleInitial()));
        preparedStatement.setString(5, e.getLastName());
        preparedStatement.setString(6, String.valueOf(e.getGender()));
        preparedStatement.setString(7, e.getEmail());
        preparedStatement.setDate(8, Date.valueOf(e.getDateOfBirth()));
        preparedStatement.setDate(9, Date.valueOf(e.getDateOfJoining()));
        preparedStatement.setInt(10, e.getSalary());

        return preparedStatement;
    }

    public static void dropTable(String listName, Connection thisConnection){
        try {
            PreparedStatement preparedStatement = thisConnection.prepareStatement(
                    "DROP TABLE IF EXISTS `employeelist`.`"+listName+"`");
            //System.out.println("Table dropped:"+ listName);
            preparedStatement.execute();
    } catch (SQLException e) {
            e.printStackTrace();
        }
        logger.info("Table dropped from Database");}

    public static void dropAndCreateTable(String listName, Connection thisConnection){
        try {
            dropTable(listName,thisConnection); //drops table if exists
            PreparedStatement preparedStatement = thisConnection.prepareStatement(

                    "CREATE TABLE `employeelist`.`"+listName+"` (\n" +
                            "                    \n" +
                            "             `EmpID` INT NOT NULL,\n" +
                            "            `Prefix` VARCHAR(45) NOT NULL,\n" +
                            "                `First_Name` VARCHAR(45) NOT NULL,\n" +
                            "            `Middle_Initial` Char(5) NOT NULL,\n" +
                            "            `Last_Name` VARCHAR(45) NOT NULL,\n" +
                            "                `Gender` CHAR (3) NOT NULL,\n" +
                            "              `Email` VARCHAR (45) NOT NULL,\n" +
                            "            `Date_Of_Birth` VARCHAR(45) NOT NULL,\n" +
                            "                `Date_Of_Joining` VARCHAR(45) NOT NULL,\n" +
                            "                    `Salary` INT NOT NULL,\n" +
                            "                    PRIMARY KEY (`EmpID`));");
            preparedStatement.execute();
//            if(list.contains(null)){
//                logger.warn(listName + " is empty");
//            }else {
//                for (Employee employee : list) {
//                    insertData(employee, listName, thisConnection);
//                }
//            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
//finally {
//        closeConnection();
//    }

}


