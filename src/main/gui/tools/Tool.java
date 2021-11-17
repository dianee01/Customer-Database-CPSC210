package gui.tools;

import gui.CustomerDatabaseGUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

//This class references code from this Simple Drawing Editor
//Link: https://github.students.cs.ubc.ca/CPSC210/SimpleDrawingPlayer-Complete
//abstract class that has many of the common tool functions
public abstract class Tool {
    protected JButton button;
    protected CustomerDatabaseGUI editor;
    private boolean active;


    public Tool(CustomerDatabaseGUI customerDatabaseGUI, JComponent parent) {
        this.editor = editor;
        createButton(parent);
        buttonClicked(parent);
        addToParent(parent);
        active = false;
    }

    //EFFECTS: sets this Tool's active field to true
    public void activate() {
        active = true;
    }

    //EFFECTS: sets this Tool's active field to false
    public void deactivate() {
        active = false;
    }

    //EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    //MODIFIES: parent
    //EFFECTS: adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    //EFFECTS: default behaviour does nothing
    public abstract void buttonClicked(JComponent parent);
}

