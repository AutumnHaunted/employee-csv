package com.sparta.employeecsv;



public abstract class People {
    String Prefix;
    String FirstName;
    char MiddleInitial;
    String LastName;
    char Gender;
    String Email;
    String Date_of_Birth;

    public People() {
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public char getMiddleInitial() {
        return MiddleInitial;
    }

    public void setMiddleInitial(char middleInitial) {
        MiddleInitial = middleInitial;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public char getGender() {
        return Gender;
    }

    public void setGender(char gender) {
        Gender = gender;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getDate_of_Birth() {
        return Date_of_Birth;
    }

    public void setDate_of_Birth(String date_of_Birth) {
        Date_of_Birth = date_of_Birth;
    }
}
