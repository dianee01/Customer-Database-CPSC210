package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {
    private Customer c;
    private Date d2019;
    private Date d2020;
    private Item chair;
    private Item desk;
    private Item bed;
    private ArrayList<Item> home;

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
        c = new Customer("Diane", home);
    }

    @Test
    void testGetName() {
        assertEquals("Diane", c.getName());
    }

    @Test
    void testGetPurchases() {
        ArrayList<Item> testHome = new ArrayList<>(home);
        assertEquals(testHome, c.getPurchases());
    }

    @Test
    void testIsVip() {
        assertFalse(c.isVip());
    }

    @Test
    void testSetName() {
        c.setName("Eva");
        assertEquals("Eva", c.getName());
    }

    @Test
    void testSetVip() {
        c.setVip(true);
        assertTrue(c.isVip());
    }

    @Test
    void testPurchaseCount() {
        assertEquals(3, c.purchaseCount());
    }

    @Test
    void testPurchaseAmount() {
        c.purchaseAmount();
        assertEquals(500 + 450.5 + 1000, c.purchaseAmount());
    }

    @Test
    void testAddPurchase() {

    }

    @Test
    void testDeletePurchase() {

    }
}