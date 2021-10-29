package persistence;

import model.Customer;
import model.Item;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

//This class references code from this JsonSerializationDemo
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkCustomer(String name, ArrayList<Item> purchases, boolean vip, Customer c) {
        assertEquals(name, c.getName());
        assertEquals(purchases, c.getPurchases());
        assertEquals(vip, c.isVip());
    }
}
