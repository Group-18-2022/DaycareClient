package za.ac.cput.views.mainPanels;

import za.ac.cput.views.UIclasses.LoginGUI;
import za.ac.cput.views.UIclasses.VenueUI;
import za.ac.cput.views.mainGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrincipalPanel extends JFrame implements ActionListener
{
    static mainGUI mgui;
    private JPanel mainPanel;
    private JPanel mainButtonPanel, classRoomBTNpanel, childBTNpanel, venueBTNPanel, logoutBtnPanel;
    private JLabel mainLabel;
    private JPanel mainLblPanel;
    private JButton classRoomBTN, childBTN, venueBTN, logoutBTN;

    public PrincipalPanel()
    {

        this.mainPanel = new JPanel();

        this.mainButtonPanel = new JPanel();
        this.mainLabel = new JLabel("Hello Principal. What do you want to do?");
        this.classRoomBTNpanel = new JPanel();
        this.childBTNpanel = new JPanel();
        this.venueBTNPanel = new JPanel();
        this.mainLblPanel = new JPanel();
        this.logoutBtnPanel = new JPanel();

        this.classRoomBTN = new JButton("Manage Classrooms");
        this.childBTN = new JButton("Look at Children");
        this.venueBTN = new JButton("Manage the school's details");
        this.logoutBTN = new JButton("Logout");

        principalGuiSetUp();
    }

    public JPanel principalGuiSetUp()
    {
        classRoomBTN.addActionListener(this);
        childBTN.addActionListener(this);
        venueBTN.addActionListener(this);
        logoutBTN.addActionListener(this);

        classRoomBTNpanel.add(classRoomBTN);
        childBTNpanel.add(childBTN);
        venueBTNPanel.add(venueBTN);

        logoutBtnPanel.add(logoutBTN);

        mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel, BoxLayout.LINE_AXIS));
        mainButtonPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.green));
        mainButtonPanel.add(classRoomBTNpanel);
        mainButtonPanel.add(childBTNpanel);
        mainButtonPanel.add(venueBTNPanel);
        mainButtonPanel.add(logoutBtnPanel);

        mainLblPanel.setLayout(new BoxLayout(mainLblPanel, BoxLayout.LINE_AXIS));
        mainLblPanel.add(mainLabel);
        mainLblPanel.setPreferredSize(new Dimension(200, 60));
        mainLblPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(550, 70));
        mainPanel.add(mainLblPanel);
        mainPanel.add(mainButtonPanel);
        return mainPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equalsIgnoreCase("manage classrooms")){}
        if (e.getActionCommand().equalsIgnoreCase("look at children")){}

        if (e.getActionCommand().equalsIgnoreCase("manage the school's details"))
                {
                    mainPanel.removeAll();

                    VenueUI vui = new VenueUI();
                    mainPanel.add(vui.venueSetUp());

                    mainPanel.revalidate();
                    mainPanel.repaint();

                }
        if (e.getActionCommand().equalsIgnoreCase("Logout"))
        {
            LoginGUI.killMain();
            LoginGUI newLogin = new LoginGUI();
            newLogin.loginSetUp();
        }

    }
}
