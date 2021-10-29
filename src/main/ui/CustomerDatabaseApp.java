package ui;

import model.Customer;
import model.CustomerDatabase;
import model.Item;
import model.Sales;
import persistence.JsonCustomerDatabaseReader;
import persistence.JsonSalesReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//This class references code from this TellerApp
//Link: https://github.students.cs.ubc.ca/CPSC210/TellerApp
//This class references code from this JsonSerializationDemo
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//The main ui, which will be separated into the customer view or sales view
public class CustomerDatabaseApp {

    public static final String JSON_STORE_CD = "./data/CustomerDatabase.json";
    public static final String JSON_STORE_S = "./data/Sales.json";

    private Scanner input;
    private CustomerDatabase cusData;
    private Sales mySales;
    private ManageCustomer mc;
    private ManageSales ms;
    private JsonWriter jsonWriterCD;
    private JsonWriter jsonWriterSales;
    private JsonCustomerDatabaseReader jsonCustomerDatabaseReader;
    private JsonSalesReader jsonSalesReader;

    //EFFECTS: run the CustomerDatabase App
    public CustomerDatabaseApp()throws FileNotFoundException {
        jsonWriterCD = new JsonWriter(JSON_STORE_CD);
        jsonWriterSales = new JsonWriter(JSON_STORE_S);
        jsonCustomerDatabaseReader = new JsonCustomerDatabaseReader(JSON_STORE_CD);
        jsonSalesReader = new JsonSalesReader(JSON_STORE_S);
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
            mc = new ManageCustomer(cusData, mySales);
            ms = new ManageSales(cusData, mySales);

            displayMenuMain();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
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
        System.out.println("\tq -> quit");
    }

    //MODIFIES: this
    //EFFECTS: process user command
    public void processCommandMain(String command) {
        if (command.equals("c")) {
            mc.runManageCustomer(jsonWriterCD, jsonWriterSales, jsonCustomerDatabaseReader);
        } else if (command.equals("s")) {
            ms.runManageSales(jsonSalesReader);
        } else {
            System.out.println("Selection not valid!");
        }
    }
}
