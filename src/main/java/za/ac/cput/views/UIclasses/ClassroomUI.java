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
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClassroomUI implements ActionListener
{
    private static OkHttpClient client = new OkHttpClient();
    private JLabel roomNumberLbl, occupancyLbl;
    private JTextField roomNumberField, occupancyField;
    private JPanel roomPanel, occupancyPanel;

    private JTable classRoomTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel classRoomTablePanel;
    private JPanel createClassRoomPanel;

    private CrudPanel crudPanel = new CrudPanel();

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

        crudPanel.createBtnAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getActionCommand() == "create")
                {
                    System.out.println("ahahahaha");
                    String roomNumber = roomNumberField.getText();
                    String occupancy = occupancyField.getText();

                    ClassRoom  cr = ClassRoomFactory.build(roomNumber, occupancy);
                    new ConsoleApp().post(cr, "http://localhost:8080/api/v1/day-care/classroom/save");
                }
                if (e.getActionCommand() == "refresh")
                {
                   createTable();
                }

            }
        });
    }
    public JPanel classRoomSetUp()
    {
        createTable();

        roomPanel.setLayout(new GridLayout(1, 2));
        occupancyPanel.setLayout(new GridLayout(1, 2));

        createClassRoomPanel.setLayout(new GridLayout(10, 2));
        createClassRoomPanel.add(roomPanel);
        createClassRoomPanel.add(occupancyPanel);

        roomPanel.add(roomNumberLbl);
        roomPanel.add(roomNumberField);
        occupancyPanel.add(occupancyLbl);
        occupancyPanel.add(occupancyField);

        classRoomTablePanel.add(newPane);

        JPanel crud = crudPanel.crudSetUp(createClassRoomPanel, classRoomTablePanel);
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
            newPane.setPreferredSize(new Dimension(750, 100));

            List classRoomList = getAll("http://localhost:8080/api/v1/day-care/classroom/all");
            List<ClassRoom> classRoomList1 =  classRoomList;
            System.out.println(classRoomList.get(1));

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

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand() == "create")
        {
            System.out.println("hahahahaha");
        }
    }
}
