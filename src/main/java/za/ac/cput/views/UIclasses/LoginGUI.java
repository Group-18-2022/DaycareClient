package za.ac.cput.views.UIclasses;

import za.ac.cput.views.mainGUI;
import za.ac.cput.views.mainPanels.PrincipalPanel;
import za.ac.cput.views.mainPanels.SecretaryPanel;
import za.ac.cput.views.mainPanels.TeacherPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class LoginGUI implements ActionListener
{
    private JLabel usernameLbl, passwordLbl;
    private JTextField userField, passwordField;
    private JButton enterButton;

    private JPanel usernamePanel, passwordPanel, enterButtonPanel, mainPanel;

    private static JFrame mainFrame;

    static mainGUI mg;

    public LoginGUI()
    {
        mainFrame = new JFrame("Login Frame");
        mainPanel = new JPanel();

        usernameLbl = new JLabel("username");
        passwordLbl = new JLabel("password");

        userField = new JTextField();
        userField.setText("principal");
        passwordField = new JTextField();
        passwordField.setText("123");

        enterButton = new JButton("Enter");

        usernamePanel = new JPanel();
        usernamePanel.setPreferredSize(new Dimension(400, 20));
        passwordPanel = new JPanel();
        passwordPanel.setPreferredSize(new Dimension(400, 20));
        enterButtonPanel = new JPanel();

        //mainPanel.setPreferredSize(new Dimension(400, 120));

    }

    public void loginSetUp()
    {
        enterButton.addActionListener(this);

        usernamePanel.setLayout(new GridLayout(1, 2));
        usernamePanel.add(usernameLbl);
        usernamePanel.add(userField);

        passwordPanel.setLayout(new GridLayout(1, 2));
        passwordPanel.add(passwordLbl);
        passwordPanel.add(passwordField);

        enterButtonPanel.add(enterButton);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(enterButtonPanel);

        mainFrame.add(mainPanel);

        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setPreferredSize(new Dimension(500, 100));
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public JPanel setUpUser(String loginValue)
    {
        if(userField.getText().equalsIgnoreCase("principal") && loginValue.equalsIgnoreCase("123"))
        {
            PrincipalPanel pp = new PrincipalPanel();

            return pp.principalGuiSetUp();
        } else if (userField.getText().equalsIgnoreCase("teacher") && loginValue.equalsIgnoreCase("1234"))
        {
            TeacherPanel tp = new TeacherPanel();
            return tp.teacherGuiSetUp();
        } else if (userField.getText().equalsIgnoreCase("secretary") && loginValue.equalsIgnoreCase("12345"))
        {
            SecretaryPanel sp = new SecretaryPanel();
            return sp.secretaryGuiSetUp();
        }
        else
        {

        }
        return null;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String name = "";
        String pass = "";
        name = userField.getText();
        pass = passwordField.getText();
        if (e.getActionCommand().equalsIgnoreCase("Enter"))
        {
            runMainGui();
        }
    }

    public void runMainGui()
    {
        mainGUI mg = new mainGUI();
        mg.loadUserPanel(setUpUser(passwordField.getText()));
    }

    public static void killMain()
    {
        mainFrame.dispose();
        mg.getMainFrame().dispose();
    }


    public static void main(String[] args) {
        LoginGUI newLogin = new LoginGUI();
        newLogin.loginSetUp();
    }
}
