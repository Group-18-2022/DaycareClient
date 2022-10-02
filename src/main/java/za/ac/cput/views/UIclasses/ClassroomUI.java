package za.ac.cput.views.UIclasses;

import org.springframework.data.jpa.repository.JpaRepository;
import za.ac.cput.domain.ClassRoom;
import za.ac.cput.views.consoleapp.ConsoleApp;
import za.ac.cput.views.mainPanels.CrudPanel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class ClassroomUI
{
    private JLabel roomNumberLbl, occupancyLbl;
    private JTextField roomNumberField, occupancyField;
    private JPanel roomPanel, occupancyPanel;

    private JTable classRoomTable;
    private DefaultTableModel tableModel;
    private JScrollPane newPane;

    private JPanel classRoomTablePanel;
    private JPanel createClassRoomPanel;
    private JPanel updateClassRoomPanel;

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
        updateClassRoomPanel = new JPanel();
    }

    public JPanel classRoomSetUp()
    {
        createTable();

        roomPanel.setLayout(new BoxLayout(roomPanel, BoxLayout.LINE_AXIS));
        occupancyPanel.setLayout(new BoxLayout(occupancyPanel, BoxLayout.LINE_AXIS));
        createClassRoomPanel.setLayout(new BoxLayout(createClassRoomPanel, BoxLayout.PAGE_AXIS));
        createClassRoomPanel.add(roomPanel);
        createClassRoomPanel.add(occupancyPanel);

        updateClassRoomPanel.setLayout(new BoxLayout(updateClassRoomPanel, BoxLayout.PAGE_AXIS));
        updateClassRoomPanel.add(roomPanel);
        updateClassRoomPanel.add(occupancyPanel);

        roomPanel.add(roomNumberLbl);
        roomPanel.add(roomNumberField);
        occupancyPanel.add(occupancyLbl);
        occupancyPanel.add(occupancyField);


        classRoomTablePanel.add(newPane);

        CrudPanel crudPanel = new CrudPanel();

        return crudPanel.crudSetUp(createClassRoomPanel, updateClassRoomPanel, classRoomTablePanel);
    }


    public void createTable()
    {
        try
        {
            String columns [] = {"Classroom Number", "Max Occupancy"};
            tableModel = new DefaultTableModel(columns , 0);
            classRoomTable = new JTable(tableModel);
            newPane.setViewportView(classRoomTable);

            List classRoomList = ConsoleApp.getAll("http://localhost:8080/api/v1/day-care/classroom/all/");

            List<ClassRoom> classRoomList1 =  (List<ClassRoom>) classRoomList;

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



}
