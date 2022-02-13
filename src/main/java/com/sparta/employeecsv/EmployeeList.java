package com.sparta.employeecsv;

import java.util.ArrayList;

public class EmployeeList {
    private ArrayList<Employee> employees;
    private ArrayList<Employee> duplicates;
    private ArrayList<Employee> questionables;


    public EmployeeList() {
        this.employees = new ArrayList<Employee>();
        this.duplicates = new ArrayList<Employee>();
        this.questionables = new ArrayList<Employee>();
    }
    public void addToEmployees(Employee e) {
        employees.add(e);
    }

    public void addToDuplicates(Employee e) {
        duplicates.add(e);
    }

    public void addToQuestionables(Employee e) {
        questionables.add(e);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public ArrayList<Employee> getDuplicates() {
        return duplicates;
    }

    public void setDuplicates(ArrayList<Employee> duplicates) {
        this.duplicates = duplicates;
    }

    public ArrayList<Employee> getQuestionables() {
        return questionables;
    }

    public void setQuestionables(ArrayList<Employee> questionables) {
        this.questionables = questionables;
    }
}
