package com.sparta.employeecsv;

public class Main {
    public static void main(String[] args) {
        EmployeeList el = CSVReadHandler.readCSV(DisplayHandler.getFilePath());
        DisplayHandler.printEmployeeLists(el);
    }
}
