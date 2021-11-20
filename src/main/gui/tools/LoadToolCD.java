package gui.tools;

import gui.CustomerDatabaseGUI;
import gui.SalesGUI;
import model.Customer;
import model.CustomerDatabase;
import model.Date;
import persistence.JsonCustomerDatabaseReader;
import ui.CustomerDatabaseApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

//loads customer database data from file
public class LoadToolCD extends Tool {
    private JsonCustomerDatabaseReader jsonCustomerDatabaseReader;
    private CustomerDatabase cd;
    private DefaultTableModel tableModel;

    public LoadToolCD(CustomerDatabaseGUI editor, JComponent parent,
                      JsonCustomerDatabaseReader jsonCustomerDatabaseReader,
                      DefaultTableModel tableModel, CustomerDatabase cd) {
        super(editor, parent);
        this.jsonCustomerDatabaseReader = jsonCustomerDatabaseReader;
        this.cd = cd;
        this.tableModel = tableModel;
    }

    //MODIFIES: this
    //EFFECTS: constructs a load button which is then added to the JComponent (parent)
    //         which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Load");
        addToParent(parent);
    }

    @Override
    public void buttonClicked(JComponent parent) {
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    cd = jsonCustomerDatabaseReader.read();
                    System.out.println("Loaded from " + CustomerDatabaseApp.JSON_STORE_CD);
                } catch (IOException e) {
                    System.out.println("Unable to read from file: " + CustomerDatabaseApp.JSON_STORE_CD);
                }
                tableModel.setRowCount(0);
                ArrayList<Customer> customers = cd.getCustomers();
                for (int i = 0; i < cd.totalCustomerSize(); i++) {
                    tableModel.insertRow(0,
                            new Object[] {customers.get(i).getName(),
                                    Boolean.toString(customers.get(i).isVip()),
                                    Double.toString(customers.get(i).purchaseAmount()),
                                    Integer.toString(customers.get(i).purchaseCount())});
                }
            }
        });
    }
}
