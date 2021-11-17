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

public class UpdateTool extends Tool {
    private DefaultTableModel tableModel;
    private CustomerDatabase cd;

    public UpdateTool(CustomerDatabaseGUI editor, JComponent parent,
                      DefaultTableModel tableModel, CustomerDatabase cd) {
        super(editor, parent);
        this.tableModel = tableModel;
        this.cd = cd;
    }

    //MODIFIES: this
    //EFFECTS: constructs a load button which is then added to the JComponent (parent)
    //         which is passed in as a parameter
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Update Customers");
        addToParent(parent);
    }

    @Override
    public void buttonClicked(JComponent parent) {
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String updateDate = JOptionPane.showInputDialog(parent,
                        "What is the current year?", null);
                cd.update(new Date(Integer.parseInt(updateDate)));
                tableModel.setRowCount(0);
                ArrayList<Customer> customers = cd.getCustomers();
                for (int i = 0; i < cd.totalCustomerSize(); i++) {
                    tableModel.insertRow(0,
                            new Object[] {customers.get(i).getName(),
                                    Boolean.toString(customers.get(i).isVip()),
                                    Double.toString(customers.get(i).purchaseAmount()),
                                    Integer.toString(customers.get(i).purchaseCount())});
                }
                JOptionPane.showMessageDialog(null,
                        "Successfully updated",
                        "Update",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
