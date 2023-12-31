package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

//Sales is a list of sold items, and can allow the user to see how much sales they are generating with their business
public class Sales implements Writable {
    private ArrayList<Item> soldItems;
    private double totalSales;

    //MODIFIES: this
    //EFFECTS: set soldItems to items and set totalSales to the total of price of the items
    public Sales(ArrayList<Item> items) {
        this.soldItems = items;
        for (Item item : items) {
            this.totalSales += item.getPrice();
        }
    }

    //Getters

    public double getTotalSales() {
        return totalSales;
    }

    public ArrayList<Item> getSoldItems() {
        return soldItems;
    }

    //Methods

    //EFFECTS: return the number of sold items
    public int itemCount() {
        return soldItems.size();
    }

    //EFFECTS: return the number of item sold during a specific date d (year)
    public int itemCountPerYear(Date d) {
        int itemPerYear = 0;
        for (Item i : soldItems) {
            if (i.getPurchaseDate().getYear() == d.getYear()) {
                itemPerYear += 1;
            }
        }
        return itemPerYear;
    }

    //EFFECTS: return the total dollar amount of sales sold during a specific date d (year)
    public double totalSalesPerYear(Date d) {
        double salesPerYear = 0;
        for (Item i : soldItems) {
            if (i.getPurchaseDate().getYear() == d.getYear()) {
                salesPerYear += i.getPrice();
            }
        }
        return salesPerYear;
    }

    //REQUIRES: Item i is not null
    //MODIFIES: this
    //EFFECTS: add a new item i to soldItems
    public void addItemSold(Item i) {
        EventLog.getInstance().logEvent(new Event("Added " + i.getItemName()));
        soldItems.add(i);
        totalSales += i.getPrice();
    }

    //REQUIRES: i not to be null and size of soldItems to be larger than 0
    //MODIFIES: this
    //EFFECTS: delete a returned item
    public void deleteItemFromSold(Item i) {
        EventLog.getInstance().logEvent(new Event("Deleted " + i.getItemName()));
        if (soldItems.size() > 0) {
            soldItems.remove(i);
            totalSales -= i.getPrice();
        }
    }

    //REQUIRES: cd, c, i, s not to be null
    //MODIFIES: Customer c's purchases, and customer database if that is the only item the regular customer purchased
    //EFFECTS: entirely return an item
    public void returnItem(CustomerDatabase cd, Customer c, Item i, Sales s) {
        EventLog.getInstance().logEvent(new Event("Customer" + c.getName() + "Returned " + i.getItemName()));
        c.deletePurchase(i, s);
        cd.removeRegularCustomer(c);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("soldItems", itemsToJson());
        json.put("totalSales", totalSales);
        return json;
    }

    //EFFECTS: returns items sold as a JSON array
    private JSONArray itemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Item i : soldItems) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }
}
