package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Principal;
import za.ac.cput.factory.PrincipalFactory;
import za.ac.cput.views.StartApp;
import za.ac.cput.views.consoleapp.ConsoleApp;
import za.ac.cput.views.mainPanels.CrudPanel;
import za.ac.cput.views.mainPanels.PrincipalPanel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PrincipalUI
{
    private static OkHttpClient client = new OkHttpClient();
    private JLabel principalIdLbl, firstNameLbl, lastNameLbl, dobLbl;
    private JTextField principalIdField, firstNameField, lastNameField, dobField;
    private JPanel principalIdPanel, firstNamePanel, lastNamePanel, dobPanel;

    private JTable principalTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel principalTablePanel;
    private JPanel createPrincipalPanel;
    JPanel crud;

    private CrudPanel crudPanel;
    private Principal pr;
    Principal prncpl;


    public PrincipalUI()
    {
        principalIdLbl = new JLabel("Principal ID");
        firstNameLbl = new JLabel("First Name");
        lastNameLbl = new JLabel("Last Name");
        dobLbl = new JLabel("Date of Birth");

        principalIdField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        dobField = new JTextField();

        principalIdPanel = new JPanel();
        firstNamePanel = new JPanel();
        lastNamePanel = new JPanel();
        dobPanel = new JPanel();

        principalTablePanel = new JPanel();
        createPrincipalPanel = new JPanel();

        principalTable = new JTable();
        String columns [] = {"Principal Id", "First Name", "Last Name", "Date of Birth"};
        tableModel = new DefaultTableModel(columns , 0);
        newPane = new JScrollPane();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();
    }
    public JPanel principalSetUp()
    {
        createTable();
        mouseListenerMethod();

        principalIdPanel.setLayout(new GridLayout(1, 2));
        firstNamePanel.setLayout(new GridLayout(1, 2));
        lastNamePanel.setLayout(new GridLayout(1, 2));
        dobPanel.setLayout(new GridLayout(1, 2));

        createPrincipalPanel.setLayout(new GridLayout(10, 0));
        createPrincipalPanel.add(principalIdPanel);
        createPrincipalPanel.add(firstNamePanel);
        createPrincipalPanel.add(lastNamePanel);
        createPrincipalPanel.add(dobPanel);

        principalIdPanel.add(principalIdLbl);
        principalIdPanel.add(principalIdField);
        firstNamePanel.add(firstNameLbl);
        firstNamePanel.add(firstNameField);
        lastNamePanel.add(lastNameLbl);
        lastNamePanel.add(lastNameField);
        dobPanel.add(dobLbl);
        dobPanel.add(dobField);

        principalTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createPrincipalPanel, principalTablePanel);
        return crud;
    }


    public static java.util.List<Object> getAll(String allUrl) //pass the url from the Controller class for findAll/getAll
    {
        java.util.List<Object> objectList = new ArrayList<>();
        try
        {
            String URL = allUrl;

            Request request = new Request.Builder()
                    .url(URL)
                    .build();
            Response response = client.newCall(request).execute();
            String responseBod = response.body().string();
            JSONArray identities = new JSONArray(responseBod);

            for (int i =0; i<identities.length(); i++)
            {
                JSONObject identity = identities.getJSONObject(i);
                Gson g = new Gson();
                Object o = g.fromJson(identity.toString(), Principal.class);
                objectList.add(o);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return objectList;
    }
    public void createTable()
    {
        try
        {
            principalTable = new JTable(tableModel);
            newPane.setViewportView(principalTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List principalList = getAll("http://localhost:8080/api/v1/day-care/principal/all");
            java.util.List<Principal> principalList1 =  principalList;

            for(int i = 0; i < principalList.size(); i++ )
            {
                Object[] objs = {principalList1.get(i).getPrincipalID(), principalList1.get(i).getFirstName(), principalList1.get(i).getLastName(), principalList1.get(i).getDob()};
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public Principal getTableItem(int rowNum)
    {
        Principal newPrincipal = null;
        try
        {
            Object obj  =  getAll("http://localhost:8080/api/v1/day-care/principal/all");
            java.util.List list = (List<Principal>)obj;
            newPrincipal = (Principal) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newPrincipal;
    }

    public void createTableModelListener(TableModelListener tml)
    {
        tableModel.addTableModelListener(tml);
    }

    public void modelListenerMethod()
    {
        createTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent e)
            {
                tableModel.addTableModelListener(this);
                int firstRow = e.getFirstRow();

                 String principalId = "";
                 String firstName = "";
                 String lastName = "";
                 String dob = "";

                for(int j = 0; j < principalTable.getColumnCount(); j++)
                {
                    if(j==0)
                    {
                        principalId =  principalTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==1)
                    {
                        firstName = principalTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==2)
                    {
                        lastName = principalTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==3)
                    {
                        dob = principalTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                }
                prncpl = PrincipalFactory.createPrincipal(principalId, firstName, lastName, dob);
            }});

        System.out.println(prncpl);
    }

    public void createMouseListener(MouseListener ml)
    {
        principalTable.addMouseListener(ml);
    }

    public void mouseListenerMethod()
    {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                pr = getTableItem(principalTable.getSelectedRow());
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
    }

    public void actionListenerMethod()
    {
        crudPanel.createBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                    if (e.getActionCommand().equalsIgnoreCase("Create record"))
                    {
                        String principalId = principalIdField.getText();
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        String dob = dobField.getText();

                        Principal  pr = PrincipalFactory.createPrincipal(principalId, firstName, lastName, dob);
                        new ConsoleApp().post(pr, "http://localhost:8080/api/v1/day-care/principal/save");
                        String columns [] = {"Principal Id", "First Name", "Last Name", "Date of Birth"};
                                tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Created!");
                    }


                if (e.getActionCommand().equalsIgnoreCase("delete record"))
                {
                    int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this record?", "Swing Tester",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if(result == JOptionPane.YES_OPTION)
                    {
                        new ConsoleApp().delete(pr.getPrincipalID(), "http://localhost:8080/api/v1/day-care/principal/delete/");
                        String columns [] = {"Principal Id", "First Name", "Last Name", "Date of Birth"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Deleted!");
                    }
                }

                if (e.getActionCommand().equalsIgnoreCase("Update"))
                {
                    int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to update this record?", "Swing Tester",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if(result == JOptionPane.YES_OPTION)
                    {
                        System.out.println(prncpl);
                        new ConsoleApp().post(prncpl, "http://localhost:8080/api/v1/day-care/principal/save/");

                        String columns [] = {"Principal Id", "First Name", "Last Name", "Date of Birth"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns [] = {"Principal Id", "First Name", "Last Name", "Date of Birth"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();
                    }
                }

                if (e.getActionCommand().equalsIgnoreCase("back home"))
                {
                    crud.removeAll();

                    PrincipalPanel pp = new PrincipalPanel();
                    crud.add(pp.principalGuiSetUp());

                    crud.revalidate();
                    crud.repaint();
                }
                if (e.getActionCommand().equalsIgnoreCase("logout"))
                {
                    StartApp.killMain();
                    StartApp newLogin = new StartApp();
                    newLogin.loginSetUp();
                }
            }
        });
    }
}
