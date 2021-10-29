package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//This class references code from this JsonSerializationDemo
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

public class JsonSalesReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonSalesReader reader = new JsonSalesReader("./data/noSuchFile.json");
        try {
            Sales s = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptySales() {
        JsonSalesReader reader = new JsonSalesReader("./data/testReaderEmptySales.json");
        try {
            Sales s = reader.read();
            assertEquals(0, s.getTotalSales());
            assertEquals(0, s.getTotalSales());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSales() {
        JsonSalesReader reader = new JsonSalesReader("./data/testReaderGeneralSales.json");
        try {
            Sales s = reader.read();

            //all purchases
            ArrayList<Item> allSoldItems = new ArrayList<>();
            assertEquals(2, s.getSoldItems().size());
            //purchase one
            allSoldItems.add(new Item("Chair",5.0, new Date(2020)));
            //purchase two
            allSoldItems.add(new Item("Desk",1000.0, new Date(2021)));
            //check for purchases
            checkSales(allSoldItems, 1005.0, s);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
