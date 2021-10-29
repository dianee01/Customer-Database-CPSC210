package ui;

import model.*;
import persistence.JsonSalesReader;

import java.util.ArrayList;
import java.util.Scanner;

//This is the interface that allows the user to modify or get data of its sales history and overview
public class ManageSales {

    private Scanner input;
    private Sales mySales;
    private CustomerDatabase cusData;

    //EFFECTS: run ManageSales
    public ManageSales(CustomerDatabase cusData, Sales mySales) {
        this.cusData = cusData;
        this.mySales = mySales;
    }


    //MODIFIES: this
    //EFFECTS: process user input
    public void runManageSales(JsonSalesReader jsonSalesReader) {
        boolean keepGoing = true;
        String command = null;

        mySales = new Sales(new ArrayList<Item>());
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

    //EFFECTS: displays main menu
    public void displayMenuSales() {
        System.out.println("\nSelect from:");
        System.out.println("\ts -> view all sold items");
        System.out.println("\tr -> total revenue");
        System.out.println("\ti -> get total number of items sold");
        System.out.println("\tiy -> get total number of items sold in a particular year");
        System.out.println("\try -> get total revenue in a particular year");
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: process user command
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

    //EFFECTS: view total sold items in a list
    public void viewSoldItems() {
        for (Item item : mySales.getSoldItems()) {
            System.out.println("Item" + item.getItemName());
            System.out.println("Price" + item.getPrice());
            System.out.println("Purchase Date" + item.getPurchaseDate().getYear());
        }
    }

    //EFFECTS: view total revenue created by the business
    public void viewTotalRevenue() {
        System.out.print(mySales.getTotalSales());
    }

    //EFFECTS: view the total number of items sold
    public void numberItemSold() {
        System.out.print(mySales.itemCount());
    }

    //EFFECTS: view the total number of items sold in a particular year
    public void numberItemSoldInYear() {
        System.out.print("Which year do you want to see?");
        System.out.print("Please enter: ");
        int year = input.nextInt();

        if (year >= 0) {
            System.out.print(mySales.itemCountPerYear(new Date(year)));
        } else {
            System.out.println("Year cannot be smaller or equal to 0. Please re-enter.");
        }
    }

    //EFFECTS: view the total revenue created in a particular year
    public void totalRevenueInYear() {
        System.out.print("Which year do you want to see?");
        System.out.print("Please enter: ");
        int year = input.nextInt();

        if (year >= 0) {
            System.out.print(mySales.totalSalesPerYear(new Date(year)));
        } else {
            System.out.println("Year cannot be smaller or equal to 0.");
        }
    }
}
