package gui;

import model.*;
import model.Event;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

//main page frame window
public class LoginPageGUI extends JFrame {
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 600;

    public static void main(String[] args) {
        new LoginPageGUI();
    }

    //EFFECTS: constructs a new login page gui
    public LoginPageGUI() {
        setTitle("CPSC 210: Customer Database");
        setSize(WIDTH, HEIGHT);
        setLayout(new BorderLayout());

        JPanel mainArea = new JPanel(new BorderLayout());
        add(mainArea);
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("./data/loginPicture.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image scaled = myPicture.getScaledInstance(780, 420, Image.SCALE_SMOOTH);
        JLabel picLabel = new JLabel(new ImageIcon(scaled));
        mainArea.add(picLabel, BorderLayout.CENTER);

        switchToOther(mainArea);

        setVisible(true);
        windowClose();
    }

    //MODIFIES: this
    //EFFECTS: helper function that adds the button that switches to the other frame (CustomerDatabaseGUI)
    public void switchToOther(JPanel mainArea) {
        JPanel switchToOther = new JPanel();
        add(switchToOther, BorderLayout.SOUTH);

        JButton cdButton = new JButton("Manage Customers");
        switchToOther.add(cdButton);
        cdButton.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerDatabaseGUI customerDatabaseGUI = new CustomerDatabaseGUI();
                setVisible(false);
                customerDatabaseGUI.setVisible(true);
            }
        });
        JButton salesButton = new JButton("Manage Sales");
        switchToOther.add(salesButton);
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
    private void windowClose() {
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                printLog();
                System.exit(0);
            }
        });
    }

    //EFFECTS: prints Event long when quit
    private void printLog() {
        for (Event e : EventLog.getInstance()) {
            System.out.println(e.toString());
        }
    }
}
