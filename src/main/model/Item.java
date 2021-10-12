package model;

public class Item {
    private String itemName;
    private double price;
    private Date purchaseDate;

    //REQUIRES: price to be larger than 0
    //EFFECTS: set itemName to item, price to p, and purchaseDate to d
    public Item(String item, double p, Date d) {
        this.itemName = item;
        this.price = p;
        this.purchaseDate = d;
    }

    public String getItemName() {
        return itemName;
    }

    public double getPrice() {
        return price;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    //MODIFIES: this
    //EFFECTS: set itemName to i
    public void setItemName(String i) {
        this.itemName = i;
    }

    //MODIFIES: this
    //EFFECTS: set price to p
    public void setPrice(double p) {
        this.price = p;
    }

    //MODIFIES: this
    //EFFECTS: set purchaseDate to d
    public void setPurchaseDate(Date d) {
        this.purchaseDate = d;
    }

}
