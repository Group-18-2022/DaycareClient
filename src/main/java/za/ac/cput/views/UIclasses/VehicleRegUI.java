package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.StartApp;
import za.ac.cput.domain.ClassRoom;
import za.ac.cput.domain.VehicleRegDetails;
import za.ac.cput.factory.VehicleRegDetailsFactory;
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

public class VehicleRegUI
{
    private static OkHttpClient client = new OkHttpClient();
    private JLabel vehicleIdLbl, brandLbl, modelLbl, yearLbl, regDateLbl;
    private JTextField vehicleIdField, brandField, modelField, yearField, regDateField;
    private JPanel vehicleIdPanel, brandPanel, modelPanel, yearPanel, regDatePanel;

    private JTable classRoomTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel vehicleregTablePanel;
    private JPanel createVehicleregPanel;

    JPanel crud;

    private CrudPanel crudPanel;

    private VehicleRegDetails vr;
    VehicleRegDetails vrgd;


    public VehicleRegUI()
    {
        vehicleIdLbl = new JLabel("Vehicle ID");
        brandLbl = new JLabel("Brand");
        modelLbl = new JLabel("Model");
        yearLbl = new JLabel("Year");
        regDateLbl = new JLabel("Registration Date");

        vehicleIdField= new JTextField();
        brandField = new JTextField();
        modelField = new JTextField();
        yearField = new JTextField();
        regDateField = new JTextField();

        vehicleIdPanel = new JPanel();
        brandPanel = new JPanel();
        modelPanel = new JPanel();
        yearPanel = new JPanel();
        regDatePanel = new JPanel();

        classRoomTable = new JTable();
        String columns [] = {"Vehicle Id", "Brand", "Model", "Year", "Registration Date"};
        tableModel = new DefaultTableModel(columns , 0);
        newPane = new JScrollPane();

        vehicleregTablePanel = new JPanel();
        createVehicleregPanel = new JPanel();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();
    }
    public JPanel vehicleRegSetUp()
    {
        createTable();
        mouseListenerMethod();

        vehicleIdPanel.setLayout(new GridLayout(1, 2));
        brandPanel.setLayout(new GridLayout(1, 2));
        modelPanel.setLayout(new GridLayout(1, 2));
        yearPanel.setLayout(new GridLayout(1, 2));
        regDatePanel.setLayout(new GridLayout(1, 2));

        createVehicleregPanel.setLayout(new GridLayout(10, 0));
        createVehicleregPanel.add(vehicleIdPanel);
        createVehicleregPanel.add(brandPanel);
        createVehicleregPanel.add(modelPanel);
        createVehicleregPanel.add(yearPanel);
        createVehicleregPanel.add(regDatePanel);

        vehicleIdPanel.add(vehicleIdLbl);
        vehicleIdPanel.add(vehicleIdField);
        brandPanel.add(brandLbl);
        brandPanel.add(brandField);
        modelPanel.add(modelLbl);
        modelPanel.add(modelField);
        yearPanel.add(yearLbl);
        yearPanel.add(yearField);
        regDatePanel.add(regDateLbl);
        regDatePanel.add(regDateField);

        vehicleregTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createVehicleregPanel, vehicleregTablePanel);
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
                Object o = g.fromJson(identity.toString(), VehicleRegDetails.class);
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
            classRoomTable = new JTable(tableModel);
            newPane.setViewportView(classRoomTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List vehicleRegList = getAll("http://localhost:8080/api/v1/day-care/vehicleregdetails/all");
            java.util.List<VehicleRegDetails> vehicleRegList1 =  vehicleRegList;

            for(int i = 0; i < vehicleRegList.size(); i++ )
            {
                Object[] objs = {vehicleRegList1.get(i).getVehicleId(),
                        vehicleRegList1.get(i).getBrand(), vehicleRegList1.get(i).getModel(),
                        vehicleRegList1.get(i).getYear(), vehicleRegList1.get(i).getRegDate()};
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public VehicleRegDetails getTableItem(int rowNum)
    {
        VehicleRegDetails newRegDetails = null;
        try
        {
            Object obj  =  getAll("http://localhost:8080/api/v1/day-care/vehicleregdetails/all");
            java.util.List list = (List<VehicleRegDetails>)obj;
            newRegDetails = (VehicleRegDetails) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newRegDetails;
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

                String vehicleId= "";
                String brand= "";
                String model= "";
                String year= "";
                String regDate= "";

                for(int j = 0; j < classRoomTable.getColumnCount();j++)
                {
                    if(j==0)
                    {
                        vehicleId =  classRoomTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==1)
                    {
                        brand = classRoomTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==2)
                    {
                        model = classRoomTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==3)
                    {
                        year = classRoomTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==4)
                    {
                        regDate = classRoomTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                }
                vrgd = VehicleRegDetailsFactory.createVehicleRegDetails(vehicleId, brand, model, year, regDate);
            }});

        System.out.println(vrgd);
    }

    public void createMouseListener(MouseListener ml)
    {
        classRoomTable.addMouseListener(ml);
    }

    public void mouseListenerMethod()
    {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                vr = getTableItem(classRoomTable.getSelectedRow());
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
                    String vehicleId= vehicleIdField.getText();
                    String brand= brandField.getText();
                    String model= modelField.getText();
                    String year= yearField.getText();
                    String regDate= regDateField.getText();

                    VehicleRegDetails  cr = VehicleRegDetailsFactory.createVehicleRegDetails(vehicleId, brand, model, year, regDate);
                    new ConsoleApp().post(cr, "http://localhost:8080/api/v1/day-care/vehicleregdetails/save");
                    String columns [] = {"Vehicle Id", "Brand", "Model", "Year", "Registration Date"};
                    tableModel = new DefaultTableModel(columns , 0);

                    createTable();
                    mouseListenerMethod();
                    modelListenerMethod();

                    JOptionPane.showMessageDialog(null, "Record was successfully Created!");
                }


                if (e.getActionCommand().equalsIgnoreCase("delete"))
                {
                    int result = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this record?", "Swing Tester",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE);

                    if(result == JOptionPane.YES_OPTION)
                    {
                        new ConsoleApp().delete(vr.getVehicleId(), "http://localhost:8080/api/v1/day-care/vehicleregdetails/delete/");
                        String columns[] = {"Vehicle Id", "Brand", "Model", "Year", "Registration Date"};
                        tableModel = new DefaultTableModel(columns, 0);

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
                        System.out.println(vrgd);
                        new ConsoleApp().post(vrgd, "http://localhost:8080/api/v1/day-care/vehicleregdetails/save/");

                        String columns[] = {"Vehicle Id", "Brand", "Model", "Year", "Registration Date"};
                        tableModel = new DefaultTableModel(columns, 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns[] = {"Vehicle Id", "Brand", "Model", "Year", "Registration Date"};
                        tableModel = new DefaultTableModel(columns, 0);

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
