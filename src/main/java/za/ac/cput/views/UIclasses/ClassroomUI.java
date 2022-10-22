package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import za.ac.cput.domain.ClassRoom;
import za.ac.cput.factory.ClassRoomFactory;
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

public class ClassroomUI {
    private static OkHttpClient client = new OkHttpClient();
    private JLabel roomNumberLbl, occupancyLbl;
    private JTextField roomNumberField, occupancyField;
    private JPanel roomPanel, occupancyPanel;

    private JTable classRoomTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel classRoomTablePanel;
    private JPanel createClassRoomPanel;

    JPanel crud;

    private CrudPanel crudPanel;

    private ClassRoom cr;
    ClassRoom cRoom;


    public ClassroomUI()
    {
        roomNumberLbl = new JLabel("Room Number");
        occupancyLbl = new JLabel("Maximum Occupancy");

        roomNumberField = new JTextField();
        occupancyField = new JTextField();

        classRoomTable = new JTable();
        String columns [] = {"Classroom Number", "Max Occupancy"};
        tableModel = new DefaultTableModel(columns , 0);
        newPane = new JScrollPane();

        roomPanel = new JPanel();
        occupancyPanel = new JPanel();
        classRoomTablePanel = new JPanel();
        createClassRoomPanel = new JPanel();

        crudPanel = new CrudPanel();

        modelListenerMethod();
        actionListenerMethod();
    }
    public JPanel classRoomSetUp()
    {
        createTable();
        mouseListenerMethod();

        roomPanel.setLayout(new GridLayout(1, 2));
        occupancyPanel.setLayout(new GridLayout(1, 2));

        createClassRoomPanel.setLayout(new GridLayout(10, 0));
        createClassRoomPanel.add(roomPanel);
        createClassRoomPanel.add(occupancyPanel);

        roomPanel.add(roomNumberLbl);
        roomPanel.add(roomNumberField);
        occupancyPanel.add(occupancyLbl);
        occupancyPanel.add(occupancyField);

        classRoomTablePanel.add(newPane);

        crud = crudPanel.crudSetUp(createClassRoomPanel, classRoomTablePanel);
        return crud;
    }


    public static List<Object> getAll(String allUrl) //pass the url from the Controller class for findAll/getAll
    {
        List<Object> objectList = new ArrayList<>();
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
                Object o = g.fromJson(identity.toString(), ClassRoom.class);
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

            List classRoomList = getAll("http://localhost:8080/api/v1/day-care/classroom/all");
            List<ClassRoom> classRoomList1 =  classRoomList;

            for(int i = 0; i < classRoomList.size(); i++ )
            {
                Object[] objs = {classRoomList1.get(i).getClassroomNumber(), classRoomList1.get(i).getOccupancy()};
                tableModel.addRow(objs);
            }
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public ClassRoom getTableItem(int rowNum)
    {
        ClassRoom newClassroom = null;
        try
        {
            Object obj  =  getAll("http://localhost:8080/api/v1/day-care/classroom/all");
            List list = (List<ClassRoom>)obj;
            newClassroom = (ClassRoom) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newClassroom;
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

                String roomNumber = "";
                String occupiedRooms = "";

                    for(int j = 0; j < classRoomTable.getColumnCount();j++)
                    {
                        if(j==0)
                        {
                            roomNumber =  classRoomTable.getModel().getValueAt(firstRow ,j).toString();
                        }
                        if(j==1)
                        {
                            occupiedRooms = classRoomTable.getModel().getValueAt(firstRow ,j).toString();
                        }
                    }
                    cRoom = ClassRoomFactory.build(roomNumber, occupiedRooms);
        }});

        System.out.println(cRoom);
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
                cr = getTableItem(classRoomTable.getSelectedRow());
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
                        String roomNumber = roomNumberField.getText();
                        String occupancy = occupancyField.getText();

                        ClassRoom  cr = ClassRoomFactory.build(roomNumber, occupancy);
                        new ConsoleApp().post(cr, "http://localhost:8080/api/v1/day-care/classroom/save");
                        String columns [] = {"Classroom Number", "Max Occupancy"};
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
                        new ConsoleApp().delete(cr.getClassroomNumber(), "http://localhost:8080/api/v1/day-care/classroom/delete/");
                        String columns[] = {"Classroom Number", "Max Occupancy"};
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
                        System.out.println(cRoom);
                        new ConsoleApp().post(cRoom, "http://localhost:8080/api/v1/day-care/classroom/save/");

                        String columns[] = {"Classroom Number", "Max Occupancy"};
                        tableModel = new DefaultTableModel(columns, 0);

                        createTable();
                        mouseListenerMethod();
                        modelListenerMethod();

                        JOptionPane.showMessageDialog(null, "Record was successfully Updated!");
                    }
                    else
                    {
                        String columns[] = {"Classroom Number", "Max Occupancy"};
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
