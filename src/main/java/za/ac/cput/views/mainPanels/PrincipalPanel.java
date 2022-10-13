package za.ac.cput.views.mainPanels;

import javax.swing.*;
import java.awt.*;

public class PrincipalPanel
{
    private JPanel mainPanel;
    private JPanel mainButtonPanel, classRoomBTNpanel, childBTNpanel, venueBTNPanel;
    private JLabel mainLabel;
    private JPanel mainLblPanel;
    private JButton classRoomBTN, childBTN, venueBTN;

    public PrincipalPanel()
    {
        this.mainPanel = new JPanel();
        this.mainButtonPanel = new JPanel();
        this.mainLabel = new JLabel("Hello Principal. What do you want to do?");
        this.classRoomBTNpanel = new JPanel();
        this.childBTNpanel = new JPanel();
        this.venueBTNPanel = new JPanel();
        this.mainLblPanel = new JPanel();

        this.classRoomBTN = new JButton("Manage Classrooms");
        this.childBTN = new JButton("Look at Children");
        this.venueBTN = new JButton("Manage the school's details");
    }

    public JPanel principalGuiSetUp()
    {
        classRoomBTNpanel.add(classRoomBTN);
        childBTNpanel.add(childBTN);
        venueBTNPanel.add(venueBTN);

        mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel, BoxLayout.LINE_AXIS));
        mainButtonPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.green));
        mainButtonPanel.add(classRoomBTNpanel);
        mainButtonPanel.add(childBTNpanel);
        mainButtonPanel.add(venueBTNPanel);

        mainLblPanel.setLayout(new BoxLayout(mainLblPanel, BoxLayout.LINE_AXIS));
        mainLblPanel.add(mainLabel);
        //mainLblPanel.setPreferredSize(new Dimension(200, 200));
        mainLblPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(mainLblPanel);
        mainPanel.add(mainButtonPanel);
        return mainPanel;
    }
}
