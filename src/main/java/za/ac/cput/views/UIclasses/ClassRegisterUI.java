package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.ClassRegister;
import za.ac.cput.factory.ClassRegisterFactory;
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

public class ClassRegisterUI
{
    private static OkHttpClient client = new OkHttpClient();
    private JLabel rosterIDLBL, teacherIDLBL, classIDLBL, dateLBL, numOfStudentsLBL;
    private JTextField rosterIDField, teacherIDField, classIDField, dateField, numOfStudentsField;
    private JPanel rosterIDPanel, teacherIDPanel, classIDPanel, datePanel, numOfStudentsPanel;
    private JTable registerTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel registerTablePanel;
    private JPanel createRegisterPanel;

    JPanel crud;

    private CrudPanel crudPanel;

    private ClassRegister reg;
    ClassRegister rgstr;


    public ClassRegisterUI()
    {
        rosterIDLBL = new JLabel("Roster ID");
        teacherIDLBL= new JLabel("Teacher ID");
        classIDLBL = new JLabel("Class ID");
        dateLBL = new JLabel("Date");
        numOfStudentsLBL = new JLabel("Number of Students");

        rosterIDField = new JTextField();
        teacherIDField = new JTextField();
        classIDField = new JTextField();
        dateField = new JTextField();
        numOfStudentsField = new JTextField();

        rosterIDPanel = new JPanel();
        teacherIDPanel = new JPanel();
        classIDPanel = new JPanel();
        datePanel = new JPanel();
        numOfStudentsPanel = new JPanel();

        registerTablePanel = new JPanel();
        createRegisterPanel = new JPanel();

        registerTable = new JTable();
        String columns [] = {"Roster ID","Teacher ID", "Class ID", "Date", "Number of Students"};
        tableModel = new DefaultTableModel(columns , 0);
        newPane = new JScrollPane();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();
    }
    public JPanel registerSetUp()
    {
        createTable();
        mouseListenerMethod();

        rosterIDPanel.setLayout(new GridLayout(1, 2));
        teacherIDPanel.setLayout(new GridLayout(1, 2));
        classIDPanel.setLayout(new GridLayout(1, 2));
        datePanel.setLayout(new GridLayout(1, 2));
        numOfStudentsPanel.setLayout(new GridLayout(1, 2));

        createRegisterPanel.setLayout(new GridLayout(10, 0));
        createRegisterPanel.add(rosterIDPanel);
        createRegisterPanel.add(teacherIDPanel);
        createRegisterPanel.add(classIDPanel);
        createRegisterPanel.add(datePanel);
        createRegisterPanel.add(numOfStudentsPanel);

        rosterIDPanel.add(rosterIDLBL);
        rosterIDPanel.add(rosterIDField);
        teacherIDPanel.add(teacherIDLBL);
        teacherIDPanel.add(teacherIDField);
        classIDPanel.add(classIDLBL);
        classIDPanel.add(classIDField);
        datePanel.add(dateLBL);
        datePanel.add(dateField);
        numOfStudentsPanel.add(numOfStudentsLBL);
        numOfStudentsPanel.add(numOfStudentsField);

        registerTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createRegisterPanel, registerTablePanel);
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
                Object o = g.fromJson(identity.toString(), ClassRegister.class);
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
            registerTable = new JTable(tableModel);
            newPane.setViewportView(registerTable);
            newPane.setPreferredSize(new Dimension(900, 200));

            java.util.List regList = getAll("http://localhost:8080/api/v1/day-care/classregister/all");
            java.util.List<ClassRegister> regList1 =  regList;

            for(int i = 0; i < regList.size(); i++ )
            {
                Object[] objs = {regList1.get(i).getRosterID(), regList1.get(i).getTeacherID(), regList1.get(i).getClassID(),
                        regList1.get(i).getDate(), regList1.get(i).getNumOfPresStudents()};
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public ClassRegister getTableItem(int rowNum)
    {
        ClassRegister newReg = null;
        try
        {
            Object obj  =  getAll("http://localhost:8080/api/v1/day-care/classregister/all");
            java.util.List list = (List<ClassRegister>)obj;
            newReg = (ClassRegister) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newReg;
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

                String rosterID = "";
                String teacherID = "";
                String classID = "";
                String date = "";
                String numOfStudents = "";
                int numOfStudentsInt = 0;

                for(int j = 0; j < registerTable.getColumnCount(); j++)
                {
                    if(j==0)
                    {
                        rosterID =  registerTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==1)
                    {
                        teacherID = registerTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==2)
                    {
                        classID =  registerTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==3)
                    {
                        date = registerTable.getModel().getValueAt(firstRow ,j).toString();
                    }
                    if(j==4)
                    {
                        numOfStudents = registerTable.getModel().getValueAt(firstRow ,j).toString();
                        numOfStudentsInt = Integer.parseInt(numOfStudents);
                    }
                }
                rgstr = ClassRegisterFactory.createClassRegister(rosterID, teacherID,
                        classID, date, numOfStudentsInt);
            }});

        System.out.println(rgstr);
    }

    public void createMouseListener(MouseListener ml)
    {
        registerTable.addMouseListener(ml);
    }

    public void mouseListenerMethod()
    {
        createMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                reg = getTableItem(registerTable.getSelectedRow());
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

                    String rosterID = rosterIDField.getText();
                    String teacherID = teacherIDField.getText();
                    String classID = classIDField.getText();
                    String date = dateField.getText();
                    String numOfStudents = numOfStudentsField.getText();
                    int numOfStudentsInt = Integer.parseInt(numOfStudents);

                    ClassRegister  dc = ClassRegisterFactory.createClassRegister(rosterID, teacherID,
                            classID, date, numOfStudentsInt);
                    new ConsoleApp().post(dc, "http://localhost:8080/api/v1/day-care/classregister/save");

                    String columns [] = {"Roster ID","Teacher ID", "Class ID", "Date", "Number of Students"};
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
                        new ConsoleApp().delete(reg.getRosterID(), "http://localhost:8080/api/v1/day-care/classregister/delete/");

                        String columns [] = {"Roster ID","Teacher ID", "Class ID", "Date", "Number of Students"};
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
                        System.out.println(rgstr);
                        new ConsoleApp().post(rgstr, "http://localhost:8080/api/v1/day-care/classregister/save/");

                        String columns [] = {"Roster ID","Teacher ID", "Class ID", "Date", "Number of Students"};
                        tableModel = new DefaultTableModel(columns , 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns [] = {"Roster ID","Teacher ID", "Class ID", "Date", "Number of Students"};
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
