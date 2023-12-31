package model;

import org.json.JSONObject;
import persistence.Writable;

//Creates an item that is sold to a customer that contains a name, a price, and a purchased date
public class Item implements Writable {
    private String itemName;
    private double price;
    private Date purchaseDate;

    //REQUIRES: price to be larger than 0
    //EFFECTS: set itemName to item, price to p, and purchaseDate to d
    public Item(String item, double p, Date d) {
        this.itemName = item;
        if (p > 0) {
            this.price = p;
        }
        this.purchaseDate = d;
    }

    //Getters
    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    //Setters

    //MODIFIES: this
    //EFFECTS: set itemName to i
    public void setItemName(String i) {
        EventLog.getInstance().logEvent(new Event("Set " + this.itemName +  "name to: " + i));
        this.itemName = i;
    }

    //MODIFIES: this
    //EFFECTS: set price to p
    public void setPrice(double p) {
        EventLog.getInstance().logEvent(new Event("Set" + this.itemName + "item price to: " + p));
        this.price = p;
    }

    //MODIFIES: this
    //EFFECTS: set purchaseDate to d
    public void setPurchaseDate(Date d) {
        EventLog.getInstance().logEvent(new Event("Set" + this.itemName
                + "item purchase year to: " + d.getYear()));
        this.purchaseDate = d;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("itemName", itemName);
        json.put("price", price);
        json.put("purchaseDate", purchaseDate.toJson());
        return json;
    }
}
