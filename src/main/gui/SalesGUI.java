package gui;

import gui.tools.*;
import model.Event;
import model.EventLog;
import model.Item;
import model.Sales;
import persistence.JsonSalesReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//Sales GUI main window frame
public class SalesGUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;
    public static final String JSON_STORE_S = "./data/Sales.json";

    private Sales sales;

    private DefaultTableModel tableModel;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    private JsonWriter jsonWriterSales;
    private JsonSalesReader jsonSalesReader;

    private List<Tool> tools;

    //EFFECTS: constructs a customer database gui
    public SalesGUI() {
        initializeFields();
        initializeGraphics();
    }

    // MODIFIES: this
    // EFFECTS:  sets activeTool to null, and instantiates tools with ArrayList
    private void initializeFields() {
        sales = new Sales(new ArrayList<Item>());
        tools = new ArrayList<Tool>();
        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);
        sorter = new TableRowSorter<DefaultTableModel>(tableModel);
        jsonWriterSales = new JsonWriter(JSON_STORE_S);
        jsonSalesReader = new JsonSalesReader(JSON_STORE_S);
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
        add(toolArea, BorderLayout.SOUTH);

        LoadToolSales loadTool = new LoadToolSales(this, toolArea, jsonSalesReader,
                tableModel, sales);
        tools.add(loadTool);

        JButton cdButton = new JButton("Manage Customers");
        toolArea.add(cdButton);
        cdButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerDatabaseGUI customerDatabaseGUI = new CustomerDatabaseGUI();
                setVisible(false);
                customerDatabaseGUI.setVisible(true);
            }
        });
    }

    //MODIFIES: this
    //EFFECTS: helper method that creates a table that displays the multiple Xs
    private void createTable() {
        tableModel.addColumn("Item Name");
        tableModel.addColumn("Price");
        tableModel.addColumn("Sold Year");
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
        String[] listOfItems = new String[]{};
        for (int i = 0; i < sales.itemCount(); i++) {
            listOfItems[i] = String.valueOf(sales.getSoldItems().get(i).getPurchaseDate().getYear());
        }
        JComboBox<String> comboBox = new JComboBox<>(listOfItems);
        JButton button = new JButton("filter year");
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
