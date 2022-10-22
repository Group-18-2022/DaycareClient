package za.ac.cput.views.mainPanels;

import za.ac.cput.views.UIclasses.*;

import javax.swing.*;
import java.awt.*;

public class mainFrameContainer //extends JFrame
{
    private static JFrame mainFrame;
    private JPanel mainPanel;

    public mainFrameContainer()
    {
        mainFrame = new JFrame("Main GUI Frame");
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setPreferredSize(new Dimension(950, 650));

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

}
