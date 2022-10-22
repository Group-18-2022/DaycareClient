package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.ClassGroup;
import za.ac.cput.factory.ClassGroupFactory;
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

public class ClassGroupUI
{
    private static OkHttpClient client = new OkHttpClient();
    private JLabel classIDLBL, numOfRegStudentLBL, isJuniorLBL;
    private JTextField classIDField, numOfRegStudentField, isJuniorField;
    private JPanel classIDPanel, numOfRegStudentPanel, isJuniorPanel;
    private JTable groupTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel groupTablePanel;
    private JPanel createGroupPanel;

    JPanel crud;

    private CrudPanel crudPanel;

    private ClassGroup cgr;
    ClassGroup clsgrp;


    public ClassGroupUI()
    {
        classIDLBL = new JLabel("Class ID");
        numOfRegStudentLBL = new JLabel("Number of Students");
        isJuniorLBL = new JLabel("Is Junior (true/false)");

        classIDField = new JTextField();
        numOfRegStudentField= new JTextField();
        isJuniorField = new JTextField();

        classIDPanel = new JPanel();
        numOfRegStudentPanel= new JPanel();
        isJuniorPanel = new JPanel();

        groupTablePanel = new JPanel();
        createGroupPanel = new JPanel();

        groupTable = new JTable();
        String columns [] = {"Class ID","Number Of Students", "Is Junior"};
        tableModel = new DefaultTableModel(columns , 0);
        newPane = new JScrollPane();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();
    }
    public JPanel groupSetUp()
    {
        createTable();
        mouseListenerMethod();

        classIDPanel.setLayout(new GridLayout(1, 2));
        numOfRegStudentPanel.setLayout(new GridLayout(1, 2));
        isJuniorPanel.setLayout(new GridLayout(1, 2));

        createGroupPanel.setLayout(new GridLayout(10, 0));
        createGroupPanel.add(classIDPanel);
        createGroupPanel.add(numOfRegStudentPanel);
        createGroupPanel.add(isJuniorPanel);

        classIDPanel.add(classIDLBL);
        classIDPanel.add(classIDField);
        numOfRegStudentPanel.add(numOfRegStudentLBL);
        numOfRegStudentPanel.add(numOfRegStudentField);
        isJuniorPanel.add(isJuniorLBL);
        isJuniorPanel.add(isJuniorField);

        groupTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createGroupPanel, groupTablePanel);
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
                Object o = g.fromJson(identity.toString(), ClassGroup.class);
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
            groupTable = new JTable(tableModel);
            newPane.setViewportView(groupTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List grpList = getAll("http://localhost:8080/api/v1/day-care/classgroup/all");
            java.util.List<ClassGroup> grpList1 =  grpList;

            for(int i = 0; i < grpList.size(); i++ )
            {
                Object[] objs = {grpList1.get(i).getClassID(), grpList1.get(i).getNumOfRegStudent(),
                        grpList1.get(i).isJunior()};
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public ClassGroup getTableItem(int rowNum)
    {
        ClassGroup newGrp = null;
        try
        {
            Object obj  =  getAll("http://localhost:8080/api/v1/day-care/classgroup/all");
            java.util.List list = (List<ClassGroup>)obj;
            newGrp = (ClassGroup) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newGrp;
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

                String classID = "";
                int numOfRegStudent= 0;
                String numOfRegStudentStr = "";
                boolean isJunior = false;
                String isJuniorStr = "";

                for(int j = 0; j < groupTable.getColumnCount(); j++)
                {
                    if(j==0)
                    {
                        classID =  groupTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==1)
                    {
                        numOfRegStudentStr = groupTable.getModel().getValueAt(firstRow ,j).toString();
                        numOfRegStudent = Integer.parseInt(numOfRegStudentStr);
                    }
                    if(j==2)
                    {
                        isJuniorStr =  groupTable.getModel().getValueAt(firstRow ,j).toString();
                        isJunior = Boolean.parseBoolean(isJuniorStr);
                    }

                }
                clsgrp = ClassGroupFactory.createClassGroup(classID, numOfRegStudent, isJunior);
            }});

        System.out.println(clsgrp);
    }

    public void createMouseListener(MouseListener ml)
    {
        groupTable.addMouseListener(ml);
    }

    public void mouseListenerMethod()
    {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                cgr = getTableItem(groupTable.getSelectedRow());
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

                    String classID = classIDField.getText();
                    String numOfRegStudentStr = numOfRegStudentField.getText();
                    int numOfRegStudent = Integer.parseInt(numOfRegStudentStr);
                    String isJuniorStr = isJuniorField.getText();
                    boolean isJunior = Boolean.parseBoolean(isJuniorStr);

                    ClassGroup  cg = ClassGroupFactory.createClassGroup(classID, numOfRegStudent, isJunior);
                    new ConsoleApp().post(cg, "http://localhost:8080/api/v1/day-care/classgroup/save");

                    String columns [] = {"Class ID","Number Of Students", "Is Junior"};
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
                        new ConsoleApp().delete(cgr.getClassID(), "http://localhost:8080/api/v1/day-care/classgroup/delete/");

                        String columns [] = {"Class ID","Number Of Students", "Is Junior"};
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
                        System.out.println(clsgrp);
                        new ConsoleApp().post(clsgrp, "http://localhost:8080/api/v1/day-care/classgroup/save/");

                        String columns [] = {"Class ID","Number Of Students", "Is Junior"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns [] = {"Class ID","Number Of Students", "Is Junior"};
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
