package com.sparta.employeecsv;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class EmployeeValidator {
    private static HashMap<Integer, Employee> uniqueEmployees;


    EmployeeValidator(){
        uniqueEmployees = new HashMap<Integer, Employee>();
    }


    public boolean validate(Employee emp) {
        if (validateNamePrefix(emp.getPrefix()) && validateGender(emp.getGender()) && validateEmail(emp.getEmail())){
            return true;
        };
    return false;
    }
    public boolean validateNamePrefix(String prefix){
        if (prefix.equals("Mrs.") || prefix.equals("Mr.") || prefix.equals("Dr.")|| prefix.equals("Hon.")|| prefix.equals("Ms.") || prefix.equals("Drs.") || prefix.equals("Prof.")){
            return true;
        }
        return false;
    }
    public boolean validateGender(char gender){
        if (gender == 'M' || gender == 'F' || gender == 'X'){
            return true;
        }
        return false;
    }
    public boolean validateEmail(String email){
        return true;
    }

    public static boolean isUnique(Employee emp){
            if (uniqueEmployees.containsKey(emp.getEmpID())) {
                return false;
            }
            uniqueEmployees.put(emp.getEmpID(), emp);
            return true;
        }
    }
