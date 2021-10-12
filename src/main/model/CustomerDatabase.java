package model;

import java.util.ArrayList;

public class CustomerDatabase {
    private ArrayList<Customer> customers;
    private ArrayList<RegularCustomer> regularCustomers;
    private ArrayList<VipCustomer> vipCustomers;

    //REQUIRES: regularCustomers and vipCustomers not to be null
    //MODIFIES: this
    //EFFECTS: set this.regularCustomers to regularCustomers, this.vipCustomers to vipCustomers,
    //         and this.customers to be the concatenation of the two
    public CustomerDatabase(ArrayList<RegularCustomer> regularCustomers, ArrayList<VipCustomer> vipCustomers) {
        this.regularCustomers = regularCustomers;
        this.vipCustomers = vipCustomers;

        //customers is an arraylist with both regular customers and vip customers
        this.customers = new ArrayList<>();
        this.customers.addAll(regularCustomers);
        this.customers.addAll(vipCustomers);
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<RegularCustomer> getRegularCustomers() {
        return regularCustomers;
    }

    public ArrayList<VipCustomer> getVipCustomers() {
        return vipCustomers;
    }

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
    public void addCustomer(RegularCustomer rc) {
        regularCustomers.add(rc);
        customers.add(rc);
    }

    //EFFECTS: add a new vip customer vp to vipCustomers and the customers list
    public void addCustomer(VipCustomer vc) {
        vipCustomers.add(vc);
        customers.add(vc);
    }

    //REQUIRES: c not to be null
    //MODIFIES: customers and regularCustomers
    //EFFECTS: if customer is a regular customer and no longer purchased anything from the store
    //         then delete them from the customer tracker
    public void removeCustomer(Customer c) {
        if (c instanceof RegularCustomer && c.purchaseCount() == 0) {
            regularCustomers.remove(c);
            customers.remove(c);
        }
    }

}
