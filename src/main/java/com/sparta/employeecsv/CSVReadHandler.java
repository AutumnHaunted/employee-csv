package com.sparta.employeecsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.stream.Stream;

public class CSVReadHandler {

    private static String STRING_DELIMITER = ",";

    public static EmployeeList readCSV(String filePath) {
        EmployeeList el = new EmployeeList();
        Employee curEmployee;

        getCSVLines(filePath).stream()
                .map(line -> line.split(STRING_DELIMITER))
                .forEach(line -> {/*add to the correct list in el*/});


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
            DisplayHandler.printException(e);
        }
        return el;
    }

    private static ArrayList<String> getCSVLines(String filePath) {
        ArrayList<String> csvLines = new ArrayList<>();
        String curLine;
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while ((curLine = br.readLine()) != null) {
                csvLines.add(curLine);
            }
        } catch (IOException e) {
            DisplayHandler.printException(e);
        }
        return csvLines;
    }

}
