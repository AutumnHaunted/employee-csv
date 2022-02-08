package com.sparta.employeecsv;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee extends People{

    private int EmpID;
    private String Date_of_Joining;


    private int Salary;

    public Employee(int empID, String prefix, String firstName, char middleInitial, String lastName, char gender, String email, String date_of_Birth, String date_of_Joining, int salary) throws ParseException {
        EmpID = empID;
        Prefix = prefix;
        FirstName = firstName;
        MiddleInitial = middleInitial;
        LastName = lastName;
        Gender = gender;
        Email = email;
        Date_of_Birth = date_of_Birth;
        Date_of_Joining = date_of_Joining;
        Salary = salary;
    }
    public Date formatDate(String s) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = null;
        try {
            date = formatter.parse(s);
            System.out.println("Date is: " + date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public int getEmpID() {
        return EmpID;
    }

    public void setEmpID(int empID) {
        EmpID = empID;
    }

    public String getDate_of_Joining() {
        return Date_of_Joining;
    }

    public void setDate_of_Joining(String date_of_Joining) {
        Date_of_Joining = date_of_Joining;
    }

    public int getSalary() {
        return Salary;
    }

    public void setSalary(int salary) {
        Salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "EmpID=" + EmpID +
                ", Date_of_Joining='" + Date_of_Joining + '\'' +
                ", Salary=" + Salary +
                ", Prefix='" + Prefix + '\'' +
                ", FirstName='" + FirstName + '\'' +
                ", MiddleInitial=" + MiddleInitial +
                ", LastName='" + LastName + '\'' +
                ", Gender=" + Gender +
                ", Email='" + Email + '\'' +
                ", Date_of_Birth='" + Date_of_Birth + '\'' +
                '}';
    }
}

