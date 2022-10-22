package za.ac.cput.factory;

import za.ac.cput.domain.ClassRegister;
import za.ac.cput.util.Helper;

/**
 *
 * This is the ClassRegister Factory
 * @author Charles Moses Lemmert (220498385)
 *
 * **/
public class ClassRegisterFactory {

    public static ClassRegister createClassRegister(String rosterID,String teacherID, String classID, String date, int numOfPresStudents){


        Helper.checkStringParam("Roster ID", rosterID);
        Helper.checkStringParam("Teacher ID", teacherID);
        // Helper.checkStringParam("ChildID", childID);
        Helper.checkStringParam("Class ID", classID);
        Helper.checkStringParam("Date", date);

        if(numOfPresStudents < 0)
            throw new IllegalArgumentException("\"Error: There cannot be a negative number of students presents.\"");

        return new ClassRegister.ClassRegisterBuilder()
                .setRosterID(rosterID)
                .setTeacherID(teacherID)
                //.setChildID(childID)
                .setClassID(classID)
                .setDate(date)
                .setNumOfPresStudents(numOfPresStudents)
                .build();


    }

}
