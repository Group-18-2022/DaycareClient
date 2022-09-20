package za.ac.cput.factory;
/* Author : Mike Somelezo Tyolani
 *  Student Number: 220187568
 */

import za.ac.cput.domain.Teacher;
import za.ac.cput.util.Helper;

public class TeacherFactory {
    public static Teacher build (String teacherID, String classNumber, String firstName, String lastName, String dateOfBirth){
        Helper.checkStringParam("teacherID", teacherID);
        Helper.checkStringParam("classNumber", classNumber);
        Helper.checkStringParam("firstName", firstName);
        Helper.checkStringParam("lastName", lastName);
        Helper.checkStringParam("dateOfBirth", dateOfBirth);

        return new Teacher.Builder()
                .setTeacherID(teacherID)
                .setClassNumber(classNumber)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setDateOfBirth(dateOfBirth)
                .build();
    }

}
