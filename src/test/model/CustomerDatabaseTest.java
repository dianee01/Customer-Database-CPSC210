package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDatabaseTest {
    Customer one;
    Customer two;
    Customer three;
    private Date d2019;
    private Date d2020;
    private Item chair;
    private Item desk;
    private Item bed;
    private ArrayList<Item> customerOne;
    private ArrayList<Item> customerTwo;
    private ArrayList<Item> customerThree;
    private ArrayList<Customer> customerList;
    private ArrayList<Customer> testCustomer;
    private ArrayList<Customer> testRegularCustomer;
    private ArrayList<Customer> testVipCustomer;
    private CustomerDatabase cData;

    @BeforeEach
    void setup() {
        d2019 = new Date(2019);
        d2020 = new Date(2020);
        chair = new Item("chair", 450.5, d2020);
        desk = new Item("desk", 1000.0, d2020);
        bed = new Item("bed", 0.1, d2020);
        customerOne = new ArrayList<>(Arrays.asList(chair));
        customerTwo = new ArrayList<>(Arrays.asList(desk));
        customerThree = new ArrayList<>(Arrays.asList(bed));
        one = new Customer("One", customerOne);
        two = new Customer("Two", customerTwo);
        three = new Customer("Three", customerThree);
        two.setVip(true);
        customerList = new ArrayList<>(Arrays.asList(one, two, three));
        cData = new CustomerDatabase(customerList);
        testCustomer = new ArrayList<>();
        testCustomer.addAll(customerList);
        testRegularCustomer = new ArrayList<>();
        testRegularCustomer.add(one);
        testRegularCustomer.add(three);
        testVipCustomer = new ArrayList<>();
        testVipCustomer.add(two);
    }

    @Test
    void testGetCustomers() {
        assertEquals(testCustomer, cData.getCustomers());
    }

    @Test
    void testGetRegularCustomers() {
        assertEquals(testRegularCustomer, cData.getRegularCustomers());
    }

    @Test
    void testGetVipCustomers() {
        assertEquals(testVipCustomer, cData.getVipCustomers());
    }

    @Test
    void testTotalCustomerSize() {
        assertEquals(testCustomer.size(), cData.totalCustomerSize());
    }

    @Test
    void testTotalRegularCustomer() {
        assertEquals(testRegularCustomer.size(), cData.totalRegularCustomer());
    }

    @Test
    void testTotalVipCustomer() {
        assertEquals(testVipCustomer.size(), cData.totalVipCustomer());
    }

    @Test
    void testAddCustomer() {
        Customer testRegular = new Customer("testRegular", customerOne);
        Customer testVip = new Customer("testVip", customerTwo);
        testVip.setVip(true);
        cData.addCustomer(testRegular);
        cData.addCustomer(testVip);
        testCustomer.add(testRegular);
        testCustomer.add(testVip);
        testRegularCustomer.add(testRegular);
        testVipCustomer.add(testVip);

        assertEquals(testCustomer, cData.getCustomers());
        assertEquals(testRegularCustomer, cData.getRegularCustomers());
        assertEquals(testVipCustomer, cData.getVipCustomers());
    }

    @Test
    void testRemoveRegularCustomerPart1() {
        Customer noItem = new Customer("noItem", new ArrayList<>());
        cData.addCustomer(noItem);
        cData.removeRegularCustomer(noItem);
        assertEquals(testCustomer, cData.getCustomers());
        assertEquals(testRegularCustomer, cData.getRegularCustomers());
    }

    @Test
    void testRemoveRegularCustomerPart2() {
        //customer with one item, does not fulfill if (so successfully added, but failed to remove)
        Customer oneItem = new Customer("oneItem", customerOne);
        cData.addCustomer(oneItem);
        cData.removeRegularCustomer(oneItem);
        testCustomer.add(oneItem);
        testRegularCustomer.add(oneItem);
        assertEquals(testCustomer, cData.getCustomers());
        assertEquals(testRegularCustomer, cData.getRegularCustomers());
    }

    @Test
    void testAnnualUpdate() {
        //curent date 2020
        Date d2021 = new Date(2021);
        Item sofa = new Item("sofa", 1001.0, d2019);
        customerOne.add(sofa);
        one.setVip(true);
        cData.getRegularCustomers().remove(one);
        cData.getVipCustomers().add(one);
        //now 2021
        cData.annualUpdate(d2021);
        assertEquals(testCustomer, cData.getCustomers());
        assertTrue(cData.getRegularCustomers().contains(one) && cData.getRegularCustomers().contains(three));
        assertTrue(cData.getVipCustomers().contains(two));
    }

    @Test
    void testSortVip() {
        Item sofa = new Item("sofa", 1001.0, d2019);
        customerOne.add(sofa);
        one.setVip(true);
        cData.getRegularCustomers().remove(one);
        cData.getVipCustomers().add(one);
        ArrayList<Customer> sortedVip = new ArrayList<>();
        sortedVip.add(one);
        sortedVip.add(two);
        assertEquals(sortedVip, cData.sortVip());
    }
}
