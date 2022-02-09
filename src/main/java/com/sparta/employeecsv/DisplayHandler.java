package com.sparta.employeecsv;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class DisplayHandler {
    static Logger logger = LogManager.getLogger(DisplayHandler.class);
    private static final Properties properties = new Properties();

    private static void createProperties(){
        try{
            properties.load(new FileReader("src/main/resources/<insert.propertiesFileNameHere>"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void printInvalidEmployee(String[] entries) {
        System.out.println("The following record is not a valid employee: ");
        for(String s : entries) {
            System.out.print(s);
            System.out.print(", ");
        }
        System.out.println();
    }
    public static void printException(Exception e) {
        System.out.print("An exception occurred: ");
        System.out.println(e.getClass().getName());
    }

    public static String getFilePath(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a file path: ");
        return scanner.nextLine();
    }
}
