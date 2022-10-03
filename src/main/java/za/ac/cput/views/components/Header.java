package za.ac.cput.views.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
/**
 * Header.java -> A component that contains the user details
 * @author karlh
 */
public class Header extends JPanel {
//    private final JLabel lblName, lblTypeOfUser, lblRole, lblTypeOfRole, lblEmail, lblEmailType, lblId, lblUserId;
    private final JLabel lblName;
//    private User user;
    private Font fontBold;

    public Header() {
//        user = UserActions.getUser();
        fontBold = new Font(Font.SERIF, Font.BOLD,  12);
        lblName = new JLabel("Name: ");
//        lblTypeOfUser = new JLabel(user.getName() + "        ");
//        lblRole = new JLabel("Role: ");
//        lblTypeOfRole = new JLabel(user.getRole() + "        ");
//        lblEmail = new JLabel("Email: ");
//        lblEmailType = new JLabel(user.getEmail() + "        ");
//        lblId = new JLabel("ID: ");
//        lblUserId = new JLabel(user.getUserId() + "        ");
//

        this.setHeader();
    }

    public void setHeader() {
        lblName.setFont(fontBold);
//        lblRole.setFont(fontBold);
//        lblEmail.setFont(fontBold);
//        lblId.setFont(fontBold);

        this.setPreferredSize(new Dimension(70, 30));
        this.setBackground(Color.decode("#f59b42"));

        this.add(lblName);
//        this.add(lblTypeOfUser);
//        this.add(lblRole);
//        this.add(lblTypeOfRole);
//        this.add(lblEmail);
//        this.add(lblEmailType);
//        this.add(lblId);
//        this.add(lblUserId);
    }

}