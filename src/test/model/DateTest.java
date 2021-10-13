package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {
    private Date date;
    private Date dateSetTrueUpdate;
    private Date negativeDate;

    @BeforeEach
    void setup() {
        date = new Date(2020);
        dateSetTrueUpdate = new Date(2020);
        dateSetTrueUpdate.setUpdateTrue();
        negativeDate = new Date(-100);
    }

    @Test
    void testGetYear() {
        assertEquals(2020, date.getYear());
    }

    @Test
    void testGetUpdate() {
        assertFalse(date.getUpdate());
    }

    @Test
    void testSetUpdateTrue() {
        date.setUpdateTrue();
        assertTrue(date.getUpdate());
    }

    @Test
    void testSetYear() {
        //only when update is true, you can set the year, and all changed date will have update back to false
        date.setYear(2021);
        dateSetTrueUpdate.setYear(2021);
        assertEquals(2020, date.getYear());
        assertEquals(2021, dateSetTrueUpdate.getYear());
        assertFalse(date.getUpdate());
        assertFalse(dateSetTrueUpdate.getUpdate());
    }

}
