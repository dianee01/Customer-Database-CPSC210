package ui;

import model.Customer;

import java.io.FileNotFoundException;

//Main, run ui from here
public class Main {
    public static void main(String[] args) {
        try {
            new CustomerDatabaseApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
