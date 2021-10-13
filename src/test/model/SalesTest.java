package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SalesTest {
    private Date d2019;
    private Date d2020;
    private Item chair;
    private Item desk;
    private Item bed;
    private ArrayList<Item> home;
    private Sales s;

    @BeforeEach
    void setup() {
        d2019 = new Date(2019);
        d2020 = new Date(2020);
        chair = new Item("chair", 500.0, d2019);
        desk = new Item("desk", 450.5, d2019);
        bed = new Item("bed", 1000.0, d2020);
        home = new ArrayList<>();
        home.add(chair);
        home.add(desk);
        home.add(bed);
        s = new Sales(home);
    }

    @Test
    void testGetSoldItems() {
        ArrayList<Item> testHome = new ArrayList<>(home);
        assertEquals(testHome, s.getSoldItems());
    }

    @Test
    void testGetTotalSales() {
        assertEquals(500.0 + 450.5 + 1000.0, s.getTotalSales());
    }

    @Test
    void testItemCount() {
        assertEquals(3, s.itemCount());
    }

    @Test
    void testItemCountPerYear() {
        Date d1 = new Date(1);
        Date d3000 = new Date(3000);
        assertEquals(2, s.itemCountPerYear(d2019));
        assertEquals(1, s.itemCountPerYear(d2020));
        assertEquals(0, s.itemCountPerYear(d1));
        assertEquals(0, s.itemCountPerYear(d3000));
    }

    @Test
    void testTotalSalesPerYear() {
        Date d1 = new Date(1);
        Date d3000 = new Date(3000);
        assertEquals(950.5, s.totalSalesPerYear(d2019));
        assertEquals(1000.0, s.totalSalesPerYear(d2020));
        assertEquals(0, s.totalSalesPerYear(d1));
        assertEquals(0, s.totalSalesPerYear(d3000));
    }

    @Test
    void testAddItemSold() {
        Item sofa = new Item("sofa", 700.4, d2020);
        s.addItemSold(sofa);
        ArrayList<Item> testHome = new ArrayList<>(home);
        assertEquals(testHome, s.getSoldItems());
        assertEquals(500.0 + 450.5 + 1000.0 + 700.4, s.getTotalSales());
    }

    @Test
    void testDeleteItemFromSold() {
        s.deleteItemFromSold(bed);
        ArrayList<Item> testHome = new ArrayList<>(home);
        //new list with only one item left
        ArrayList<Item> o = new ArrayList<>();
        o.add(desk);
        Sales onlyOneItem = new Sales(o);
        onlyOneItem.deleteItemFromSold(desk);
        //new list with no item, so nothing happens if you want to delete a non-existent item
        ArrayList<Item> n = new ArrayList<>();
        Sales noItems = new Sales(n);
        noItems.deleteItemFromSold(desk);

        assertEquals(testHome, s.getSoldItems());
        assertEquals(500.0 + 450.5, s.getTotalSales());
        assertEquals(o, onlyOneItem.getSoldItems());
        assertEquals(0, onlyOneItem.getTotalSales());
        assertEquals(n, noItems.getSoldItems());
        assertEquals(0, noItems.getTotalSales());
    }

    @Test
    void testReturnItem() {
        Customer oneItem = new Customer("oneItem", new ArrayList<Item>(Arrays.asList(chair)));
        Customer multipleItems = new Customer("multipleItems", new ArrayList<Item>(Arrays.asList(desk, bed)));
        CustomerDatabase cData = new CustomerDatabase(new ArrayList<Customer>(Arrays.asList(oneItem, multipleItems)));
        s.returnItem(cData, multipleItems, desk, s);
        s.returnItem(cData, oneItem, chair, s);

        assertEquals(new ArrayList<Item>(Arrays.asList(bed)), s.getSoldItems());
        assertEquals(new ArrayList<Customer>(Arrays.asList(multipleItems)), cData.getCustomers());
    }
}
