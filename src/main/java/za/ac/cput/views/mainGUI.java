package za.ac.cput.views;

import za.ac.cput.views.UIclasses.ClassroomUI;

import javax.swing.*;
import java.awt.*;

public class mainGUI
{
    private Frame mainFrame;
    private JPanel mainPanel;

    public mainGUI()
    {
        mainFrame = new Frame();
        mainPanel = new JPanel();
    }

    public void setUp()
    {
        ClassroomUI cr = new ClassroomUI();

        mainPanel.add(cr.classRoomSetUp());

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        //mainFrame.setSize(750, 620);
        mainFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new mainGUI().setUp();
    }
}
