package za.ac.cput.views.mainPanels;

import javax.swing.*;

public class TeacherPanel
{
    private JPanel mainPanel;

    private JPanel mainButtonPanel, classRegisterBTNpanel, childBTNpanel, incidentsBTNpanel;

    private JLabel mainLabel;
    private JPanel mainLblPanel;

    private JButton classRegisterBTN, childBTN, incidentsBTN;

    public TeacherPanel()
    {
        this.mainPanel = new JPanel();
        this.mainButtonPanel = new JPanel();
        this.classRegisterBTNpanel = new JPanel();
        this.childBTNpanel = new JPanel();
        this.incidentsBTNpanel = new JPanel();
        this.mainLabel = new JLabel("Hello Teacher. What do you want to do?");
        this.mainLblPanel = new JPanel();
        this.classRegisterBTN = new JButton("Take and view registers");
        this.childBTN = new JButton("See Children");
        this.incidentsBTN = new JButton("Manage Incidents");
    }

    public JPanel teacherGuiSetUp()
    {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainLblPanel.setLayout(new BoxLayout(mainLblPanel, BoxLayout.LINE_AXIS));
        mainLblPanel.add(mainLabel);
        mainPanel.add(mainLblPanel);

        mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel, BoxLayout.LINE_AXIS));
        childBTNpanel.add(childBTN);
        classRegisterBTNpanel.add(classRegisterBTN);
        incidentsBTNpanel.add(incidentsBTN);

        mainButtonPanel.add(childBTNpanel);
        mainButtonPanel.add(classRegisterBTNpanel);
        mainButtonPanel.add(incidentsBTNpanel);
        mainPanel.add(mainButtonPanel);

        return  mainPanel;
    }
}
