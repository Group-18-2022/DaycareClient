package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Parent;
import za.ac.cput.factory.ParentFactory;
import za.ac.cput.views.consoleapp.ConsoleApp;
import za.ac.cput.views.mainPanels.CrudPanel;

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

public class ParentUI
{
    private static OkHttpClient client = new OkHttpClient();
    private JLabel parentIdLbl, firstNameLbl, lastNameLbl, addressLbl, phoneNumberLbl;
    private JTextField parentIdField, firstNameField, lastNameField, addressField, phoneNumberField;
    private JPanel parentIdPanel, firstNamePanel, lastNamePanel, addressPanel, phoneNumberPanel;

    private JTable parentTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel parentTablePanel;
    private JPanel createParentPanel;

    JPanel crud;

    private CrudPanel crudPanel;

    private Parent pr;
    Parent prnt;


    public ParentUI()
    {
        parentIdLbl = new JLabel("Parent ID");
        firstNameLbl= new JLabel("First Name");
        lastNameLbl= new JLabel("Last Name");
        addressLbl= new JLabel("Address");
        phoneNumberLbl= new JLabel("Phone Number");

        parentIdField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        addressField = new JTextField();
        phoneNumberField = new JTextField();

        parentIdPanel = new JPanel();
        firstNamePanel = new JPanel();
        lastNamePanel = new JPanel();
        addressPanel = new JPanel();
        phoneNumberPanel = new JPanel();

        parentTable = new JTable();
        String columns [] = {"Parent Id", "First Name", "Last Name", "Address", "Phone Number"};
        tableModel = new DefaultTableModel(columns , 0);
        newPane = new JScrollPane();

        parentTablePanel = new JPanel();
        createParentPanel = new JPanel();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();
    }
    public JPanel parentSetUp()
    {
        createTable();
        mouseListenerMethod();

        parentIdPanel.setLayout(new GridLayout(1, 2));
        firstNamePanel.setLayout(new GridLayout(1, 2));
        lastNamePanel.setLayout(new GridLayout(1, 2));
        addressPanel.setLayout(new GridLayout(1, 2));
        phoneNumberPanel.setLayout(new GridLayout(1, 2));

        createParentPanel.setLayout(new GridLayout(10, 0));
        createParentPanel.add(parentIdPanel);
        createParentPanel.add(firstNamePanel);
        createParentPanel.add(lastNamePanel);
        createParentPanel.add(addressPanel);
        createParentPanel.add(phoneNumberPanel);

        parentIdPanel.add(parentIdLbl);
        parentIdPanel.add(parentIdField);
        firstNamePanel.add(firstNameLbl);
        firstNamePanel.add(firstNameField);
        lastNamePanel.add(lastNameLbl);
        lastNamePanel.add(lastNameField);
        addressPanel.add(addressLbl);
        addressPanel.add(addressField);
        phoneNumberPanel.add(phoneNumberLbl);
        phoneNumberPanel.add(phoneNumberField);

        parentTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createParentPanel, parentTablePanel);
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
                Object o = g.fromJson(identity.toString(), Parent.class);
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
            parentTable = new JTable(tableModel);
            newPane.setViewportView(parentTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List parentList = getAll("http://localhost:8080/api/v1/day-care/parent/all");
            java.util.List<Parent> parentList1 =  parentList;

            for(int i = 0; i < parentList.size(); i++ )
            {

                Object[] objs = {parentList1.get(i).getParentID(), parentList1.get(i).getFirstName(),
                        parentList1.get(i).getLastName(), parentList1.get(i).getAddress(), parentList1.get(i).getPhoneNumber()};
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public Parent getTableItem(int rowNum)
    {
        Parent newParent = null;
        try
        {
            Object obj  =  getAll("http://localhost:8080/api/v1/day-care/parent/all");
            java.util.List list = (List<Parent>)obj;
            newParent = (Parent) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newParent;
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

                String parentId = "";
                String firstName = "";
                String lastName = "";
                String address = "";
                String phoneNumber = "";

                for(int j = 0; j < parentTable.getColumnCount(); j++)
                {
                    if(j==0)
                    {
                        parentId =  parentTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==1)
                    {
                        firstName = parentTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==2)
                    {
                        lastName =  parentTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==3)
                    {
                        address = parentTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==4)
                    {
                        phoneNumber = parentTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                }
                prnt = ParentFactory.buildParent(parentId, firstName, lastName, address, phoneNumber);
            }});

        System.out.println(prnt);
    }

    public void createMouseListener(MouseListener ml)
    {
        parentTable.addMouseListener(ml);
    }

    public void mouseListenerMethod()
    {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                pr = getTableItem(parentTable.getSelectedRow());
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

    public String validateStrings()
    {
        String stringRegex = "^[a-zA-Z]*$";
        return stringRegex;
    }
    public String validateNumbers()
    {
        String numberRegex = "^[0-9]*$";
        return numberRegex;
    }
    public boolean allValidators()
    {
        boolean validate = true;
/*
        if(     roomNumberField.getText().matches(validateStrings()) ||
                roomNumberField.getText().matches(validateNumbers()) &&
                occupancyField.getText().matches(validateStrings()) ||
                occupancyField.getText().matches(validateNumbers())
            )
        {
            validate = true;

        }

 */
        return validate;
    }


    public void actionListenerMethod()
    {
        crudPanel.createBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(allValidators())
                {
                    if (e.getActionCommand().equalsIgnoreCase("Create record"))
                    {
                        String parentId = parentIdField.getText();
                        String firstName = firstNameField.getText();
                        String lastName = lastNameField.getText();
                        String address = addressField.getText();
                        String phoneNumber = phoneNumberField.getText();

                        Parent  par = ParentFactory.buildParent(parentId, firstName, lastName, address, phoneNumber);
                        new ConsoleApp().post(par, "http://localhost:8080/api/v1/day-care/parent/save");
                        String columns [] = {"Parent Id", "First Name", "Last Name", "Address", "Phone Number"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Created!");
                    }

                }else
                {
                    JOptionPane.showMessageDialog(null, "Enter valid values only");

                }


                if (e.getActionCommand().equalsIgnoreCase("delete record"))
                {
                    int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this record?", "Swing Tester",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if(result == JOptionPane.YES_OPTION)
                    {
                        new ConsoleApp().delete(pr.getParentID(), "http://localhost:8080/api/v1/day-care/parent/delete/");
                        String columns [] = {"Parent Id", "First Name", "Last Name", "Address", "Phone Number"};
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
                        System.out.println(prnt);
                        new ConsoleApp().post(prnt, "http://localhost:8080/api/v1/day-care/parent/save/");

                        String columns [] = {"Parent Id", "First Name", "Last Name", "Address", "Phone Number"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns [] = {"Parent Id", "First Name", "Last Name", "Address", "Phone Number"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();
                    }
                }

                if (e.getActionCommand().equalsIgnoreCase("back home"))
                {
                    System.out.println("Go Back Home");
                }
                if (e.getActionCommand().equalsIgnoreCase("logout"))
                {
                    System.out.println("Log yourself out");

                }
            }
        });
    }
}
