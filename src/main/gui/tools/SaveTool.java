package gui.tools;

import gui.CustomerDatabaseGUI;
import model.Customer;
import model.CustomerDatabase;
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

//saves data to file
public class SaveTool extends Tool {
    private JsonWriter jsonWriterCD;
    private CustomerDatabase cd;
    private DefaultTableModel tableModel;

    public SaveTool(CustomerDatabaseGUI editor, JComponent parent,
                    JsonWriter jsonWriterCD,DefaultTableModel tableModel, CustomerDatabase cd) {
        super(editor, parent);
        this.jsonWriterCD = jsonWriterCD;
        this.cd = cd;
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
                    System.out.println("Saved to " + CustomerDatabaseApp.JSON_STORE_CD);
                } catch (FileNotFoundException e) {
                    System.out.println("Unable to write to file: " + CustomerDatabaseApp.JSON_STORE_CD);
                }
                JOptionPane.showMessageDialog(null,
                        "Successfully saved",
                        "Save",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }
}
