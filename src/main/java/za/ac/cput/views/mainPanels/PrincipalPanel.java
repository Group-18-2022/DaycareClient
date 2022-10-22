package za.ac.cput.views.mainPanels;

import za.ac.cput.domain.ClassGroup;
import za.ac.cput.domain.ClassRegister;
import za.ac.cput.views.UIclasses.*;
import za.ac.cput.views.mainGUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PrincipalPanel extends JFrame implements ActionListener
{
    static mainGUI mgui;
    private final Font headingFont;
    private JPanel mainPanel;
    private JPanel mainButtonPanel, classRoomBTNpanel, childBTNpanel, venueBTNPanel, logoutBtnPanel;
    private JLabel mainLabel, centerLabel;
    private JTextArea addressArea;
    private JPanel mainLblPanel, centerPanel, addressPanel;
    private JButton classRoomBTN, childBTN, venueBTN, logoutBTN, doctorBTN, parentBTN, classGroupBTN, classRegisterBTN,
            espBTN, vehicleRegBTN, driverBTN, incidentBTN, principalBTN, secretaryBTN, teacherBTN;
    private JPanel logoutBTNPanel, doctorBTNPanel, parentBTNPanel, classGroupBTNPanel,
            classRegisterBTNPanel, espBTNPanel, vehicleRegBTNPanel, driverBTNPanel, incidentBTNPanel, principalBTNPanel,
            secretaryBTNPanel, teacherBTNPanel;

    private JLabel wIcon;

    public PrincipalPanel()
    {
        headingFont = new Font("Rockwell", Font.PLAIN, 20);
        imageMethod();
        this.mainPanel = new JPanel();

        this.centerPanel = new JPanel();
        centerPanel.setBackground(new Color(5, 35, 90));
        this.addressPanel = new JPanel();

        this.centerLabel = new JLabel();
        this.addressArea = new JTextArea("\n\n\t\t\tFor Daycare:\n" +
                "\t\t\tUlwazi Daycare Center\n" +
                "\t\t\t18 Tutu Drive\n" +
                "\t\t\tVosloorus\n" +
                "\t\t\tGauteng");
        addressArea.setFont(headingFont);
        addressArea.setEditable(false);
        addressArea.setBackground(new Color(240, 240, 240));

        this.mainButtonPanel = new JPanel();
        this.mainLabel = new JLabel("Hello Principal. What do you want to do?");
        mainLabel.setFont(headingFont);
        this.classRoomBTNpanel = new JPanel();
        this.childBTNpanel = new JPanel();
        this.venueBTNPanel = new JPanel();
        this.mainLblPanel = new JPanel();
        this.logoutBtnPanel = new JPanel();
        logoutBTNPanel = new JPanel();
        doctorBTNPanel= new JPanel();
        parentBTNPanel= new JPanel();
        classGroupBTNPanel= new JPanel();
        classRegisterBTNPanel = new JPanel();
        espBTNPanel = new JPanel();
        vehicleRegBTNPanel = new JPanel();
        driverBTNPanel = new JPanel();
        incidentBTNPanel = new JPanel();
        principalBTNPanel = new JPanel();
        secretaryBTNPanel = new JPanel();
        teacherBTNPanel = new JPanel();


        this.classRoomBTN = new JButton("Manage Classrooms");
        this.childBTN = new JButton("Look at Children");
        this.venueBTN = new JButton("Manage the school's details");
        this.logoutBTN = new JButton("Logout");
        logoutBTN.setBackground(Color.red);

        classRoomBTN = new JButton("Classrooms");
        doctorBTN = new JButton("Doctors");
        parentBTN= new JButton("Parents");
        classGroupBTN = new JButton("Class Groups");
        classRegisterBTN= new JButton("Registers");
        espBTN= new JButton("Emergency Services");
        vehicleRegBTN= new JButton("Vehicle Details");
        driverBTN = new JButton("Drivers");
        incidentBTN = new JButton("Incidents");
        principalBTN= new JButton("Principals");
        secretaryBTN= new JButton("Secretaries");
        teacherBTN= new JButton("Teachers");

        principalGuiSetUp();
    }

    public JPanel principalGuiSetUp()
    {

        childBTN.addActionListener(this);
        classGroupBTN.addActionListener(this);
        classRegisterBTN.addActionListener(this);
        venueBTN.addActionListener(this);
        logoutBTN.addActionListener(this);
        doctorBTN.addActionListener(this);
        classRoomBTN.addActionListener(this);
        parentBTN.addActionListener(this);
        espBTN.addActionListener(this);
        incidentBTN.addActionListener(this);
        principalBTN.addActionListener(this);


        logoutBtnPanel.add(logoutBTN);

        classRoomBTNpanel.add(classRoomBTN);
        childBTNpanel.add(childBTN);
        venueBTNPanel.add(venueBTN);

        centerPanel.add(centerLabel);
        addressPanel.add(addressArea);

        doctorBTNPanel.add(doctorBTN);
        parentBTNPanel.add(parentBTN);
        classGroupBTNPanel.add(classGroupBTN);
        classRegisterBTNPanel.add(classRegisterBTN);
        espBTNPanel.add(espBTN);
        vehicleRegBTNPanel.add(vehicleRegBTN);
        driverBTNPanel.add(driverBTN);
        incidentBTNPanel.add(incidentBTN);
        principalBTNPanel.add(principalBTN);
        secretaryBTNPanel.add(secretaryBTN);
        teacherBTNPanel.add(teacherBTN);

        //mainButtonPanel.setLayout(new BoxLayout(mainButtonPanel, BoxLayout.LINE_AXIS));
        mainButtonPanel.setLayout(new FlowLayout());
        mainButtonPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.green));
        mainButtonPanel.setPreferredSize(new Dimension(400, 150));
        mainButtonPanel.add(classRoomBTNpanel);
        mainButtonPanel.add(childBTNpanel);
        mainButtonPanel.add(venueBTNPanel);
        //mainButtonPanel.add(logoutBtnPanel);
        mainButtonPanel.add(doctorBTNPanel);
        mainButtonPanel.add(parentBTNPanel);
        mainButtonPanel.add(classGroupBTNPanel);
        mainButtonPanel.add(espBTNPanel);
        mainButtonPanel.add(vehicleRegBTNPanel);
        mainButtonPanel.add(driverBTNPanel);
        mainButtonPanel.add(incidentBTNPanel);
        mainButtonPanel.add(principalBTNPanel);
        mainButtonPanel.add(secretaryBTNPanel);
        mainButtonPanel.add(teacherBTNPanel);

        mainLblPanel.setLayout(new FlowLayout());
        mainLblPanel.add(mainLabel);
        mainLblPanel.add(logoutBtnPanel);


        //mainLblPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.RED));
        mainLblPanel.setPreferredSize(new Dimension(400, 60));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        //mainPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.orange));
        mainPanel.setPreferredSize(new Dimension(550, 70));

        //centerPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));
        centerPanel.add(wIcon);
        addressPanel.setLayout(new BoxLayout(addressPanel, BoxLayout.PAGE_AXIS));
        addressPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.orange));
        //addressPanel.setPreferredSize(new Dimension(550, 150));

        mainPanel.add(mainLblPanel);
        mainPanel.add(mainButtonPanel);
        mainPanel.add(centerPanel);
        mainPanel.add(addressPanel);
        return mainPanel;
    }

    public void imageMethod() {
        BufferedImage wPic = null;
        try {
            wPic = ImageIO.read(new File("Navy Logo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image newImage = wPic.getScaledInstance(400, 100, Image.SCALE_DEFAULT);

        wIcon = new JLabel(new ImageIcon(newImage));
        wIcon.setPreferredSize(new Dimension(400, 100));
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getActionCommand().equalsIgnoreCase("doctors"))
        {
            mainPanel.removeAll();
            DoctorUI dui = new DoctorUI();
            mainPanel.add(dui.venueSetUp());
            mainPanel.revalidate();
            mainPanel.repaint();

        }
        if (e.getActionCommand().equalsIgnoreCase("Class Groups"))
        {
            mainPanel.removeAll();
            ClassGroupUI cgui = new ClassGroupUI();
            mainPanel.add(cgui.groupSetUp());
            mainPanel.revalidate();
            mainPanel.repaint();

        }
        if (e.getActionCommand().equalsIgnoreCase("Registers"))
        {
            mainPanel.removeAll();

            ClassRegisterUI creg = new ClassRegisterUI();
            mainPanel.add(creg.registerSetUp());

            mainPanel.revalidate();
            mainPanel.repaint();
        }

        if (e.getActionCommand().equalsIgnoreCase("manage the school's details"))
                {
                    mainPanel.removeAll();
                    VenueUI vui = new VenueUI();
                    mainPanel.add(vui.venueSetUp());
                    mainPanel.revalidate();
                    mainPanel.repaint();
                }
        if (e.getActionCommand().equalsIgnoreCase("look at children"))
        {
            mainPanel.removeAll();
            ChildUI chui = new ChildUI();
            mainPanel.add(chui.childSetUp());
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        if (e.getActionCommand().equalsIgnoreCase("classrooms"))
        {
            mainPanel.removeAll();
            ClassroomUI clrui = new ClassroomUI();
            mainPanel.add(clrui.classRoomSetUp());
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        if (e.getActionCommand().equalsIgnoreCase("emergency services"))
        {
            mainPanel.removeAll();
            EmergencyServiceProviderUI emspui = new EmergencyServiceProviderUI();
            mainPanel.add(emspui.espSetUp());
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        if (e.getActionCommand().equalsIgnoreCase("incidents"))
        {
            mainPanel.removeAll();
            IncidentUI incui = new IncidentUI();
            mainPanel.add(incui.incidentSetUp());
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        if (e.getActionCommand().equalsIgnoreCase("parents"))
        {
            mainPanel.removeAll();
            ParentUI parui = new ParentUI();
            mainPanel.add(parui.parentSetUp());
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        if (e.getActionCommand().equalsIgnoreCase("principals"))
        {
            mainPanel.removeAll();
            PrincipalUI pincui = new PrincipalUI();
            mainPanel.add(pincui.principalSetUp());
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
