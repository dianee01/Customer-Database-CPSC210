package gui.tools;

import gui.CustomerDatabaseGUI;
import model.Customer;
import model.CustomerDatabase;
import model.Date;
import model.Item;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class CustomerTool extends Tool {
    private DefaultTableModel tableModel;
    private CustomerDatabase cd;

    public CustomerTool(CustomerDatabaseGUI editor, JComponent parent,
                        DefaultTableModel tableModel, CustomerDatabase cd) {
        super(editor, parent);
        this.tableModel = tableModel;
        this.cd = cd;
    }

    //MODIFIES: this
    //EFFECTS: constructs an add customer button which is then added to the JComponent (parent)
    //         which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Add Customer");
        addToParent(parent);
    }

    @Override
    public void buttonClicked(JComponent parent) {
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String name = JOptionPane.showInputDialog(parent,
                        "What is the new customer's name?", null);
                String numberOfPurchase = JOptionPane.showInputDialog(parent,
                        "How many items did this customer purchase?", null);
                ArrayList<Item> purchases = new ArrayList<Item>();
                for (int i = 0; i < Integer.parseInt(numberOfPurchase); i++) {
                    String purchaseName = JOptionPane.showInputDialog(parent,
                            "What is this item's name?", null);
                    String purchasePrice = JOptionPane.showInputDialog(parent,
                            "What is this item's price?", null);
                    String purchaseDate = JOptionPane.showInputDialog(parent,
                            "What year is this item purchased?", null);
                    Item purchase = new Item(purchaseName, Double.parseDouble(purchasePrice),
                            new Date(Integer.parseInt(purchaseDate)));
                    purchases.add(purchase);
                }
                Customer newCustomer = new Customer(name, purchases);
                addRowAndCustomer(newCustomer);
            }
        });
    }

    public void addRowAndCustomer(Customer newCustomer) {
        cd.addCustomer(newCustomer);
        tableModel.insertRow(0,
                new Object[] {newCustomer.getName(), Boolean.toString(newCustomer.isVip()),
                        Double.toString(newCustomer.purchaseAmount()), Integer.toString(newCustomer.purchaseCount())});
    }
}
