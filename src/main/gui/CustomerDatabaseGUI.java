package gui;

import gui.tools.*;
import model.*;
import model.Event;
import persistence.JsonCustomerDatabaseReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//Customer Database GUI main window frame
public class CustomerDatabaseGUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    public static final String JSON_STORE_CD = "./data/CustomerDatabase.json";
    public static final String JSON_STORE_S = "./data/Sales.json";

    private CustomerDatabase customerDatabase;
    private Sales sales;

    private DefaultTableModel tableModel;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    private JsonWriter jsonWriterCD;
    private JsonWriter jsonWriterSales;
    private JsonCustomerDatabaseReader jsonCustomerDatabaseReader;

    private List<Tool> tools;
    private Tool activeTool;

    //EFFECTS: constructs a customer database gui
    public CustomerDatabaseGUI() {
        initializeFields();
        initializeGraphics();
        printLog();
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool to null, and instantiates tools with ArrayList
    private void initializeFields() {
        customerDatabase = new CustomerDatabase(new ArrayList<Customer>());
        sales = new Sales(new ArrayList<Item>());
        activeTool = null;
        tools = new ArrayList<Tool>();
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        sorter = new TableRowSorter<DefaultTableModel>(tableModel);
        jsonWriterCD = new JsonWriter(JSON_STORE_CD);
        jsonWriterSales = new JsonWriter(JSON_STORE_S);
        jsonCustomerDatabaseReader = new JsonCustomerDatabaseReader(JSON_STORE_CD);
    }

    //MODIFIES: this
    //EFFECTS: draws the JFrame window where this CustomerDatabase will operate
    private void initializeGraphics() {
        setTitle("CPSC 210: Customer Database");
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());
        createTable();
        createSorter();
        createTools();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog();
                System.exit(0);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: a helper method which declares and instantiates all tools
    private void createTools() {
        JPanel toolArea = new JPanel();
        toolArea.setLayout(new GridLayout(0,1));
        toolArea.setSize(new Dimension(0, 0));
        add(toolArea, BorderLayout.EAST);

        CustomerTool customerTool = new CustomerTool(this, toolArea, tableModel, customerDatabase, sales);
        tools.add(customerTool);

        UpdateTool updateTool = new UpdateTool(this, toolArea, tableModel, customerDatabase);
        tools.add(updateTool);

        LoadToolCD loadToolCD = new LoadToolCD(this, toolArea, jsonCustomerDatabaseReader,
                tableModel, customerDatabase);
        tools.add(loadToolCD);

        SaveTool saveTool = new SaveTool(this, toolArea, jsonWriterCD, jsonWriterSales,
                tableModel, customerDatabase, sales);
        tools.add(saveTool);

        manageSales(toolArea);

        setActiveTool(customerTool);
    }

    //MODIFIES: this
    //EFFECTS: helper function to createTools that switch to sales panel
    public void manageSales(JPanel toolArea) {
        JButton salesButton = new JButton("Manage Sales");
        toolArea.add(salesButton);
        salesButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SalesGUI salesGUI = new SalesGUI();
                setVisible(false);
                salesGUI.setVisible(true);
            }
        });
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

    //MODIFIES: this
    //EFFECTS: helper method that creates a table that displays the multiple Xs
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

    //MODIFIES: this
    //EFFECTS: creates a filter for the multiple Xs; filter according vip and regular customers
    private void createSorter() {
        JPanel filterArea = new JPanel(new BorderLayout());
        add(filterArea, BorderLayout.NORTH);
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"","false","true"});
        JButton button = new JButton("filter vip");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                RowFilter<DefaultTableModel, Object> rf  =
                        RowFilter.regexFilter(comboBox.getSelectedItem().toString(), 1);
                sorter.setRowFilter(rf);
            }
        });
        filterArea.add(comboBox, BorderLayout.CENTER);
        filterArea.add(button, BorderLayout.WEST);
        table.setRowSorter(sorter);
    }

    //EFFECTS: prints Event long when quit
    private void printLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }
}
