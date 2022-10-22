package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Child;

import za.ac.cput.factory.ChildFactory;

import za.ac.cput.StartApp;
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

public class ChildUI
{

    private static OkHttpClient client = new OkHttpClient();
    private JLabel childIdLbl, firstNameLbl, lastNameLbl, addressLbl,
            dobLbl, genderlbl;
    private JTextField childIdField, firstNameField, lastNameField, addressField,
            dobField, genderField;
    private JPanel childIdPanel, firstNamePanel, lastNamePanel, addressPanel,
            dobPanel, genderPanel;

    private JTable childTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel childTablePanel;
    private JPanel createChildPanel;
    JPanel crud;

    private CrudPanel crudPanel;

    private Child child1;
    Child child;

    public ChildUI()
    {
        childIdLbl = new JLabel("Child Id");
        firstNameLbl = new JLabel("First Name");
        lastNameLbl = new JLabel("Last Name");
        addressLbl = new JLabel("Address");
        dobLbl = new JLabel("Date of Birth");
        genderlbl = new JLabel("Gender");

        childIdField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        addressField = new JTextField();
        dobField = new JTextField();
        genderField = new JTextField();


        childTable = new JTable();
        String columns [] = {"Child Id", "First Name", "Last Name",
                "Address", "DoB", "Gender"};
        tableModel = new DefaultTableModel(columns , 0);
        newPane = new JScrollPane();

        childIdPanel = new JPanel();
        firstNamePanel = new JPanel();
        lastNamePanel = new JPanel();
        addressPanel = new JPanel();
        dobPanel = new JPanel();
        genderPanel= new JPanel();

        childTablePanel = new JPanel();
        createChildPanel = new JPanel();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();

    }
    public JPanel childSetUp()
    {
        createTable();
        mouseListenerMethod();

        childIdPanel.setLayout(new GridLayout(1, 2));
        firstNamePanel.setLayout(new GridLayout(1, 2));
        lastNamePanel.setLayout(new GridLayout(1, 2));
        addressPanel.setLayout(new GridLayout(1, 2));
        dobPanel.setLayout(new GridLayout(1, 2));
        genderPanel.setLayout(new GridLayout(1, 2));

        createChildPanel.setLayout(new GridLayout(10, 0));
        createChildPanel.add(childIdPanel);
        createChildPanel.add(firstNamePanel);
        createChildPanel.add(lastNamePanel);
        createChildPanel.add(addressPanel);
        createChildPanel.add(dobPanel);
        createChildPanel.add(genderPanel);

        childIdPanel.add(childIdLbl);
        childIdPanel.add(childIdField);
        firstNamePanel.add(firstNameLbl);
        firstNamePanel.add(firstNameField);
        lastNamePanel.add(lastNameLbl);
        lastNamePanel.add(lastNameField);
        addressPanel.add(addressLbl);
        addressPanel.add(addressField);
        dobPanel.add(dobLbl);
        dobPanel.add(dobField);
        genderPanel.add(genderlbl);
        genderPanel.add(genderField);

        childTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createChildPanel, childTablePanel);
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
                Object o = g.fromJson(identity.toString(), Child.class);
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
            childTable = new JTable(tableModel);
            newPane.setViewportView(childTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List childList = getAll("http://localhost:8080/api/v1/day-care/child/all");
            java.util.List<Child> childList1 =  childList;

            for(int i = 0; i < childList.size(); i++ )
            {
                Object[] objs = {childList1.get(i).getChildID(),
                        childList1.get(i).getFirstName(), childList1.get(i).getLastName(),
                        childList1.get(i).getAddress(), childList1.get(i).getDob(),
                        childList1.get(i).getGender()
                };
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public Child getTableItem(int rowNum)
    {
        Child newChild = null;
        try
        {
            //Object obj  =  ConsoleApp.getAll("http://localhost:8080/api/v1/day-care/child/all");
            Object obj = getAll("http://localhost:8080/api/v1/day-care/child/all");
            List list = (List<Child>)obj;
            newChild = (Child) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newChild;
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

                String childID = "";
                String firstName = "";
                String lastName = "";
                String address = "";
                String dob = "";
                String gender = "";

                for(int j = 0; j < childTable.getColumnCount(); j++)
                {
                    if(j==0)
                    {
                        childID =  childTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==1)
                    {
                        firstName = childTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==2)
                    {
                        lastName =  childTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==3)
                    {
                        address =  childTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==4)
                    {
                        dob =  childTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==5)
                    {
                        gender =  childTable.getModel().getValueAt(firstRow ,j).toString();
                    }

                }
                child = ChildFactory.createChild(childID, firstName, lastName, address, dob, gender);
            }});
        System.out.println(child);
    }

    public void createMouseListener(MouseListener ml)

    {
        childTable.addMouseListener(ml);
    }

    public void mouseListenerMethod()
    {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                child1 = getTableItem(childTable.getSelectedRow());
                //System.out.println(c.toString());
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

                        String childID = childIdField.getText();
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        String address = addressField.getText();
                        String dob = dobField.getText();
                        String gender = genderField.getText();

                        Child  ch = ChildFactory.createChild(childID, firstName, lastName, address, dob, gender);
                        new ConsoleApp().post(ch, "http://localhost:8080/api/v1/day-care/child/save");
                        String columns [] = {"Child Id", "First Name", "Last Name",
                                "Address", "DoB", "Gender"};
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
                        new ConsoleApp().delete(child1.getChildID(), "http://localhost:8080/api/v1/day-care/child/delete/");

                        String columns [] = {"Child Id", "First Name", "Last Name",
                                "Address", "DoB", "Gender"};
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
                        System.out.println(child);
                        new ConsoleApp().post(child, "http://localhost:8080/api/v1/day-care/child/save/");

                        String columns [] = {"Child Id", "First Name", "Last Name",
                                "Address", "DoB", "Gender"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns [] = {"Child Id", "First Name", "Last Name",
                                "Address", "DoB", "Gender"};
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
