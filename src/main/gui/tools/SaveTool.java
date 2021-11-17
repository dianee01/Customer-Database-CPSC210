package gui.tools;

import gui.CustomerDatabaseGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class SaveTool extends Tool {

    public SaveTool(CustomerDatabaseGUI editor, JComponent parent) {
        super(editor, parent);

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

    }
}