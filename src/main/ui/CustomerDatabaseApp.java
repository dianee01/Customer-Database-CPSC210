package ui;

import model.Customer;
import model.CustomerDatabase;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDatabaseApp {

    private Scanner input;

    public CustomerDatabaseApp() {
        runCustomerDatabase();
    }

    public void runCustomerDatabase() {
        boolean keepGoing = true;
        String command = null;

        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenuMain();
            command = input.next();
            command = command.toLowerCase();

            if (command == "q") {
                keepGoing = false;
            } else {
                processCommandMain(command);
            }
        }
    }

    public void displayMenuMain() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> manage customer");
        System.out.println("\ts -> manage sales");
    }

    public void processCommandMain(String command) {
        if (command.equals("c")) {
            new ManageCustomer();
        } else if (command.equals("s")) {
            new ManageSales();
        } else {
            System.out.println("Selection not valid!");
        }
    }
}
