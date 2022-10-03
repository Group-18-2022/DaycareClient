package za.ac.cput.views.components;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * Sidebar.java -> Is a component that is added to all the JFrames after login.
 * Use for navigation purposes.
 * @author karlh
 */
public class Sidebar extends JPanel {
    private Component parent;
    //TODO:Rename labels, buttons and do role logic
    private final JLabel lblAdminNewProducts, lblAdminNewUsers;
    private final JLabel lblNewCustomers, lblCustomerPurchase, lblGetInvoice, lblWriteReport, lblLogout;
    private Font fontHeading;

    public Sidebar() {
        parent = this.getParent();

        lblAdminNewProducts = new JLabel("Add Product  ");
        lblAdminNewUsers = new JLabel("Create user");
        lblNewCustomers = new JLabel("Add Customer");
        lblCustomerPurchase = new JLabel("Invoice Customer");
        lblGetInvoice = new JLabel("Get Invoice");
        lblWriteReport = new JLabel("Get Business Report");
        lblLogout = new JLabel("     Logout");

        fontHeading = new Font(Font.SERIF, Font.BOLD, 15);

        this.setSidebarGUI();
    }

    public void setSidebarGUI() {
        String userRole = "Principal";

        lblAdminNewProducts.setFont(fontHeading);
        lblAdminNewUsers.setFont(fontHeading);
        lblNewCustomers.setFont(fontHeading);
        lblCustomerPurchase.setFont(fontHeading);
        lblGetInvoice.setFont(fontHeading);
        lblWriteReport.setFont(fontHeading);
        lblLogout.setFont(fontHeading);

        this.setPreferredSize(new Dimension(150, 750));

        this.add(lblAdminNewProducts);
        this.add(lblAdminNewUsers);
        this.add(lblNewCustomers);
        this.add(lblCustomerPurchase);
        this.add(lblGetInvoice);
        this.add(lblWriteReport);
        this.add(lblLogout);

        if (userRole.equals("Principal")) {
            lblAdminNewProducts.setVisible(true);
            lblAdminNewUsers.setVisible(true);
            lblLogout.setVisible(true);

            lblNewCustomers.setVisible(false);
            lblCustomerPurchase.setVisible(false);
            lblGetInvoice.setVisible(false);
            lblWriteReport.setVisible(false);

            lblAdminNewProducts.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblAdminNewUsers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
//        else {
//            lblAdminNewProducts.setVisible(false);
//            lblAdminNewUsers.setVisible(false);
//
//            lblNewCustomers.setVisible(true);
//            lblCustomerPurchase.setVisible(true);
//            lblGetInvoice.setVisible(true);
//            lblWriteReport.setVisible(true);
//            lblLogout.setVisible(true);
//
//            lblNewCustomers.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//            lblCustomerPurchase.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//            lblGetInvoice.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//            lblWriteReport.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//            lblLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
//        }

        this.setBackground(Color.decode("#f59b42"));
//        this.setListener();
    }

//    private void setListener() {
//        lblAdminNewProducts.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                CreateProduct cp = new CreateProduct();
//                cp.setGUI();
//                lblAdminNewProducts.setForeground(Color.WHITE);
//                lblAdminNewUsers.setForeground(Color.BLACK);
//            }
//        });
//        lblAdminNewUsers.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                CreateUser c = new CreateUser();
//                c.setGUI();
//                lblAdminNewUsers.setForeground(Color.WHITE);
//                lblAdminNewProducts.setForeground(Color.BLACK);
//            }
//        });
//        lblNewCustomers.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                CreateCustomer cc = new CreateCustomer();
//                cc.setGUI();
//            }
//        });
//        lblCustomerPurchase.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                CreateInvoice ci = new CreateInvoice();
//                ci.setGUI();
//            }
//        });
//        lblGetInvoice.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                GetInvoice getInvoice = new GetInvoice();
//                getInvoice.setGUI();
//            }
//        });
//        lblWriteReport.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                invoiceActions.writeBusinessReport();
//                JOptionPane.showMessageDialog(parent, "Business Report Produced");
//            }
//        });
//        lblLogout.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                Login loginGUI = new Login();
//                loginGUI.setGUI();
//                UserActions.setUser(null);
//                parent.setVisible(false);
//            }
//        });
//    }

    public JLabel getLblNewCustomers() {
        return lblNewCustomers;
    }

    public JLabel getLblAdminNewUsers() {
        return lblAdminNewUsers;
    }

    public JLabel getLblAdminNewProducts() {
        return lblAdminNewProducts;
    }

    public JLabel getLblCustomerPurchase() {
        return lblCustomerPurchase;
    }

    public JLabel getLblGetInvoice() {
        return lblGetInvoice;
    }

    public JLabel getLblWriteReport() {
        return lblWriteReport;
    }

    public JLabel getLblLogout() {
        return lblLogout;
    }
}
