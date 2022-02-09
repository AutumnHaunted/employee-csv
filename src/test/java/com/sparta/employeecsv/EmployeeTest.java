package com.sparta.employeecsv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Date;

import static com.sparta.employeecsv.Employee.formatDate;
import static com.sparta.employeecsv.Employee.printDate;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class EmployeeTest {
    @Test
    @DisplayName("Employee: checking string to date and date to string methods")
    public void bstSortNegArray() throws ParseException {
            String a="9/21/1982";
            Date ab = formatDate(a);
            String b= printDate(ab);
            String expected= "09/21/1982";
            String result=b;
            //arrange
            //Assert
            assertEquals(expected, result);

    }

}
