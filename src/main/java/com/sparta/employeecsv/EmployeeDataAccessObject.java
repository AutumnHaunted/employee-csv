package com.sparta.employeecsv;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class EmployeeDataAccessObject {
    private static Connection connection;
    private static final Properties properties = new Properties();


    private static void createProperties(){
        try{
            properties.load(new FileReader("src/main/resources/<Insert.propertiesFileNameHere>"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void connectToDataBase(String url){
        createProperties();
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        try{
            connection = DriverManager.getConnection(url,username,password);

        }catch(SQLException e){
            e.printStackTrace();
        }

        System.out.println("Connected to DataBase");

    }

    public static void queryDataBase(String query){
        StringBuilder sb = new StringBuilder();
        Statement statement = null;
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

    }
    public static void insertData(Employee e, Connection thisConnection){
        try {
            PreparedStatement preparedStatement = thisConnection.prepareStatement(
         // Insert SQL Statement Here           "insert INTO `tester`.`employees` (`emp_id`,`name_prefix`,`first_name`, `middle_initial`, " +
         //                   "`last_name`, `gender`,`email`, `dob`, `date_joined`, `salary`) " +
         //                   "values (?,?,?,?,?,?,?,?,?,?)");


            preparedStatement = setEmployeeVars(preparedStatement, e);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static PreparedStatement setEmployeeVars(PreparedStatement preparedStatement, Employee e) throws SQLException {
        preparedStatement.setInt(1,e.getEmpID()));
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


    public static void insertListOfEmployees(ArrayList<Employee> employeeList, Connection thisConnection){
        System.out.println("Implementing EmployeeList into Database...");
        for(Employee employee: employeeList){
            insertData(employee, thisConnection);
        }
    }
    public static void insertInBatches(ArrayList<Employee> employeeList, Connection thisConnection)  {

        try {
            PreparedStatement preparedStatement = thisConnection.prepareStatement(
        //Insert SQL Statement here       //     "insert INTO `tester`.`employees` (`emp_id`,`name_prefix`,`first_name`, `middle_initial`, " +
                //            "`last_name`, `gender`,`email`, `dob`, `date_joined`, `salary`) " +
                 //           "values (?,?,?,?,?,?,?,?,?,?)");

            for(Employee employee : employeeList) {
                preparedStatement = setEmployeeVars(preparedStatement, employee);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}


