package com.sparta.employeecsv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReadHandler {

    private static String STRING_DELIMITER = ",";

    public static EmployeeList readCSV(String filePath) {
        EmployeeList el = new EmployeeList();
        
        Stream<Employee> csvStream = getCSVLines(filePath).stream().map(line -> line.split(STRING_DELIMITER)).map(line -> generateEmployee(line));
        Map<String, List<Employee>> groups = csvStream.collect(Collectors.groupingBy(e -> groupEmployee(e)));

        groups.get("employees").forEach(el::addToEmployees);
        groups.get("duplicates").forEach(el::addToDuplicates);
        groups.get("questionables").forEach(el::addToQuestionables);

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

    private static Employee generateEmployee(String[] line) {
        try {
            return new Employee( Integer.parseInt(line[0]),
                    line[1],
                    line[2],
                    line[3].toUpperCase().charAt(0),
                    line[4],
                    line[5].toUpperCase().charAt(0),
                    line[6],
                    line[7],
                    line[8],
                    Integer.parseInt(line[9]));
        } catch(IllegalArgumentException e) {
            if(!line[0].equals("Emp ID")) DisplayHandler.printInvalidEmployee(line);
        } catch(ArrayIndexOutOfBoundsException e) {
            DisplayHandler.printInvalidEmployee(line);
        } catch(ParseException e) {
            System.out.println(e.getClass().getName());
            DisplayHandler.printInvalidEmployee(line);
        } // else
        return null;
    }

    private static String groupEmployee(Employee e) {
        if(EmployeeValidator.validate(e)) {
            if(EmployeeValidator.isUnique(e)) return "employees";
            else return "duplicates";
        } else return "questionables";
    }

}
