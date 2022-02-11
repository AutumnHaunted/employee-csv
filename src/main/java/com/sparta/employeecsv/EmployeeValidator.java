package com.sparta.employeecsv;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

public class EmployeeValidator {


    public static boolean validate(Employee emp) {
        if ((emp != null) && validateNamePrefix(emp.getPrefix()) && validateGender(emp.getGender()) && validateEmail(emp.getEmail())) {
            return true;
        }
        ;
        return false;
    }

    private static boolean validateNamePrefix(String prefix) {
        if (prefix.equals("Mrs.") || prefix.equals("Mr.") || prefix.equals("Dr.") || prefix.equals("Hon.") || prefix.equals("Ms.") || prefix.equals("Drs.") || prefix.equals("Prof.")) {
            return true;
        }
        return false;
    }

    private static boolean validateGender(char gender) {
        if (gender == 'M' || gender == 'F' || gender == 'X') {
            return true;
        }
        return false;
    }

    private static boolean validateEmail(String email) {
        boolean result = true;
        Pattern regexEmailPattern = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
        Matcher matcher = regexEmailPattern.matcher(email);
        boolean correct = matcher.find();
        if (correct) {
            return true;
        }
        return false;
    }


    public static boolean isUnique(EmployeeList el, Employee emp) {

        for (int i = 0; i < el.getEmployees().size(); i++) {
            if (el.getEmployees().get(i).getEmpID()== emp.getEmpID()){
                return false;
            }

        }
        return true;
    }
}
