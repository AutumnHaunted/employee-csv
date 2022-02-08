package com.sparta.employeecsv;

public class Main {
    public static void main(String[] args) {
        EmployeeLists el = CSVReadHandler.readCSV(DisplayHandler.getFilePath());
        DisplayHandler.printEmployeeLists(el);
    }
}
