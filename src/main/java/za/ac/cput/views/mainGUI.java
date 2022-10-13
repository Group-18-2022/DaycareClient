package za.ac.cput.views;

import za.ac.cput.views.UIclasses.ClassroomUI;
import za.ac.cput.views.mainPanels.PrincipalPanel;
import za.ac.cput.views.mainPanels.SecretaryPanel;
import za.ac.cput.views.mainPanels.TeacherPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainGUI extends JFrame
{
    private Frame mainFrame;
    private JPanel mainPanel;

    public mainGUI()
    {
        mainFrame = new Frame();
        mainPanel = new JPanel();

        ClassroomUI cr = new ClassroomUI();
        add(cr.classRoomSetUp());
        //dispose();

        //setUpUser(3);

        mainFrame.add(mainPanel);
        pack();
        setLocationRelativeTo(null);
        //setSize(950, 700);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setUpUser(int loginValue)
    {
        if(loginValue == 1)
        {
            PrincipalPanel pp = new PrincipalPanel();
            add(pp.principalGuiSetUp());
        } else if (loginValue == 2)
        {
            TeacherPanel tp = new TeacherPanel();
            add(tp.teacherGuiSetUp());
        } else if (loginValue == 3)
        {
            SecretaryPanel sp = new SecretaryPanel();
            add(sp.secretaryGuiSetUp());
        }
    }

    public static void main(String[] args)
    {
        new mainGUI();
    }

}
