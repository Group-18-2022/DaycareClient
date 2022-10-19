package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.EmergencyServiceProvider;
import za.ac.cput.factory.ESPFactory;
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

public class EmergencyServiceProviderUI
{
    private static OkHttpClient client = new OkHttpClient();
    private JLabel serviceIdLbl, serviceNameLbl, serviceTypeLbl, phoneNumLbl;
    private JTextField serviceIdField, serviceNameField, serviceTypeField, phoneNumField;
    private JPanel serviceIdPanel, serviceNamePanel, serviceTypePanel, phoneNumPanel;

    private JTable espTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel espTablePanel;
    private JPanel createEspPanel;

    JPanel crud;

    private CrudPanel crudPanel;

    private EmergencyServiceProvider es;
    EmergencyServiceProvider emspr;


    public EmergencyServiceProviderUI()
    {
        serviceIdLbl= new JLabel("Service ID");
        serviceNameLbl= new JLabel("Service Name");
        serviceTypeLbl= new JLabel("Service Type");
        phoneNumLbl= new JLabel("Phone Number");

        serviceIdField = new JTextField();
        serviceNameField = new JTextField();
        serviceTypeField  = new JTextField();
        phoneNumField  = new JTextField();

        serviceIdPanel= new JPanel();
        serviceNamePanel = new JPanel();
        serviceTypePanel = new JPanel();
        phoneNumPanel = new JPanel();

        espTablePanel = new JPanel();
        createEspPanel = new JPanel();

        espTable = new JTable();
        String columns [] = {"Service Id", "Service Name", "Service Type", "Phone Number"};
        tableModel = new DefaultTableModel(columns , 0);
        newPane = new JScrollPane();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();
    }
    public JPanel espSetUp()
    {
        createTable();
        mouseListenerMethod();

        serviceIdPanel.setLayout(new GridLayout(1, 2));
        serviceNamePanel.setLayout(new GridLayout(1, 2));
        serviceTypePanel.setLayout(new GridLayout(1, 2));
        phoneNumPanel.setLayout(new GridLayout(1, 2));

        createEspPanel.setLayout(new GridLayout(10, 0));
        createEspPanel.add(serviceIdPanel);
        createEspPanel.add(serviceNamePanel);
        createEspPanel.add(serviceTypePanel);
        createEspPanel.add(phoneNumPanel);

        serviceIdPanel.add(serviceIdLbl);
        serviceIdPanel.add(serviceIdField);
        serviceNamePanel.add(serviceNameLbl);
        serviceNamePanel.add(serviceNameField);
        serviceTypePanel.add(serviceTypeLbl);
        serviceTypePanel.add(serviceTypeField);
        phoneNumPanel.add(phoneNumLbl);
        phoneNumPanel.add(phoneNumField);

        espTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createEspPanel, espTablePanel);
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
                Object o = g.fromJson(identity.toString(), EmergencyServiceProvider.class);
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
            espTable = new JTable(tableModel);
            newPane.setViewportView(espTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List espList = getAll("http://localhost:8080/api/v1/day-care/esp/all");
            java.util.List<EmergencyServiceProvider> espList1 =  espList;

            for(int i = 0; i < espList.size(); i++ )
            {
                Object[] objs = {espList1.get(i).getServiceID(), espList1.get(i).getServiceName(),
                        espList1.get(i).getType(), espList1.get(i).getPhoneNum()};
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public EmergencyServiceProvider getTableItem(int rowNum)
    {
        EmergencyServiceProvider newEsp = null;
        try
        {
            Object obj  =  getAll("http://localhost:8080/api/v1/day-care/esp/all");
            java.util.List list = (List<EmergencyServiceProvider>)obj;
            newEsp = (EmergencyServiceProvider) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newEsp;
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

                String serviceId = "";
                String serviceName = "";
                String serviceType = "";
                String phoneNum = "";

                for(int j = 0; j < espTable.getColumnCount(); j++)
                {
                    if(j==0)
                    {
                        serviceId =  espTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==1)
                    {
                        serviceName = espTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==2)
                    {
                        serviceType =  espTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==3)
                    {
                        phoneNum = espTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                }
                emspr = ESPFactory.createESP(serviceId, serviceName, serviceType, phoneNum);
            }});

        System.out.println(emspr);
    }

    public void createMouseListener(MouseListener ml)
    {
        espTable.addMouseListener(ml);
    }

    public void mouseListenerMethod()
    {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                es = getTableItem(espTable.getSelectedRow());
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
                        String serviceId = serviceIdField.getText();
                        String serviceName = serviceNameField.getText();
                        String serviceType = serviceTypeField.getText();
                        String phoneNum = phoneNumField.getText();

                        EmergencyServiceProvider  espr = ESPFactory.createESP(serviceId, serviceName, serviceType, phoneNum);
                        new ConsoleApp().post(espr, "http://localhost:8080/api/v1/day-care/esp/save");

                        String columns [] = {"Service Id", "Service Name", "Service Type", "Phone Number"};
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
                        new ConsoleApp().delete(es.getServiceID(), "http://localhost:8080/api/v1/day-care/esp/delete/");

                        String columns [] = {"Service Id", "Service Name", "Service Type", "Phone Number"};
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
                        System.out.println(emspr);
                        new ConsoleApp().post(emspr, "http://localhost:8080/api/v1/day-care/esp/save/");

                        String columns [] = {"Service Id", "Service Name", "Service Type", "Phone Number"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns [] = {"Service Id", "Service Name", "Service Type", "Phone Number"};
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
