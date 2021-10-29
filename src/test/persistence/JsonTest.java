package persistence;

import model.Customer;
import model.Item;
import model.Sales;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//This class references code from this JsonSerializationDemo
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkCustomer(String name, ArrayList<Item> purchases, boolean vip, Customer c) {
        assertEquals(name, c.getName());
        for (int i = 0; i < purchases.size(); i++) {
            assertEquals(purchases.get(i).getItemName(), c.getPurchases().get(i).getItemName());
            assertEquals(purchases.get(i).getPrice(), c.getPurchases().get(i).getPrice());
            assertEquals(purchases.get(i).getPurchaseDate().getYear(),
                    c.getPurchases().get(i).getPurchaseDate().getYear());
        }
        assertEquals(vip, c.isVip());
    }
    protected void checkSales(ArrayList<Item> purchases, double totalSales, Sales s) {
        assertEquals(totalSales, s.getTotalSales());
        for (int i = 0; i < purchases.size(); i++) {
            assertEquals(purchases.get(i).getItemName(), s.getSoldItems().get(i).getItemName());
            assertEquals(purchases.get(i).getPrice(), s.getSoldItems().get(i).getPrice());
            assertEquals(purchases.get(i).getPurchaseDate().getYear(),
                    s.getSoldItems().get(i).getPurchaseDate().getYear());
        }
    }
}
