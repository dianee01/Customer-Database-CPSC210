package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageCustomer {

    private Scanner input;
    private CustomerDatabase cusData;

    public ManageCustomer() {
        runManageCustomer();
    }

    public void runManageCustomer() {
        boolean keepGoing = true;
        String command = null;

        cusData = new CustomerDatabase(new ArrayList<Customer>());
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenuCustomer();
            command = input.next();
            command = command.toLowerCase();

            if (command == "q") {
                keepGoing = false;
            } else {
                processCommandCustomer(command);
            }
        }
    }

    public void displayMenuCustomer() {
        System.out.println("\nSelect from:");
        System.out.println("\tc -> view all customers");
        System.out.println("\tr -> view regular customers");
        System.out.println("\tv -> view vip customers");
        System.out.println("\tcsize -> get total number of customers");
        System.out.println("\trsize -> get total number of regular customers");
        System.out.println("\tvsize -> get total number of vip customers");
        System.out.println("\tac -> add customer");
        System.out.println("\tu -> update customers");
        System.out.println("\ts -> sort vip customers");
    }

    public void processCommandCustomer(String command) {
        if (command.equals("c")) {
            viewCustomers();
        } else if (command.equals("r")) {
            viewRegularCustomers();
        } else if (command.equals("v")) {
            viewVipCustomers();
        } else if (command.equals("csize")) {
            numberCustomers();
        } else if (command.equals("rsize")) {
            numberRegularCustomers();
        } else if (command.equals("vsize")) {
            numberVipCustomers();
        } else if (command.equals("ac")) {
            addCustomers();
        } else if (command.equals("u")) {
            updateCustomers();
        } else if (command.equals("s")) {
            sortVipCustomers();
        } else {
            System.out.println("Selection not valid!");
        }
    }

    //view the customers in different groups
    public void viewCustomers() {

    }

    public void viewRegularCustomers() {

    }

    public void viewVipCustomers() {

    }

    //get different groups of customers' size
    public void numberCustomers() {

    }

    public void numberRegularCustomers() {

    }

    public void numberVipCustomers() {

    }

    //lets the user add a new user
    public void addCustomers() {

    }

    //lets the user update customer database
    public void updateCustomers() {

    }

    //lets the user sort his Vip customers
    public void sortVipCustomers() {

    }
}
