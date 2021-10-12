package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Date d;
    private Item chair;

    @BeforeEach
    void setup() {
        d = new Date(2019);
        chair = new Item("chair", 500.0, d);
    }

    @Test
    void testGetItemName() {
        assertEquals("chair", chair.getItemName());
    }

    @Test
    void testGetPrice() {
        assertEquals(500, chair.getPrice());
    }

    @Test
    void testGetPurchaseDate() {
        assertEquals(d, chair.getPurchaseDate());
    }

    @Test
    void testSetItemName() {
        chair.setItemName("BlackChair");
        assertEquals("BlackChair", chair.getItemName());
    }

    @Test
    void testSetPrice() {
        chair.setPrice(500.5);
        assertEquals(500.5, chair.getPrice());
    }

    @Test
    void testSetPurchaseDate() {
        d.setUpdateTrue();
        d.setYear(2020);
        chair.setPurchaseDate(d);
        assertEquals(d, chair.getPurchaseDate());
    }
}
