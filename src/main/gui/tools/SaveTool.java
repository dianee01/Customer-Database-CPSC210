package gui.tools;

import gui.CustomerDatabaseGUI;
import model.Customer;
import model.CustomerDatabase;
import model.Sales;
import persistence.JsonWriter;
import ui.CustomerDatabaseApp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

//saves customer database and sales data to file
public class SaveTool extends Tool {
    private JsonWriter jsonWriterCD;
    private JsonWriter jsonWriterSales;
    private CustomerDatabase cd;
    private Sales sales;
    private DefaultTableModel tableModel;

    public SaveTool(CustomerDatabaseGUI editor, JComponent parent,
                    JsonWriter jsonWriterCD, JsonWriter jsonWriterSales,
                    DefaultTableModel tableModel, CustomerDatabase cd, Sales sales) {
        super(editor, parent);
        this.jsonWriterCD = jsonWriterCD;
        this.jsonWriterSales = jsonWriterSales;
        this.cd = cd;
        this.sales = sales;
        this.tableModel = tableModel;
    }

    //MODIFIES: this
    //EFFECTS: constructs a load button which is then added to the JComponent (parent)
    //         which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Save");
        addToParent(parent);
    }

    @Override
    public void buttonClicked(JComponent parent) {
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    jsonWriterCD.open();
                    jsonWriterCD.writeCD(cd);
                    jsonWriterCD.close();
                    jsonWriterSales.open();
                    jsonWriterSales.writeSales(sales);
                    jsonWriterSales.close();
                    System.out.println("Saved to " + CustomerDatabaseApp.JSON_STORE_CD);
                    System.out.println("Saved to " + CustomerDatabaseApp.JSON_STORE_S);
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to write to file: " + CustomerDatabaseApp.JSON_STORE_CD);
                    System.out.println("Unable to write to file: " + CustomerDatabaseApp.JSON_STORE_S);
                }
                JOptionPane.showMessageDialog(null,
                        "Successfully saved",
                        "Save",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
