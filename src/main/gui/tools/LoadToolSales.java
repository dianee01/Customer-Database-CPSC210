package gui.tools;

import gui.CustomerDatabaseGUI;
import gui.SalesGUI;
import model.Customer;
import model.CustomerDatabase;
import model.Item;
import model.Sales;
import persistence.JsonCustomerDatabaseReader;
import persistence.JsonSalesReader;
import ui.CustomerDatabaseApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.ArrayList;

//load sales data from file
public class LoadToolSales extends Tool {
    private JsonSalesReader jsonSalesReader;
    private Sales sales;
    private DefaultTableModel tableModel;

    public LoadToolSales(SalesGUI editor, JComponent parent,
                         JsonSalesReader jsonSalesReader,
                         DefaultTableModel tableModel, Sales sales) {
        super(editor, parent);
        this.jsonSalesReader = jsonSalesReader;
        this.sales = sales;
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
                    sales = jsonSalesReader.read();
                    System.out.println("Loaded from " + CustomerDatabaseApp.JSON_STORE_S);
                } catch (IOException e) {
                    System.out.println("Unable to read from file: " + CustomerDatabaseApp.JSON_STORE_S);
                }
                tableModel.setRowCount(0);
                ArrayList<Item> purchases = sales.getSoldItems();
                for (int i = 0; i < sales.itemCount(); i++) {
                    tableModel.insertRow(0,
                            new Object[] {purchases.get(i).getItemName(),
                                    Double.toString(purchases.get(i).getPrice()),
                                    Integer.toString(purchases.get(i).getPurchaseDate().getYear())});
                }
            }
        });
    }
}
