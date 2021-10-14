package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageCustomer {

    private Scanner input;
    private CustomerDatabase cusData;
    private Sales mySales;

    //EFFECTS: run ManageCustomer
    public ManageCustomer(CustomerDatabase cusData, Sales mySales) {
        this.cusData = cusData;
        this.mySales = mySales;
        runManageCustomer();
    }

    //MODIFIES: this
    //EFFECTS: process user input
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

    //EFFECTS: displays main menu
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

    //MODIFIES: this
    //EFFECTS: process user command
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

    //EFFECTS: view all existing customers in a list
    public void viewCustomers() {
        System.out.print(cusData.getCustomers());
    }

    //EFFECTS: view all existing regular customers in a list
    public void viewRegularCustomers() {
        System.out.print(cusData.getRegularCustomers());
    }

    //EFFECTS: view all existing vip customers in a list
    public void viewVipCustomers() {
        System.out.print(cusData.getVipCustomers());
    }

    //get different groups of customers' size

    //EFFECTS: get the number of total existing numbers
    public void numberCustomers() {
        System.out.print(cusData.totalCustomerSize());
    }

    //EFFECTS: get the number of total existing regular numbers
    public void numberRegularCustomers() {
        System.out.print(cusData.totalRegularCustomer());
    }

    //EFFECTS: get the number of total existing vip numbers
    public void numberVipCustomers() {
        System.out.print(cusData.totalVipCustomer());
    }

    //MODIFIES: this
    //EFFECTS: lets the user add a new user
    public void addCustomers() {
        System.out.print("How many customers do you want to add?");
        System.out.print("Please enter: ");
        int customer = input.nextInt();

        if (customer > 0) {
            addOneCustomer(customer);
        } else {
            System.out.print("The number of new customer cannot be zero.");
        }

    }

    //MODIFIES: this
    //EFFECTS: add a single customer to customer database, helper function for addCustomers()
    public void addOneCustomer(int numberCustomer) {
        int customer = numberCustomer;
        for (int i = 0; i < customer; i++) {
            System.out.print("What is this customer's name?");
            System.out.print("Please enter: ");
            String name = input.next();
            System.out.print("How many items did this customer's purchase?");
            System.out.print("Please enter: ");
            int purchase = input.nextInt();
            if (!name.equals("") && purchase > 0) {
                Customer c = new Customer("name", new ArrayList<Item>(purchase));
                addPurchaseForCustomer(c);
            } else {
                System.out.print("The above answers cannot be empty or zero.");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: add a single purchase to the customer, helper function for addOneCustomer(int numberCustomer)
    public void addPurchaseForCustomer(Customer c) {
        for (int i = 0; i < c.purchaseCount(); i++) {
            System.out.print("What is the name of this item?");
            System.out.print("Please enter: ");
            String itemName = input.next();
            itemName = itemName.toLowerCase();
            System.out.print("What is the price of this item?");
            System.out.print("Please enter: ");
            double itemPrice = input.nextDouble();
            System.out.print("What is the date of sales of this item?");
            System.out.print("Please enter: ");
            int year = input.nextInt();
            if (!itemName.equals("") && itemPrice > 0.0 && year > 0) {
                Item newItem = new Item(itemName, itemPrice, new Date(year));
                c.addPurchase(newItem, mySales);
            } else {
                System.out.print("The above answers cannot be blank or empty.");
            }
        }
    }

    //MODIFIES: this
    //EFFECTS: lets the user update customer database
    public void updateCustomers() {
        System.out.print("What is the new current year?");
        System.out.print("Please enter: ");
        int year = input.nextInt();
        cusData.annualUpdate(new Date(year));
        System.out.print("Update Complete!");
    }

    //EFFECTS: lets the user sort his Vip customers
    public void sortVipCustomers() {
        System.out.print("Do you want to sort your current vip customers?");
        System.out.print("Please enter: ");
        boolean sort = input.nextBoolean();
        if (sort) {
            System.out.print(cusData.sortVip());
        } else {
            System.out.print("You selected not to sort");
        }
    }
}
