package ui;

import model.Customer;
import model.CustomerDatabase;
import model.Item;
import model.Sales;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerDatabaseApp {

    private Scanner input;
    private CustomerDatabase cusData;
    private Sales mySales;

    //EFFECTS: run the CustomerDatabase App
    public CustomerDatabaseApp() {
        runCustomerDatabase();
    }

    //MODIFIES: this
    //EFFECTS: process user input
    public void runCustomerDatabase() {
        boolean keepGoing = true;
        String command = null;

        input = new Scanner(System.in);
        cusData = new CustomerDatabase(new ArrayList<Customer>());
        mySales = new Sales(new ArrayList<Item>());

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

    //EFFECTS: displays main menu
    public void displayMenuMain() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> manage customer");
        System.out.println("\ts -> manage sales");
    }

    //MODIFIES: this
    //EFFECTS: process user command
    public void processCommandMain(String command) {
        if (command.equals("c")) {
            new ManageCustomer(cusData, mySales);
        } else if (command.equals("s")) {
            new ManageSales(cusData, mySales);
        } else {
            System.out.println("Selection not valid!");
        }
    }
}
