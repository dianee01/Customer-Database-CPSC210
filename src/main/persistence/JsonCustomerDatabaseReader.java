package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

//This class references code from this JsonSerializationDemo
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
//Represents a reader that reads customer database from JSON data stored in file
public class JsonCustomerDatabaseReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonCustomerDatabaseReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads CustomerDatabase from file and returns it;
    //throws IOException if an error occurs reading data from file
    public CustomerDatabase read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCustomerDatabase(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    //EFFECTS: parses CustomerDatabase from JSON object and returns it
    private CustomerDatabase parseCustomerDatabase(JSONObject jsonObject) {
        CustomerDatabase cd = new CustomerDatabase(new ArrayList<Customer>());
        JSONArray customers = jsonObject.getJSONArray("customers");

        for (Object json : customers) {
            JSONObject nextCustomer = (JSONObject) json;
            String name = nextCustomer.getString("name");
            ArrayList<Item> purchases = addPurchases(nextCustomer);
            Customer c = new Customer(name, purchases);
            Boolean vip = nextCustomer.getBoolean("vip");
            c.setVip(vip);
            cd.addCustomer(c);
        }

        return cd;
    }

    //MODIFIES: cd
    //EFFECTS: parse purchases of a single customer and adds them to the customers purchases list
    private ArrayList<Item> addPurchases(JSONObject nextCustomer) {
        JSONArray purchases = nextCustomer.getJSONArray("purchases");
        ArrayList<Item> purchaseOfCustomer = new ArrayList<>();
        for (Object json : purchases) {
            JSONObject nextPurchase = (JSONObject) json;
            String name = nextPurchase.getString("itemName");
            double price = nextPurchase.getDouble("price");
            Date d = returnOneDate(nextPurchase);
            Item i = new Item(name, price, d);
            purchaseOfCustomer.add(i);
        }
        return purchaseOfCustomer;
    }

    //MODIFIES: purchaseOfCustomer
    //EFFECTS: parse date of a single purchase and help add the purchase
    private Date returnOneDate(JSONObject nextPurchase) {
        JSONObject date = nextPurchase.getJSONObject("purchaseDate");
        int year = date.getInt("year");
        boolean update = date.getBoolean("update");
        Date newDate = new Date(year, update);
        return newDate;
    }

}
