package za.ac.cput.views;

import za.ac.cput.views.UIclasses.ClassroomUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainGUI extends JFrame implements ActionListener
{
    private Frame mainFrame;
    private JPanel mainPanel;

    public mainGUI()
    {
        mainFrame = new Frame();
        mainPanel = new JPanel();

        ClassroomUI cr = new ClassroomUI();
        add(cr.classRoomSetUp());


        mainFrame.add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        setSize(950, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new mainGUI();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "create")
        {
            System.out.println("hahahahaha");
        }

    }
}
