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
//Represents a reader that reads sales from JSON data stored in file
public class JsonSalesReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonSalesReader(String source) {
        this.source = source;
    }

    //EFFECTS: reads Sales from file and returns it;
    //throws IOException if an error occurs reading data from file
    public Sales read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSales(jsonObject);
    }

    //EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    private Sales parseSales(JSONObject jsonObject) {
        ArrayList<Item> items = new ArrayList<>();
        JSONArray sold = jsonObject.getJSONArray("soldItems");

        for (Object json : sold) {
            JSONObject nextPurchase = (JSONObject) json;
            String name = nextPurchase.getString("itemName");
            double price = nextPurchase.getDouble("price");
            Date d = returnOneDate(nextPurchase);
            Item i = new Item(name, price, d);
            items.add(i);
        }

        Sales s = new Sales(items);
        return s;
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
