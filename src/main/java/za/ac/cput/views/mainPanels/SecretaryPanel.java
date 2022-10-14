package za.ac.cput.views.mainPanels;

import javax.swing.*;

public class SecretaryPanel
{
    private JPanel mainPanel;

    private JPanel mainButtonPanel, classRegisterBTNpanel,
            childBTNpanel, incidentsBTNpanel, teacherBTNpanel, venueBTNPanel, classRoomBTNpanel;
    private JLabel mainLabel;
    private JPanel mainLblPanel;
    private JButton classRegisterBTN, childBTN, incidentsBTN, teacherBTN, venueBTN, classRoomBTN;
    public SecretaryPanel()
    {
        this.mainPanel = new JPanel();
        this.mainButtonPanel = new JPanel();
        this.classRegisterBTNpanel = new JPanel();
        this.childBTNpanel = new JPanel();
        this.incidentsBTNpanel = new JPanel();
        this.teacherBTNpanel = new JPanel();
        this.venueBTNPanel = new JPanel();
        this.classRoomBTNpanel = new JPanel();
        this.mainLabel = new JLabel("Hello Secretary. What do you want to do?");
        this.mainLblPanel = new JPanel();
        this.classRegisterBTN = new JButton("view and take class Register");
        this.childBTN = new JButton("See Children");
        this.incidentsBTN = new JButton("see and create incident reports");
        this.teacherBTN = new JButton("see teachers");
        this.venueBTN = new JButton("manage school details");
        this.classRoomBTN = new JButton("Manage classrooms");
    }
    public JPanel secretaryGuiSetUp()
    {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainLblPanel.add(mainLabel);
        mainPanel.add(mainLblPanel);

        classRegisterBTNpanel.add(classRegisterBTN);
        childBTNpanel.add(childBTN);
        incidentsBTNpanel.add(incidentsBTN);
        teacherBTNpanel.add(teacherBTN);
        venueBTNPanel.add(venueBTN);
        classRoomBTNpanel.add(classRoomBTN);

        mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel, BoxLayout.LINE_AXIS));
        mainButtonPanel.add(classRegisterBTNpanel);
        mainButtonPanel.add(childBTNpanel);
        mainButtonPanel.add(incidentsBTNpanel);
        mainButtonPanel.add(teacherBTNpanel);
        mainButtonPanel.add(venueBTNPanel);
        mainButtonPanel.add(classRoomBTNpanel);
        mainPanel.add(mainButtonPanel);

        return mainPanel;
    }
}
