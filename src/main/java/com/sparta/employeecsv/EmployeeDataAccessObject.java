package com.sparta.employeecsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

import java.sql.SQLException;

public class EmployeeDataAccessObject {
    private static Connection connection = null;
    private static final Properties properties = new Properties();


    private static void createProperties(){
        try{
            properties.load(new FileReader("src/main/resources/<Insert.propertiesFileNameHere>"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

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
            System.out.println("Connected");
            return connection;
        }
        return connection;
    }
    public  static void closeConnection() throws SQLException{
        if (connection!=null){
            connection.close();
        }
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
//          Insert SQL Statement Here
          "insert INTO `tester`.`employees` (`emp_id`,`name_prefix`,`first_name`, `middle_initial`, " +
                            "`last_name`, `gender`,`email`, `dob`, `date_joined`, `salary`) " +
                            "values (?,?,?,?,?,?,?,?,?,?)");


            preparedStatement = setEmployeeVars(preparedStatement, e);
            preparedStatement.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private static PreparedStatement setEmployeeVars(PreparedStatement preparedStatement, Employee e) throws SQLException {
        preparedStatement.setInt(1,e.getEmpID());
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
        //System.out.println("Populating Database with Employee List...");
        for(Employee employee: employeeList){
            insertData(employee, thisConnection);
        }
    }
    public static void insertInBatches(ArrayList<Employee> employeeList, Connection thisConnection)  {

        try {
            PreparedStatement preparedStatement = thisConnection.prepareStatement(
        //Insert SQL Statement here
                         "insert INTO `tester`.`employees` (`emp_id`,`name_prefix`,`first_name`, `middle_initial`, " +
                            "`last_name`, `gender`,`email`, `dob`, `date_joined`, `salary`) " +
                            "values (?,?,?,?,?,?,?,?,?,?)");

            for(Employee employee : employeeList) {
                preparedStatement = setEmployeeVars(preparedStatement, employee);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    private static void insertPrepared() throws SQLException {
        PreparedStatement preparedStatement= null;
        try{
            Connection connection= getConnection();
            //? is a placeholder
            //sql syntax INSERT INTO actor (first_name,last_name) VALUES (?,?)
            preparedStatement = connection.prepareStatement(
                    "UPDATE actor SET last_name = ? where actor_id= ?");
            preparedStatement.setString(1,"Master");
            preparedStatement.setInt(2,199);
            preparedStatement.executeUpdate();
            int rowsAffected =preparedStatement.executeUpdate();
            System.out.println(rowsAffected);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
    public static void main(String[] args) throws SQLException {
        insertPrepared();

    }
}


