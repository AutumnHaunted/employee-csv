package com.sparta.employeecsv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import static com.sparta.employeecsv.EmployeeDataAccessObject.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeDAOTest {



    @Test
    @DisplayName("Check Connection is closes")
    public void checkConnectionCloses() throws ParseException
    {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee e;
        Object el = null;
        try {
            e = new Employee(19843, "Mrs.", "Serafina", 'I', "Bumgarner", 'F', "serafina.bumgarner@exxonmobil.com", "9/21/1982", "2/1/2008", 69294);
            employees.add(e);
            //insertData(e, getConnection());
            dropAndCreateTable(employees,"testing3", getConnection());
            closeConnection();
            boolean actual = getConnection().isClosed();
            boolean expected2=true;
            assertEquals(expected2,actual);
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    @Test
    @DisplayName("Check Connection is opens")
    public void checkConnectionOpens() throws ParseException
    {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee e;
        Object el = null;
        try {
            e = new Employee(19843, "Mrs.", "Serafina", 'I', "Bumgarner", 'F', "serafina.bumgarner@exxonmobil.com", "9/21/1982", "2/1/2008", 69294);
            employees.add(e);
            //insertData(e, getConnection());
            dropAndCreateTable(employees,"testing3", getConnection());
            boolean actual = getConnection().isClosed();
            boolean expected2=false;
            assertEquals(expected2,actual);
            closeConnection();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    // not working, Values are the same but it wont say its equal
    @Test
    @DisplayName("Given a  valid employee return true")
    public void givenAValidEmployee_ReturnTrue() throws ParseException
    {
        ArrayList<Employee> employees = new ArrayList<>();
        Employee e;
        Object el = null;
        try {
            e = new Employee(19843, "Mrs.", "Serafina", 'I', "Bumgarner", 'F', "serafina.bumgarner@exxonmobil.com", "9/21/1982", "2/1/2008", 69294);
            employees.add(e);
            //insertData(e, getConnection());
            dropAndCreateTable(employees,"testing3", getConnection());
            String s=queryDataBase("SELECT * FROM testing3;");
            String expected="19843Mrs.SerafinaIBumgarnerFserafina.bumgarner@exxonmobil.com1982-09-212008-02-0169294 ";
            closeConnection();
            boolean actual = expected.equals(s);
            boolean expected2=true;
            assertEquals(s,expected);
        } catch (ParseException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
