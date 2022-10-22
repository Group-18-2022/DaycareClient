package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.DayCareVenue;
import za.ac.cput.factory.DayCareVenueFactory;
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

public class VenueUI
{
    private static OkHttpClient client = new OkHttpClient();
    private JLabel dayCareNameLbl, addressLbl, phoneNumLbl, principalIdLbl;
    private JTextField dayCareNameField, addressField, phoneNumField, principalIdField;
    private JPanel dayCareNamePanel, addressPanel, phoneNumPanel, principalIdPanel;
    private JTable venueTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel venueTablePanel;
    private JPanel createVenuePanel;

    JPanel crud;

    private CrudPanel crudPanel;

    private DayCareVenue dcv;
    DayCareVenue daycrven;


    public VenueUI()
    {
        dayCareNameLbl = new JLabel("Daycare Name");
        addressLbl = new JLabel("Address");
        phoneNumLbl= new JLabel("Phone Number");
        principalIdLbl = new JLabel("Principal ID");

        dayCareNameField = new JTextField();
        addressField = new JTextField();
        phoneNumField = new JTextField();
        principalIdField = new JTextField();;

        dayCareNamePanel = new JPanel();
        addressPanel  = new JPanel();
        phoneNumPanel  = new JPanel();
        principalIdPanel  = new JPanel();

        venueTablePanel = new JPanel();
        createVenuePanel = new JPanel();

        venueTable = new JTable();
        String columns [] = {"Daycare Name", "Address", "Phone Number", "Principal Id"};
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

        dayCareNamePanel.setLayout(new GridLayout(1, 2));
        addressPanel.setLayout(new GridLayout(1, 2));
        phoneNumPanel.setLayout(new GridLayout(1, 2));
        principalIdPanel.setLayout(new GridLayout(1, 2));

        createVenuePanel.setLayout(new GridLayout(10, 0));
        createVenuePanel.add(dayCareNamePanel);
        createVenuePanel.add(addressPanel);
        createVenuePanel.add(phoneNumPanel);
        createVenuePanel.add(principalIdPanel);

        dayCareNamePanel.add(dayCareNameLbl);
        dayCareNamePanel.add(dayCareNameField);
        addressPanel.add(addressLbl);
        addressPanel.add(addressField);
        phoneNumPanel.add(phoneNumLbl);
        phoneNumPanel.add(phoneNumField);
        principalIdPanel.add(principalIdLbl);
        principalIdPanel.add(principalIdField);

        venueTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createVenuePanel, venueTablePanel);
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
                Object o = g.fromJson(identity.toString(), DayCareVenue.class);
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
            venueTable = new JTable(tableModel);
            newPane.setViewportView(venueTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List venList = getAll("http://localhost:8080/api/v1/day-care/venue/all");
            java.util.List<DayCareVenue> venList1 =  venList;

            for(int i = 0; i < venList.size(); i++ )
            {
                Object[] objs = {venList1.get(i).getDayCareName(), venList1.get(i).getAddress(),
                        venList1.get(i).getPhone(), venList1.get(i).getPricipalId()};
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public DayCareVenue getTableItem(int rowNum)
    {
        DayCareVenue newVen = null;
        try
        {
            Object obj  =  getAll("http://localhost:8080/api/v1/day-care/venue/all");
            java.util.List list = (List<DayCareVenue>)obj;
            newVen = (DayCareVenue) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newVen;
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

                String dayCareName = "";
                String address  = "";
                String phoneNum = "";
                String principalId  = "";

                for(int j = 0; j < venueTable.getColumnCount(); j++)
                {
                    if(j==0)
                    {
                        dayCareName =  venueTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==1)
                    {
                        address = venueTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==2)
                    {
                        phoneNum =  venueTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==3)
                    {
                        principalId = venueTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                }
                daycrven = DayCareVenueFactory.build(dayCareName, address, phoneNum, principalId);
            }});

        System.out.println(daycrven);
    }

    public void createMouseListener(MouseListener ml)
    {
        venueTable.addMouseListener(ml);
    }

    public void mouseListenerMethod()
    {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                dcv = getTableItem(venueTable.getSelectedRow());
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

                    String dayCareName = dayCareNameField.getText();
                    String address  = addressField.getText();
                    String phoneNum = phoneNumField.getText();
                    String principalId  = principalIdField.getText();

                    DayCareVenue  dcvn = DayCareVenueFactory.build(dayCareName, address, phoneNum, principalId);
                    new ConsoleApp().post(dcvn, "http://localhost:8080/api/v1/day-care/venue/save");

                    String columns [] = {"Daycare Name", "Address", "Phone Number", "Principal Id"};
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
                        new ConsoleApp().delete(dcv.getDayCareName(), "http://localhost:8080/api/v1/day-care/venue/delete/");

                        String columns [] = {"Daycare Name", "Address", "Phone Number", "Principal Id"};
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
                        System.out.println(daycrven);
                        new ConsoleApp().post(daycrven, "http://localhost:8080/api/v1/day-care/venue/save/");

                        String columns [] = {"Daycare Name", "Address", "Phone Number", "Principal Id"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns [] = {"Daycare Name", "Address", "Phone Number", "Principal Id"};
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
