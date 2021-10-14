package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class ManageSales {

    private Scanner input;
    private CustomerDatabase cusData;

    public ManageSales() {
        runManageSales();
    }

    public void runManageSales() {
        boolean keepGoing = true;
        String command = null;

        cusData = new CustomerDatabase(new ArrayList<Customer>());
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenuSales();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommandSales(command);
            }
        }
    }

    public void displayMenuSales() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> view all sold items");
        System.out.println("\tr -> total revenue");
        System.out.println("\ti -> get total number of items sold");
        System.out.println("\tiy -> get total number of items sold in a particular year");
        System.out.println("\try -> get total revenue in a particular year");
    }

    public void processCommandSales(String command) {
        if (command.equals("s")) {
            viewSoldItems();
        } else if (command.equals("r")) {
            viewTotalRevenue();
        } else if (command.equals("i")) {
            numberItemSold();
        } else if (command.equals("iy")) {
            numberItemSoldInYear();
        } else if (command.equals("ry")) {
            totalRevenueInYear();
        } else {
            System.out.println("Selection not valid!");
        }
    }

    public void viewSoldItems() {

    }

    public void viewTotalRevenue() {

    }

    public void numberItemSold() {

    }

    public void numberItemSoldInYear() {

    }

    public void totalRevenueInYear() {

    }
}
