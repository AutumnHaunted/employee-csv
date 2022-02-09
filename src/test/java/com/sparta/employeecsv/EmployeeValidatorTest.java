package com.sparta.employeecsv;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeValidatorTest {

    @Test
    @DisplayName("Given a duplicate employee id, return false")
    public void givenADuplicateEmpID_ReturnFalse() throws ParseException {
        Employee emp1 = new Employee(123456, "Mr.","Harry",'I',"Lewis", 'M',
                "harryh@h.com", "09/14/1994", "13/12/13",60000);

        Employee emp2 = new Employee(123456, "Mr.","Harry",'I',"Lewis", 'M',
                "harryh@h.com", "09/14/1994", "13/12/13",60000);



        boolean actual = EmployeeValidator.isUnique(emp2);
        boolean expected = false;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Given a unique employee id, return true")
    public void givenAUniqueEmpID_ReturnTrue() throws ParseException {
        Employee emp1 = new Employee(123456, "Mr.","Harry",'I',"Lewis", 'M',
                "harryh@h.com", "09/14/1994", "13/12/13",60000);

        Employee emp2 = new Employee(123457, "Mr.","Harry",'I',"Lewis", 'M',
                "harryh@h.com", "09/14/1994", "13/12/13",60000);


        boolean actual = EmployeeValidator.isUnique(emp2);
        boolean expected = true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Given a  valid employee return true")
    public void givenAValidEmployee_ReturnTrue() throws ParseException {
        Employee emp1 = new Employee(123456, "Mr.","Harry",'I',"Lewis", 'M',
                "harryh@h.com", "09/14/1994", "13/12/13",60000);
        boolean actual = EmployeeValidator.validate(emp1);
        boolean expected =  true;
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Given an invalid employee prefix return false")
    public void givenAnInvalidEmployeePrefix_ReturnFalse() throws ParseException {
        Employee emp1 = new Employee(123456, "","Harry",'I',"Lewis", 'M',
                "harryh@h.com", "09/14/1994", "13/12/13",60000);
        boolean actual = EmployeeValidator.validate(emp1);
        boolean expected = false;
        assertEquals(expected,actual);
    }


    @Test
    @DisplayName("Given an invalid employee prefix return false")
    public void givenAnInvalidEmployeeGender_ReturnFalse() throws ParseException {
        Employee emp1 = new Employee(123456, "Mr.","Harry",'I',"Lewis", 'I',
                "harryh@h.com", "09/14/1994", "13/12/13",60000);
        boolean actual = EmployeeValidator.validate(emp1);
        boolean expected = false;
        assertEquals(expected,actual);
    }
    @Test
    @DisplayName("Given an invalid employee prefix return false")
    public void givenAnInvalidEmployeeEmail_ReturnFalse() throws ParseException {
        Employee emp1 = new Employee(123456, "","Harry",'I',"Lewis", 'M',
                "harryhh.com", "09/14/1994", "13/12/13",60000);
        boolean actual = EmployeeValidator.validate(emp1);
        boolean expected = false;
        assertEquals(expected,actual);
    }


}
