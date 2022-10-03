package za.ac.cput.views.mainPanels;

import javax.swing.*;
import java.awt.*;

public class CrudPanel
{
    private JPanel mainPanel;

    private JPanel createUpdatePanel;
    private JPanel readPanel;

    private JPanel createPanel;
    private JPanel updatePanel;

    private JPanel createLblPanel, createCenterPanel, createButtonPanel;

    private JPanel updateLblPanel, updateCenterPanel, updateButtonPanel;

    private JPanel tableLblPanel, tablePanel, tableButtonPanel;

    private JLabel createLbl, updateLbl, readLbl;

    private JButton createButton, updateButton, deleteButton;

    public CrudPanel()
    {
        mainPanel = new JPanel();
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
        tableButtonPanel = new JPanel();
        createLbl = new JLabel("Create");
        updateLbl = new JLabel("Update");
        readLbl = new JLabel("Read");
        createButton = new JButton("create");
        updateButton = new JButton("update");
        deleteButton = new JButton("delete");
    }

    public JPanel crudSetUp(JPanel panelCreate, JPanel panelUpdate, JPanel panelTable)
    {
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.red));

        createUpdatePanel.setLayout(new BoxLayout(createUpdatePanel, BoxLayout.LINE_AXIS));
        createUpdatePanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.blue));

        readPanel.setLayout(new BoxLayout(readPanel, BoxLayout.PAGE_AXIS));
        readPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.blue));

        createPanel.setLayout(new BoxLayout(createPanel, BoxLayout.PAGE_AXIS));
        createPanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));

        updatePanel.setLayout(new BoxLayout(updatePanel, BoxLayout.PAGE_AXIS));
        updatePanel.setBorder(BorderFactory.createMatteBorder(4, 4, 4, 4, Color.black));

        mainPanel.add(createUpdatePanel);
        mainPanel.add(readPanel);

        createUpdatePanel.add(createPanel);
        createUpdatePanel.add(updatePanel);

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
        tableButtonPanel.add(deleteButton);

        createLblPanel.add(createLbl);
        createCenterPanel.add(panelCreate);
        createButtonPanel.add(createButton);

        updateLblPanel.add(updateLbl);
        updateCenterPanel.add(panelUpdate);
        updateButtonPanel.add(updateButton);

        return mainPanel;
    }
}
