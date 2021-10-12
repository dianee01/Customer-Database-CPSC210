package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sun.jvm.hotspot.utilities.Assert;

import java.util.ArrayList;

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
}
