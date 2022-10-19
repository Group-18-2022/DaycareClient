package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.DayCareVenue;
import za.ac.cput.domain.Incidents;
import za.ac.cput.factory.DayCareVenueFactory;
import za.ac.cput.factory.IncidentsFactory;
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

public class IncidentUI {
    private static OkHttpClient client = new OkHttpClient();
    private JLabel incidentIdLbl, dateLbl, locationLbl, descriptionLbl, teacherIdLbl, childIdLbl;
    private JTextField incidentIdField, dateField, locationField, descriptionField, teacherIdField, childIdField;
    private JPanel incidentIdPanel, datePanel, locationPanel, descriptionPanel, teacherIdPanel, childIdPanel;
    private JTable incidentTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel incidentTablePanel;
    private JPanel createIncidentPanel;

    JPanel crud;

    private CrudPanel crudPanel;

    private Incidents inc;
    Incidents incdnt;


    public IncidentUI() {
        incidentIdLbl = new JLabel("Incident Id");
        dateLbl = new JLabel("Date");
        locationLbl = new JLabel("Location");
        descriptionLbl = new JLabel("Description");
        teacherIdLbl = new JLabel("Teacher Id");
        childIdLbl = new JLabel("Child Id");

        incidentIdField = new JTextField();
        dateField = new JTextField();
        locationField = new JTextField();
        descriptionField = new JTextField();
        teacherIdField = new JTextField();
        childIdField = new JTextField();


        incidentIdPanel = new JPanel();
        datePanel = new JPanel();
        locationPanel = new JPanel();
        descriptionPanel = new JPanel();
        teacherIdPanel = new JPanel();
        childIdPanel = new JPanel();

        incidentTablePanel = new JPanel();
        createIncidentPanel = new JPanel();

        incidentTable = new JTable();
        String columns[] = {"Incidents Id", "Date", "Location", "Description", "Teacher Id", "Child Id"};
        tableModel = new DefaultTableModel(columns, 0);
        newPane = new JScrollPane();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();
    }

    public JPanel incidentSetUp() {
        createTable();
        mouseListenerMethod();

        incidentIdPanel.setLayout(new GridLayout(1, 2));
        datePanel.setLayout(new GridLayout(1, 2));
        locationPanel.setLayout(new GridLayout(1, 2));
        descriptionPanel.setLayout(new GridLayout(1, 2));
        teacherIdPanel.setLayout(new GridLayout(1, 2));
        childIdPanel.setLayout(new GridLayout(1, 2));

        createIncidentPanel.setLayout(new GridLayout(10, 0));
        createIncidentPanel.add(incidentIdPanel);
        createIncidentPanel.add(datePanel);
        createIncidentPanel.add(locationPanel);
        createIncidentPanel.add(descriptionPanel);
        createIncidentPanel.add(teacherIdPanel);
        createIncidentPanel.add(childIdPanel);

        incidentIdPanel.add(incidentIdLbl);
        incidentIdPanel.add(incidentIdField);
        datePanel.add(dateLbl);
        datePanel.add(dateField);
        locationPanel.add(locationLbl);
        locationPanel.add(locationField);
        descriptionPanel.add(descriptionLbl);
        descriptionPanel.add(descriptionField);
        teacherIdPanel.add(teacherIdLbl);
        teacherIdPanel.add(teacherIdField);
        childIdPanel.add(childIdLbl);
        childIdPanel.add(childIdField);

        incidentTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createIncidentPanel, incidentTablePanel);
        return crud;
    }


    public static java.util.List<Object> getAll(String allUrl) //pass the url from the Controller class for findAll/getAll
    {
        java.util.List<Object> objectList = new ArrayList<>();
        try {
            String URL = allUrl;

            Request request = new Request.Builder()
                    .url(URL)
                    .build();
            Response response = client.newCall(request).execute();
            String responseBod = response.body().string();
            JSONArray identities = new JSONArray(responseBod);

            for (int i = 0; i < identities.length(); i++) {
                JSONObject identity = identities.getJSONObject(i);
                Gson g = new Gson();
                Object o = g.fromJson(identity.toString(), Incidents.class);
                objectList.add(o);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return objectList;
    }

    public void createTable() {
        try {
            incidentTable = new JTable(tableModel);
            newPane.setViewportView(incidentTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List incList = getAll("http://localhost:8080/api/v1/day-care/incidents/all");
            java.util.List<Incidents> incList1 = incList;

            for (int i = 0; i < incList.size(); i++) {
                Object[] objs = {incList1.get(i).getIncidentID(),
                        incList1.get(i).getDate(), incList1.get(i).getLocation(),
                        incList1.get(i).getInjuryDescription(), incList1.get(i).getTeacherID(), incList1.get(i).getChildID()};
                tableModel.addRow(objs);
            }
        } catch (Exception E) {
            System.out.println(E);
        }
    }

    public Incidents getTableItem(int rowNum) {
        Incidents newInc = null;
        try {
            Object obj = getAll("http://localhost:8080/api/v1/day-care/incidents/all");
            java.util.List list = (List<Incidents>) obj;
            newInc = (Incidents) list.get(rowNum);
        } catch (Exception e) {
            System.out.println(e);
        }
        return newInc;
    }

    public void createTableModelListener(TableModelListener tml) {
        tableModel.addTableModelListener(tml);
    }

    public void modelListenerMethod() {
        createTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                tableModel.addTableModelListener(this);
                int firstRow = e.getFirstRow();

                String incidentId = "";
                String date = "";
                String location = "";
                String description = "";
                String teacherId = "";
                String childId = "";

                for (int j = 0; j < incidentTable.getColumnCount(); j++) {
                    if (j == 0) {
                        incidentId = incidentTable.getModel().getValueAt(firstRow, j).toString();
                    }
                    if (j == 1) {
                        date = incidentTable.getModel().getValueAt(firstRow, j).toString();
                    }
                    if (j == 2) {
                        location = incidentTable.getModel().getValueAt(firstRow, j).toString();
                    }
                    if (j == 3) {
                        description = incidentTable.getModel().getValueAt(firstRow, j).toString();
                    }
                    if (j == 4) {
                        teacherId = incidentTable.getModel().getValueAt(firstRow, j).toString();
                    }
                    if (j == 5) {
                        childId = incidentTable.getModel().getValueAt(firstRow, j).toString();
                    }
                }
                incdnt = IncidentsFactory.build(incidentId, teacherId, childId, date, location, description);
            }
        });
        System.out.println(incdnt);
    }

    public void createMouseListener(MouseListener ml) {
        incidentTable.addMouseListener(ml);
    }

    public void mouseListenerMethod() {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                inc = getTableItem(incidentTable.getSelectedRow());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }

    public void actionListenerMethod() {
        crudPanel.createBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equalsIgnoreCase("Create record")) {

                    String incidentId = incidentIdField.getText();
                    String date = dateField.getText();
                    String location = locationField.getText();
                    String description = descriptionField.getText();
                    String teacherId = teacherIdField.getText();
                    String childId = childIdField.getText();

                    Incidents dcvn = IncidentsFactory.build(incidentId, teacherId, childId, date, location, description);
                    new ConsoleApp().post(dcvn, "http://localhost:8080/api/v1/day-care/incidents/save");

                    String columns[] = {"Incidents Id", "Date", "Location", "Description", "Teacher Id", "Child Id"};
                    tableModel = new DefaultTableModel(columns, 0);

                    createTable();
                    mouseListenerMethod();
                    modelListenerMethod();

                    JOptionPane.showMessageDialog(null, "Record was successfully Created!");
                }

                if (e.getActionCommand().equalsIgnoreCase("delete record")) {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Swing Tester",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        new ConsoleApp().delete(inc.getIncidentID(), "http://localhost:8080/api/v1/day-care/incidents/delete/");

                        String columns[] = {"Incidents Id", "Date", "Location", "Description", "Teacher Id", "Child Id"};
                        tableModel = new DefaultTableModel(columns, 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Deleted!");
                    }
                }

                if (e.getActionCommand().equalsIgnoreCase("Update")) {
                    int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to update this record?", "Swing Tester",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if (result == JOptionPane.YES_OPTION) {
                        System.out.println(incdnt);
                        new ConsoleApp().post(incdnt, "http://localhost:8080/api/v1/day-care/incidents/save/");

                        String columns[] = {"Incidents Id", "Date", "Location", "Description", "Teacher Id", "Child Id"};
                        tableModel = new DefaultTableModel(columns, 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    } else {
                        String columns[] = {"Incidents Id", "Date", "Location", "Description", "Teacher Id", "Child Id"};
                        tableModel = new DefaultTableModel(columns, 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();
                    }
                }

                if (e.getActionCommand().equalsIgnoreCase("back home")) {
                    System.out.println("Go Back Home");
                }
                if (e.getActionCommand().equalsIgnoreCase("logout")) {
                    System.out.println("Log yourself out");

                }
            }
        });
    }
}
