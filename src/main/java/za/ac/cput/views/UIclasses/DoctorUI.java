package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.Doctor;
import za.ac.cput.factory.DoctorFactory;
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

public class DoctorUI
{

    private static OkHttpClient client = new OkHttpClient();
    private JLabel doctorIDLBL, practiceNameLBL, firstNameLBL, lastNameLBL, phoneNumberLBL;
    private JTextField doctorIDField, practiceNameField, firstNameField, lastNameField, phoneNumberField;
    private JPanel doctorIDPanel, practiceNamePanel, firstNamePanel, lastNamePanel, phoneNumberPanel;
    private JTable doctorTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel doctorTablePanel;
    private JPanel createDoctorPanel;

    JPanel crud;

    private CrudPanel crudPanel;

    private Doctor doc;
    Doctor dctr;


    public DoctorUI()
    {
        doctorIDLBL = new JLabel("Doctor ID");
        practiceNameLBL = new JLabel("Practice Name");
        firstNameLBL = new JLabel("First Name");
        lastNameLBL = new JLabel("Last Name");
        phoneNumberLBL= new JLabel("Phone Number");

        doctorIDField = new JTextField();
        practiceNameField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        phoneNumberField = new JTextField();

        doctorIDPanel = new JPanel();
        practiceNamePanel = new JPanel();
        firstNamePanel = new JPanel();
        lastNamePanel = new JPanel();
        phoneNumberPanel = new JPanel();

        doctorTablePanel = new JPanel();
        createDoctorPanel = new JPanel();

        doctorTable = new JTable();
        String columns [] = {"Doctor ID","Practice Name", "First Name", "Last Name", "Phone Number"};
        tableModel = new DefaultTableModel(columns , 0);
        newPane = new JScrollPane();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();
    }
    public JPanel venueSetUp()
    {
        createTable();
        mouseListenerMethod();

        doctorIDPanel.setLayout(new GridLayout(1, 2));
        practiceNamePanel.setLayout(new GridLayout(1, 2));
        firstNamePanel.setLayout(new GridLayout(1, 2));
        lastNamePanel.setLayout(new GridLayout(1, 2));
        phoneNumberPanel.setLayout(new GridLayout(1, 2));

        createDoctorPanel.setLayout(new GridLayout(10, 0));
        createDoctorPanel.add(doctorIDPanel);
        createDoctorPanel.add(practiceNamePanel);
        createDoctorPanel.add(firstNamePanel);
        createDoctorPanel.add(lastNamePanel);
        createDoctorPanel.add(phoneNumberPanel);

        doctorIDPanel.add(doctorIDLBL);
        doctorIDPanel.add(doctorIDField);
        practiceNamePanel.add(practiceNameLBL);
        practiceNamePanel.add(practiceNameField);
        firstNamePanel.add(firstNameLBL);
        firstNamePanel.add(firstNameField);
        lastNamePanel.add(lastNameLBL);
        lastNamePanel.add(lastNameField);
        phoneNumberPanel.add(phoneNumberLBL);
        phoneNumberPanel.add(phoneNumberField);

        doctorTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createDoctorPanel, doctorTablePanel);
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
                Object o = g.fromJson(identity.toString(), Doctor.class);
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
            doctorTable = new JTable(tableModel);
            newPane.setViewportView(doctorTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List docList = getAll("http://localhost:8080/api/v1/day-care/doctor/all");
            java.util.List<Doctor> docList1 =  docList;

            for(int i = 0; i < docList.size(); i++ )
            {
                Object[] objs = {docList1.get(i).getDoctorID(), docList1.get(i).getPracticeName(), docList1.get(i).getFirstName(),
                        docList1.get(i).getLastName(), docList1.get(i).getPhoneNumber()};
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public Doctor getTableItem(int rowNum)
    {
        Doctor newDoc = null;
        try
        {
            Object obj  =  getAll("http://localhost:8080/api/v1/day-care/doctor/all");
            java.util.List list = (List<Doctor>)obj;
            newDoc = (Doctor) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newDoc;
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

                String doctorID = "";
                String practiceName = "";;
                String firstName = "";;
                String lastName = "";;
                String phoneNumber = "";;

                for(int j = 0; j < doctorTable.getColumnCount(); j++)
                {
                    if(j==0)
                    {
                        doctorID =  doctorTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==1)
                    {
                        practiceName = doctorTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==2)
                    {
                        firstName =  doctorTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==3)
                    {
                        lastName = doctorTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==4)
                    {
                        phoneNumber = doctorTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                }
                dctr = DoctorFactory.buildDoctor(doctorID, practiceName, firstName, lastName, phoneNumber);
            }});

        System.out.println(dctr);
    }

    public void createMouseListener(MouseListener ml)
    {
        doctorTable.addMouseListener(ml);
    }

    public void mouseListenerMethod()
    {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                doc = getTableItem(doctorTable.getSelectedRow());
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

                    String doctorID = doctorIDField.getText();
                    String practiceName = practiceNameField.getText();
                    String firstName = firstNameField.getText();
                    String lastName = lastNameField.getText();
                    String phoneNumber = phoneNumberField.getText();

                    Doctor  dc = DoctorFactory.buildDoctor(doctorID, practiceName, firstName, lastName, phoneNumber);
                    new ConsoleApp().post(dc, "http://localhost:8080/api/v1/day-care/doctor/save");

                    String columns [] = {"Doctor ID","Practice Name", "First Name", "Last Name", "Phone Number"};
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
                        new ConsoleApp().delete(doc.getDoctorID(), "http://localhost:8080/api/v1/day-care/doctor/delete/");

                        String columns [] = {"Doctor ID","Practice Name", "First Name", "Last Name", "Phone Number"};
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
                        System.out.println(dctr);
                        new ConsoleApp().post(dctr, "http://localhost:8080/api/v1/day-care/doctor/save/");

                        String columns [] = {"Doctor ID","Practice Name", "First Name", "Last Name", "Phone Number"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns [] = {"Doctor ID","Practice Name", "First Name", "Last Name", "Phone Number"};
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
