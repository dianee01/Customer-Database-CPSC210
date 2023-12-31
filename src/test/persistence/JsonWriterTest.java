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
public class JsonWriterTest extends JsonTest{
    @Test
    void testWriterInvalidFile() {
        try {
            CustomerDatabase cd = new CustomerDatabase(new ArrayList<Customer>());
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyData() {
        try {
            CustomerDatabase cd = new CustomerDatabase(new ArrayList<Customer>());
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCustomerDatabase.json");
            writer.open();
            writer.writeCD(cd);
            writer.close();

            JsonCustomerDatabaseReader reader =
                    new JsonCustomerDatabaseReader("./data/testWriterEmptyCustomerDatabase.json");
            cd = reader.read();
            assertEquals(0, cd.totalCustomerSize());
            assertEquals(0, cd.totalRegularCustomer());
            assertEquals(0, cd.totalVipCustomer());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralCD() {
        try {
            CustomerDatabase cd = new CustomerDatabase(new ArrayList<Customer>());
            //customer one
            ArrayList<Item> purchase1 = new ArrayList<>();
            Date d1 = new Date(2020);
            purchase1.add(new Item("Chair",5.0, d1));
            //customer two
            ArrayList<Item> purchase2 = new ArrayList<>();
            Date d2 = new Date(2021);
            purchase2.add(new Item("Desk",1000.0, d2));
            purchase2.add(new Item("Mirror",2000.0, d2));
            Customer customerOne = new Customer("Diane", purchase1);
            Customer customerTwo = new Customer("Eva", purchase2);
            customerTwo.setVip(true);
            cd.addCustomer(customerOne);
            cd.addCustomer(customerTwo);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCustomerDatabase.json");
            writer.open();
            writer.writeCD(cd);
            writer.close();

            JsonCustomerDatabaseReader reader =
                    new JsonCustomerDatabaseReader("./data/testWriterGeneralCustomerDatabase.json");
            cd = reader.read();
            testHelperForWriterGeneralCD(cd, d1, d2);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    void testHelperForWriterGeneralCD(CustomerDatabase cd, Date d1, Date d2) {
        //all customers
        List<Customer> allCustomers = cd.getCustomers();
        assertEquals(2, allCustomers.size());
        //customer one
        ArrayList<Item> purchase1 = new ArrayList<>();
        purchase1.add(new Item("Chair",5.0, d1));
        //customer two
        ArrayList<Item> purchase2 = new ArrayList<>();
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
    }

    @Test
    void testWriterGeneralSales() {
        try {
            //all purchases
            ArrayList<Item> allSoldItems = new ArrayList<>();
            Sales s = new Sales(allSoldItems);
            //purchase one
            allSoldItems.add(new Item("Chair",5.0, new Date(2020)));
            //purchase two
            allSoldItems.add(new Item("Desk",1000.0, new Date(2021)));
            //check for purchases
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSales.json");
            writer.open();
            writer.writeSales(s);
            writer.close();

            JsonSalesReader reader =
                    new JsonSalesReader("./data/testWriterGeneralSales.json");
            s = reader.read();
            testHelperForWriterGeneralSales(s);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    void testHelperForWriterGeneralSales(Sales s) {
        //all purchases
        ArrayList<Item> allSoldItems = new ArrayList<>();
        //purchase one
        allSoldItems.add(new Item("Chair",5.0, new Date(2020)));
        //purchase two
        allSoldItems.add(new Item("Desk",1000.0, new Date(2021)));
        //check for purchases
        checkSales(allSoldItems, 1005.0, s);
    }
}
