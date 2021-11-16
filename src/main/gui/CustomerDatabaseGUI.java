package gui;

import gui.tools.*;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//Customer Database GUI main window frame
public class CustomerDatabaseGUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    private CustomerDatabase customerDatabase;
    DefaultTableModel tableModel;
    JTable table;

    private List<Tool> tools;
    private Tool activeTool;

    public static void main(String[] args) {
        new CustomerDatabaseGUI();
    }

    public CustomerDatabaseGUI() {
        super("Customer Database");
        customerDatabase = new CustomerDatabase(new ArrayList<Customer>());
        initializeFields();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool to null, and instantiates tools with ArrayList
    private void initializeFields() {
        activeTool = null;
        tools = new ArrayList<Tool>();
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
    }

    //MODIFIES: this
    //EFFECTS: draws the JFrame window where this CustomerDatabase will operate
    private void initializeGraphics() {
        setTitle("CPSC 210: Customer Database");
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        createTable();
        createTools();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: a helper method which declares and instantiates all tools
    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.EAST);

        CustomerTool customerTool = new CustomerTool(this, toolArea, tableModel);
        tools.add(customerTool);

        UpdateTool updateTool = new UpdateTool(this, toolArea);
        tools.add(updateTool);

        LoadTool loadTool = new LoadTool(this, toolArea);
        tools.add(loadTool);

        SaveTool saveTool = new SaveTool(this, toolArea);
        tools.add(saveTool);

        setActiveTool(customerTool);
    }

    //MODIFIES: this
    //EFFECTS: sets the given tool as the activeTool
    public void setActiveTool(Tool tool) {
        if (activeTool != null) {
            activeTool.deactivate();
        }
        tool.activate();
        activeTool = tool;
    }

    private void createTable() {
        tableModel.addColumn("Name");
        tableModel.addColumn("VIP Status");
        tableModel.addColumn("Total purchases");
        tableModel.addColumn("Number of items purchased");
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);

        scrollPane.setSize(new Dimension(0, 0));
        add(scrollPane);
    }
}
