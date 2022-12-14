package za.ac.cput.views.mainPanels;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CrudPanel
{
    private JPanel mainPanel;

    private JPanel createUpdatePanel;
    private JPanel readPanel;

    private JPanel createPanel;
    private JPanel updatePanel;

    private JPanel createLblPanel, createCenterPanel, createButtonPanel;

    private JPanel updateLblPanel, updateCenterPanel, updateButtonPanel;

    private JPanel tableLblPanel, tablePanel, tableButtonPanel, logoPanel;

    private JLabel createLbl, updateLbl, readLbl;
    private JLabel wIcon;

    private JButton createButton;
    private JButton deleteButton;
    private JButton updateButton;
    private JButton backHomeBTN;
    private JButton logOutButton, printButton;
    private JPanel homeButtonPanel, logoutBTNpanel, homeAndLogoutPanel;

    private Font headingFont;

    public CrudPanel()
    {
        imageMethod();
        headingFont = new Font("Rockwell", Font.PLAIN, 20);

        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(700, 550));
        createUpdatePanel = new JPanel();
        readPanel = new JPanel();
        createPanel = new JPanel();
        updatePanel = new JPanel();
        createLblPanel = new JPanel();
        createCenterPanel = new JPanel();
        createButtonPanel = new JPanel();
        updateLblPanel = new JPanel();
        updateCenterPanel = new JPanel();
        updateButtonPanel = new JPanel();
        tableLblPanel = new JPanel();
        tablePanel = new JPanel();
        logoPanel = new JPanel();
        //tablePanel.setPreferredSize(new Dimension(950, 100));
        tableButtonPanel = new JPanel();
        createLbl = new JLabel("Create Record");
        createLbl.setFont(headingFont);
        readLbl = new JLabel("Read Record");
        readLbl.setFont(headingFont);

        createButton = new JButton("Create Record");
        deleteButton = new JButton("Delete");
        updateButton = new JButton("Update");
        printButton = new JButton("Print");

        backHomeBTN = new JButton("Back Home");
        backHomeBTN.setPreferredSize(new Dimension(100, 40));
        logOutButton = new JButton("Logout");
        logOutButton.setPreferredSize(new Dimension(100, 40));

        homeButtonPanel = new JPanel();
        logoutBTNpanel = new JPanel();
        homeAndLogoutPanel = new JPanel();
    }

    public JPanel crudSetUp(JPanel panelCreate, JPanel panelTable)
    {

        //logoPanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 60, ));
        logoPanel.setPreferredSize(new Dimension(20, 85));
        logoPanel.add(wIcon);


        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        //mainPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red));
        //mainPanel.setPreferredSize(new Dimension(950, 700));

        createUpdatePanel.setLayout(new BoxLayout(createUpdatePanel, BoxLayout.LINE_AXIS));

        createUpdatePanel.setBorder(BorderFactory.createMatteBorder(5, 5, 5, 5, Color.magenta));

        readPanel.setLayout(new BoxLayout(readPanel, BoxLayout.PAGE_AXIS));
        readPanel.setPreferredSize(new Dimension(950, 400));
        readPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.orange));

        createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.PAGE_AXIS));
        createPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.GREEN));

        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.PAGE_AXIS));
        updatePanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));

        homeAndLogoutPanel.setLayout(new GridLayout(1, 2));
        homeButtonPanel.add(backHomeBTN);
        homeButtonPanel.add(logOutButton);
        homeAndLogoutPanel.add(logoPanel);
        homeAndLogoutPanel.add(homeButtonPanel);
        //homeAndLogoutPanel.add(logoutBTNpanel);


        mainPanel.add(homeAndLogoutPanel);
        mainPanel.add(createUpdatePanel);
        mainPanel.add(readPanel);

        //createUpdatePanel.add(introPanel);
        createUpdatePanel.add(createPanel);
        //createUpdatePanel.add(updatePanel);

        readPanel.add(tableLblPanel);
        readPanel.add(tablePanel);
        readPanel.add(tableButtonPanel);

        createPanel.add(createLblPanel);
        createPanel.add(createCenterPanel);
        createPanel.add(createButtonPanel);

        updatePanel.add(updateLblPanel);
        updatePanel.add(updateCenterPanel);
        updatePanel.add(updateButtonPanel);

        tableLblPanel.add(readLbl);
        tablePanel.add(panelTable);
        tableButtonPanel.add(printButton);
        tableButtonPanel.add(updateButton);

        tableButtonPanel.add(deleteButton);

        createLblPanel.add(createLbl);

        createCenterPanel.setLayout(new GridLayout(1, 2));
        createCenterPanel.add(panelCreate);
        //createCenterPanel.setPreferredSize(new Dimension(200, 90));
        createCenterPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, new Color(22,140,210)));

        createButtonPanel.add(createButton);



        return mainPanel;
    }
    public void createBtnAddActionListener(ActionListener al)
    {
        createButton.addActionListener(al);
        deleteButton.addActionListener(al);
        backHomeBTN.addActionListener(al);
        logOutButton.addActionListener(al);
        updateButton.addActionListener(al);
        printButton.addActionListener(new HelloWorldPrinter());
    }

    public void imageMethod() {
        BufferedImage wPic = null;
        try {
            wPic = ImageIO.read(new File("Navy Logo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Image newImage = wPic.getScaledInstance(400, 65, Image.SCALE_DEFAULT);

        wIcon = new JLabel(new ImageIcon(newImage));
        wIcon.setPreferredSize(new Dimension(400, 65));
    }

}
