package com.sparta.employeecsv;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CSVReadHandlerTest {

    @DisplayName("Test that the CSV Read Handler can read a single-line CSV")
    @Test
    public static void readSingleRecord() {
        EmployeeLists el = new EmployeeLists();
        el.addToEmployees(new Employee(198429,"Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com","9/21/1982","2/1/2008",69294));
        assertEquals(el, CSVReadHandler.readCSV("readSingleRecord.csv"));
    }

    @DisplayName("Test that the CSV Read Handler can read a CSV with one line for column headings and one line for the record.")
    @Test
    public static void readSingleRecordWithColumnHeadings() {
        EmployeeLists el = new EmployeeLists();
        el.addToEmployees(new Employee(198429,"Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com","9/21/1982","2/1/2008",69294));
        assertEquals(el, CSVReadHandler.readCSV("readSingleRecordWithColumnHeadings.csv"));
    }

    @DisplayName("Test that the CSV Read Handler can read a CSV with multiple lines")
    @Test
    public static void readMultipleRecords() {
        EmployeeLists el = new EmployeeLists();
        el.addToEmployees(new Employee(198429,"Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com","9/21/1982","2/1/2008",69294));
        el.addToEmployees(new Employee(178566,"Mrs.","Juliette","M","Rojo","F","juliette.rojo@yahoo.co.uk","5/8/1967","6/4/2011",193912));
        assertEquals(el, CSVReadHandler.readCSV("readMultipleRecords.csv"));
    }

    @DisplayName("Test that the CSV Read Handler can read a CSV with a duplicate record")
    @Test
    public static void readDuplicateRecords() {
        EmployeeLists el = new EmployeeLists();
        el.addToEmployees(new Employee(198429,"Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com","9/21/1982","2/1/2008",69294));
        el.addToDuplicates(new Employee(198429,"Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com","9/21/1982","2/1/2008",69294));
        assertEquals(el, CSVReadHandler.readCSV("readDuplicateRecords.csv"));
    }

    @DisplayName("Test that the CSV Read Handler can read a CSV with a questionable record")
    @Test
    public static void readQuestionableRecords() {
        EmployeeLists el = new EmployeeLists();
        el.addToEmployees(new Employee(198429,"Mrs.","Serafina","I","Bumgarner","F","serafina.bumgarner@exxonmobil.com","9/21/1982","2/1/2008",69294));
        el.addToEmployees(new Employee(178566,"Mrs.","Juliette","M","Rojo","F","juliette.rojo'yahoo.co.uk","5/8/1967","6/4/2011",193912));
        assertEquals(el, CSVReadHandler.readCSV("readQuestionableRecords.csv"));
    }
}
