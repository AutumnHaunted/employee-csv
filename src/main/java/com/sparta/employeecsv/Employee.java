package com.sparta.employeecsv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee extends People{

    private int empID;
    private String prefix;
    private String firstName;
    private char middleInitial;
    private String lastName;
    private char gender;
    private String email;
    private LocalDate dateOfBirth;
    private LocalDate dateOfJoining;
    private int salary;

    public Employee(int empID, String prefix, String firstName, char middleInitial, String lastName, char gender, String email, String dateOfBirth, String dateOfJoining, int salary) throws ParseException {
        this.empID = empID;
        this.prefix = prefix;
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.lastName = lastName;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = formatDate(dateOfBirth);
        this.dateOfJoining = formatDate(dateOfJoining);
        this.salary = salary;
    }

    public Employee() {

    }

    private LocalDate formatDate(String s) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
        LocalDate date = LocalDate.parse(s, formatter);
        // System.out.println("Date is: " + date); //
        return date;
    }
    public static String printDate(LocalDate a) throws ParseException {
            SimpleDateFormat dt1 = new SimpleDateFormat("MM-dd-yyyy");
            System.out.println(dt1.format(a));
            return dt1.format(a);
    }

    public int getEmpID() { return empID; }
    public String getPrefix() { return prefix; }
    public String getFirstName() { return firstName; }
    public char getMiddleInitial() { return middleInitial; }
    public String getLastName() { return lastName; }
    public char getGender() { return gender; }
    public String getEmail() { return email; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public LocalDate getDateOfJoining() { return dateOfJoining; }
    public int getSalary() { return salary; }

    public void setEmpID(int empID) {
        this.empID = empID;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public void setMiddleInitial(char middleInitial) {
        this.middleInitial = middleInitial;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setDateOfJoining(LocalDate dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "Employee ID=" + empID +
                ", Date of Joining='" + dateOfJoining + '\'' +
                ", Salary=" + salary +
                ", Prefix='" + prefix + '\'' +
                ", First Name='" + firstName + '\'' +
                ", Middle Initial=" + middleInitial +
                ", Last Name='" + lastName + '\'' +
                ", Gender=" + gender +
                ", Email='" + email + '\'' +
                ", Date of Birth='" + dateOfBirth + '\'' +
                '}';
    }

}

