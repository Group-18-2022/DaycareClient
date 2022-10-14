package za.ac.cput.views.components;

import za.ac.cput.views.LoginView;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * Loader.java -> Is the start screen for the project to load
 * @author karlh
 */
public class Loader {
    private JFrame mainFrame;
    private JPanel pnlBar, pnlMain;
    private JLabel lblPercantage, lblImage;
    private JProgressBar progressBar;

    public Loader() {
        mainFrame = new JFrame("Day-Care System");
        pnlMain = new JPanel();
        pnlBar = new JPanel();

        lblPercantage = new JLabel();
        lblImage = new JLabel(new ImageIcon("C:\\Users\\karlh\\OneDrive\\Documents\\GitHub\\DaycareClient\\DC.png"));

        progressBar = new JProgressBar(0,100);

        mainFrame.pack();
        mainFrame.setVisible(true);

        mainFrame.setLocationRelativeTo(null);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(new Dimension(450, 450));
    }

    private void loading() {
        progressBar.setForeground(new Color(51, 204, 5));
        progressBar.setPreferredSize(new Dimension(300, 70));
        lblImage.setPreferredSize(new Dimension(300, 380));

        pnlBar.setBackground(Color.BLACK);
        pnlBar.add(progressBar);

        //pnlMain.add(lblImage);
        pnlMain.add(lblPercantage, BorderLayout.SOUTH);

        mainFrame.add(pnlMain);
        mainFrame.add(pnlBar, BorderLayout.SOUTH);

        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(40);
                this.progressBar.setValue(i);
                this.lblPercantage.setText(i + "%");

            }
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

        LoginView login = new LoginView();
        login.setGUI();
        mainFrame.dispose();
    }

    public static void main(String[] args) {
        new Loader().loading();
    }

}
