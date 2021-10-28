package model;

import java.util.ArrayList;

//Creates a customer that has a name, a list of purchases, and a determination of whether the customer is a vip or not
public class Customer {
    private String name;
    private ArrayList<Item> purchases;
    private boolean vip;

    //REQUIRES: name and purchases not to be null
    //MODIFIES: this
    //EFFECTS: set this.name to name and this.purchases to purchases
    public Customer(String name, ArrayList<Item> purchases) {
        this.name = name;
        this.purchases = purchases;
        this.vip = false;
    }

    //Getters
    public String getName() {
        return name;
    }

    public ArrayList<Item> getPurchases() {
        return purchases;
    }

    public boolean isVip() {
        return vip;
    }

    //Setters

    //MODIFIES: this
    //EFFECTS: set this.name to name
    public void setName(String name) {
        this.name = name;
    }

    //MODIFIES: this
    //EFFECTS: set this.vip to vip
    public void setVip(boolean vip) {
        this.vip = vip;
    }

    //Methods

    //EFFECTS: returns the size of the amount of purchases
    public int purchaseCount() {
        return purchases.size();
    }

    //EFFECTS: sum up the dollar value of all the customer's purchases
    public double purchaseAmount() {
        double purchaseTotalValue = 0;
        for (Item item : purchases) {
            purchaseTotalValue += item.getPrice();
        }
        return purchaseTotalValue;
    }

    //REQUIRES: Item i and Sales s not to be null
    //MODIFIES: this, add another item to total sales
    //EFFECTS: add a purchased item to the specific customer's purchase history, and add it to total sales
    public void addPurchase(Item i, Sales s) {
        purchases.add(i);
        s.addItemSold(i);
    }

    //REQUIRES: Item i not to be null, and sales.size() > 0
    //MODIFIES: this, add another item to total sales
    //EFFECTS: remove a purchased item to the specific customer's purchase history, and remove it from total sales
    public void deletePurchase(Item i, Sales s) {
        if (s.itemCount() > 0) {
            s.deleteItemFromSold(i);
            purchases.remove(i);
        }
    }
}
