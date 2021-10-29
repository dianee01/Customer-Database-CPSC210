package persistence;

import model.Customer;
import model.CustomerDatabase;
import model.Date;
import model.Item;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//This class references code from this JsonSerializationDemo
//Link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonCustomerDatabaseReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonCustomerDatabaseReader reader = new JsonCustomerDatabaseReader("./data/noSuchFile.json");
        try {
            CustomerDatabase cr = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyCustomerDatabase() {
        JsonCustomerDatabaseReader reader = new
                JsonCustomerDatabaseReader("./data/testReaderEmptyCustomerDatabase.json");
        try {
            CustomerDatabase cd = reader.read();
            assertEquals(0, cd.totalCustomerSize());
            assertEquals(0, cd.totalRegularCustomer());
            assertEquals(0, cd.totalVipCustomer());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralCustomerDatabase() {
        JsonCustomerDatabaseReader reader = new
                JsonCustomerDatabaseReader("./data/testReaderGeneralCustomerDatabase.json");
        try {
            CustomerDatabase cd = reader.read();

            //all customers
            List<Customer> allCustomers = cd.getCustomers();
            assertEquals(2, allCustomers.size());
            //customer one
            ArrayList<Item> purchase1 = new ArrayList<>();
            Date d1 = new Date(2020);
            purchase1.add(new Item("Chair",5.0, d1));
            //customer two
            ArrayList<Item> purchase2 = new ArrayList<>();
            Date d2 = new Date(2021);
            purchase2.add(new Item("Desk",1000.0, d2));
            purchase2.add(new Item("Mirror",2000.0, d2));
            //check for all customers
            checkCustomer("Diane", purchase1, false, allCustomers.get(0));
            checkCustomer("Eva", purchase2, true, allCustomers.get(1));

            //regular customers
            List<Customer> regularCustomers = cd.getRegularCustomers();
            assertEquals(1, regularCustomers.size());
            checkCustomer("Diane", purchase1, false, regularCustomers.get(0));

            //vip customers
            List<Customer> vipCustomers = cd.getVipCustomers();
            assertEquals(1, vipCustomers.size());
            checkCustomer("Eva", purchase2, true, vipCustomers.get(0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
