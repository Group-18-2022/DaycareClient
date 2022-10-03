package za.ac.cput.views;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import za.ac.cput.util.Helper;

/**
 * Login.java -> allows for admins and users to login into the system.
 * @author karlh
 */
public class LoginView implements ActionListener {
    private final JFrame mainFrame;
    private final JPanel pnlLeft, pnlRight;
    private final JLabel lblUsername, lblPassword, lblImage, lblHeading;
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JButton btnLogin;
    private final Font fontNormal, fontHeading;

    public LoginView() {
        mainFrame = new JFrame("Login: Daycare System");
        pnlLeft = new JPanel();
        pnlRight = new JPanel();

        lblUsername = new JLabel("Username: ");
        lblPassword = new JLabel("Password: ");
        lblImage = new JLabel(new ImageIcon("C:\\Users\\karlh\\OneDrive\\Documents\\GitHub\\DaycareClient\\DC.png"));
        lblHeading = new JLabel("Daycare System: Login");

        txtUsername = new JTextField(30);
        txtPassword = new JPasswordField(30);
        txtUsername.setMaximumSize(txtUsername.getPreferredSize());
        txtPassword.setMaximumSize(txtPassword.getPreferredSize());

        btnLogin = new JButton("LOGIN");

        fontNormal = new Font(Font.SERIF, Font.PLAIN,  15);
        fontHeading = new Font(Font.SERIF, Font.BOLD,  25);

        lblImage.setPreferredSize(new Dimension(400, 400));
        pnlLeft.setMaximumSize(new Dimension(400, 400));
        pnlRight.setMaximumSize(new Dimension(600, 400));

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        txtUsername.setText("jh@gmail.com");
//        txtPassword.setText("123456");

    }

    public void setGUI() {
        btnLogin.addActionListener(this);

        lblHeading.setFont(fontHeading);
        txtUsername.setFont(fontNormal);
        txtPassword.setFont(fontNormal);

        btnLogin.setBackground(Color.black);
        btnLogin.setForeground(Color.decode("#f59b42"));

        pnlLeft.add(lblImage);

        pnlRight.setBackground(Color.decode("#f59b42"));
        pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));
        pnlRight.setBorder(BorderFactory.createEmptyBorder(100, 80, 0, 100));
        pnlRight.add(lblHeading);
        pnlRight.add(lblUsername);
        pnlRight.add(txtUsername);
        pnlRight.add(lblPassword);
        pnlRight.add(txtPassword);
        pnlRight.add(btnLogin);

        mainFrame.add(pnlLeft, BorderLayout.WEST);
        mainFrame.add(pnlRight, BorderLayout.EAST);

        mainFrame.setSize(new Dimension(1000, 400));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnLogin) {
            if(txtPassword.getText().equals("") || Helper.isValidEmail("Email", txtUsername.getText())) {
                JOptionPane.showMessageDialog(mainFrame, "Incorrect username / password");
            } else {
                String email = txtUsername.getText();
                String password = txtPassword.getText();

                //TODO: Send POST Request to login

            }

        }
    }


}
