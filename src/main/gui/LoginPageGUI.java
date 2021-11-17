package gui;

import model.Customer;
import model.Date;
import model.Item;

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

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: helper function that adds the button that switches to the other frame (CustomerDatabaseGUI)
    public void switchToOther(JPanel mainArea) {
        JButton button = new JButton("Manage Customers");
        mainArea.add(button, BorderLayout.SOUTH);
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CustomerDatabaseGUI customerDatabaseGUI = new CustomerDatabaseGUI();
                setVisible(false);
                customerDatabaseGUI.setVisible(true);
            }
        });
    }
}
