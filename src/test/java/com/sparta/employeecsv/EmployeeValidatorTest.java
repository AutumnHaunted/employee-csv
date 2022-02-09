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
        Employee emp1 = new Employee();
        emp1.setEmpID(1);
        Employee emp2 = new Employee();
        emp2.setEmpID(1);
        EmployeeValidator validate = new EmployeeValidator();
        validate.isUnique(emp2);
        boolean actual = validate.isUnique(emp2);
        boolean expected = false;
        assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Given a unique employee id, return true")
    public void givenAUniqueEmpID_ReturnTrue(){
        Employee emp1 = new Employee();
        emp1.setEmpID(1);
        Employee emp2 = new Employee();
        emp2.setEmpID(2);
        EmployeeValidator validate = new EmployeeValidator();

        validate.isUnique(emp1);
        boolean actual = validate.isUnique(emp2);
        boolean expected = true;

        assertEquals(expected, actual);
    }
}
