package za.ac.cput.views.UIclasses;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.ClassRoom;
import za.ac.cput.factory.ClassRoomFactory;
import za.ac.cput.views.consoleapp.ConsoleApp;
import za.ac.cput.views.mainPanels.CrudPanel;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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

    public ClassroomUI()
    {
        roomNumberLbl = new JLabel("Room Number");
        occupancyLbl = new JLabel("Maximum Occupancy");

        roomNumberField = new JTextField();
        occupancyField = new JTextField();

        classRoomTable = new JTable();
        newPane = new JScrollPane();

        roomPanel = new JPanel();
        occupancyPanel = new JPanel();
        classRoomTablePanel = new JPanel();
        createClassRoomPanel = new JPanel();

        crudPanel = new CrudPanel();

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
        //crud.setPreferredSize(new Dimension(500, 100));
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
            String columns [] = {"Classroom Number", "Max Occupancy"};
            tableModel = new DefaultTableModel(columns , 0);
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
            Object obj  =  ConsoleApp.getAll("http://localhost:8080/api/v1/day-care/classroom/all");
            List list = (List<ClassRoom>)obj;
            newClassroom = (ClassRoom) list.get(rowNum);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return newClassroom;
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

                    createTable();
                }
                if (e.getActionCommand().equalsIgnoreCase("delete record"))
                {
                    new ConsoleApp().delete(cr.getClassroomNumber(), "http://localhost:8080/api/v1/day-care/classroom/delete/");

                    createTable();
                    mouseListenerMethod();
                }

                if (e.getActionCommand().equalsIgnoreCase("Update"))
                {
                    createTable();
                    mouseListenerMethod();
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
