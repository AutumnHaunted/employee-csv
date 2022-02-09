package com.sparta.employeecsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

public class CSVReadHandler {

    private static String STRING_DELIMITER = ",";

    public static EmployeeLists readCSV(String filePath) {
        EmployeeLists el = new EmployeeLists    ();
        String curLine;
        String[] curLineCols;
        Employee curEmployee;

        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            while((curLine = br.readLine()) != null) {
                curLineCols = curLine.split(STRING_DELIMITER);
                for(int i = 0; i < curLineCols.length; i++) curLineCols[i] = curLineCols[i].trim();
                try {
                    curEmployee = new Employee( Integer.parseInt(curLineCols[0]),
                                                curLineCols[1],
                                                curLineCols[2],
                                                curLineCols[3].toUpperCase().charAt(0),
                                                curLineCols[4],
                                                curLineCols[5].toUpperCase().charAt(0),
                                                curLineCols[6],
                                                curLineCols[7],
                                                curLineCols[8],
                                                Integer.parseInt(curLineCols[9]));
                    if(EmployeeValidator.validate(curEmployee)) {
                        if(EmployeeValidator.isUnique(curEmployee)) {
                            el.addToEmployees(curEmployee);
                        } else {
                            el.addToDuplicates(curEmployee);
                        }
                    } else {
                        el.addToQuestionables(curEmployee);
                    }
                } catch(IllegalArgumentException e) {
                    if(!curLineCols[0].equals("Emp ID")) DisplayHandler.printInvalidEmployee(curLineCols);
                } catch(ArrayIndexOutOfBoundsException e) {
                    DisplayHandler.printInvalidEmployee(curLineCols);
                } catch(ParseException e) {
                    System.out.println(e.getClass().getName());
                    DisplayHandler.printInvalidEmployee(curLineCols);
                }
            }
        } catch(IOException e) {
            DisplayHandler.printError(e);
        }
        return el;
    }

}
