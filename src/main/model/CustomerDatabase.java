package model;

import java.util.ArrayList;

public class CustomerDatabase {
    private ArrayList<Customer> customers;
    private ArrayList<Customer> regularCustomers;
    private ArrayList<Customer> vipCustomers;

    //REQUIRES: regularCustomers and vipCustomers not to be null
    //MODIFIES: this
    //EFFECTS: set this.regularCustomers to regularCustomers, this.vipCustomers to vipCustomers,
    //         and this.customers to be the concatenation of the two
    public CustomerDatabase(ArrayList<Customer> customers) {
        this.customers = customers;

        //categorizing customers into regular and vip and create new lists to store them
        regularCustomers = new ArrayList<>();
        vipCustomers = new ArrayList<>();
        for (Customer c : customers) {
            if (c.isVip()) {
                vipCustomers.add(c);
            } else {
                regularCustomers.add(c);
            }
        }
    }

    //Getters

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Customer> getRegularCustomers() {
        return regularCustomers;
    }

    public ArrayList<Customer> getVipCustomers() {
        return vipCustomers;
    }

    //Methods

    //EFFECTS: returns total number of customers
    public int totalCustomerSize() {
        return customers.size();
    }

    //EFFECTS: returns total number of regular customers
    public int totalRegularCustomer() {
        return regularCustomers.size();
    }

    //EFFECTS: returns total number of vip customers
    public int totalVipCustomer() {
        return vipCustomers.size();
    }

    //EFFECTS: add a new customer
    public void addCustomer(Customer c) {
        customers.add(c);
        if (c.isVip()) {
            vipCustomers.add(c);
        } else {
            regularCustomers.add(c);
        }
    }

    //REQUIRES: c not to be null
    //MODIFIES: customers and regularCustomers
    //EFFECTS: if customer is a regular customer and no longer purchased anything from the store
    //         then delete them from the customer tracker
    public void removeRegularCustomer(Customer c) {
        if (c.purchaseCount() == 0) {
            regularCustomers.remove(c);
            customers.remove(c);
        }
    }

    //REQUIRES: date d not to be null
    //MODIFIES: this
    //EFFECTS: check for vip and regular customers' purchased amount in the last year, update accordingly
    public void annualUpdate(Date d) {
        for (Customer c : customers) {
            double totalPurchasesLastYear = 0;
            for (Item i : c.getPurchases()) {
                if (i.getPurchaseDate().getYear() == (d.getYear() - 1)) {
                    totalPurchasesLastYear += i.getPrice();
                }
            }

            if (totalPurchasesLastYear >= 1000) {
                if (!c.isVip()) {
                    c.setVip(true);
                    vipCustomers.add(c);
                    regularCustomers.remove(c);
                }
            } else {
                if (c.isVip()) {
                    c.setVip(false);
                    vipCustomers.remove(c);
                    regularCustomers.add(c);
                }
            }
        }
    }

    //EFFECTS: returns a new list with sorted vip customers
    public ArrayList<Customer> sortVip() {
        ArrayList<Customer> vipSorted = new ArrayList<>();
        ArrayList<Customer> vip = new ArrayList<>(vipCustomers);

        for (int i = 0; i < totalVipCustomer(); i++) {
            Customer vipGreatest = vip.get(0);
            double greatestAmount = 0;
            for (Customer v : vip) {
                if (v.purchaseAmount() > greatestAmount) {
                    greatestAmount = v.purchaseAmount();
                    vipGreatest = v;
                }
            }
            vipSorted.add(vipGreatest);
            vip.remove(vipGreatest);
        }

        return vipSorted;
    }

}
