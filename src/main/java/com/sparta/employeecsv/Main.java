package com.sparta.employeecsv;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Main {
    static Logger logger = LogManager.getLogger(DisplayHandler.class);
    public static void main(String[] args) {
        EmployeeList el = CSVReadHandler.readCSV(DisplayHandler.getFilePath());
        DisplayHandler.printEmployeeLists(el);
    }
}
