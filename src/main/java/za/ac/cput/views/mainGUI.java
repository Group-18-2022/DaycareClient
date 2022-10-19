package za.ac.cput.views;

import za.ac.cput.domain.DayCareVenue;
import za.ac.cput.views.UIclasses.*;
import za.ac.cput.views.mainPanels.PrincipalPanel;
import za.ac.cput.views.mainPanels.SecretaryPanel;
import za.ac.cput.views.mainPanels.TeacherPanel;

import javax.swing.*;
import java.awt.*;

public class mainGUI //extends JFrame
{
    private static JFrame mainFrame;
    private JPanel mainPanel;

    public mainGUI()
    {
        mainFrame = new JFrame("Main GUI Frame");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(950, 650));

        //ClassroomUI cr = new ClassroomUI();
        //add(cr.classRoomSetUp());

        //ChildUI cui = new ChildUI();
        //add(cui.childSetUp());

        //PrincipalUI pui = new PrincipalUI();
        //add(pui.principalSetUp());

        //ParentUI parentUI = new ParentUI();
        //add(parentUI.parentSetUp());

        //EmergencyServiceProviderUI espUI = new EmergencyServiceProviderUI();
        //mainPanel.add(espUI.espSetUp());

        //IncidentUI iui = new IncidentUI();
        //add(iui.incidentSetUp());

        //setUpUser(1);
        //mainPanel.add(jp);

        //mainPanel.add(jp);

        mainPanel.add(new ChildUI().childSetUp()); //This is how to manually run each GUI

        mainFrame.add(mainPanel);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
    }
    public static final JFrame getMainFrame(){
        return mainFrame;
    }

    public JPanel loadUserPanel(JPanel jp)
    {
        mainPanel.removeAll();
        mainPanel.add(jp);

        mainPanel.revalidate();
        mainPanel.repaint();

        return jp;
    }

    public static void main(String[] args) {
        new mainGUI();
    }
}
