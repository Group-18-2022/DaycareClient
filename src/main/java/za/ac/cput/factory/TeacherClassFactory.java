package za.ac.cput.factory;
/* Author : Mike Somelezo Tyolani
 *  Student Number: 220187568
 */

import za.ac.cput.domain.TeacherClass;
import za.ac.cput.util.Helper;

public class TeacherClassFactory {
    public static TeacherClass build(String teacherID, String roomID) {
        if(isInvalidParameters(teacherID, roomID)) throw new IllegalArgumentException("Error: Invalid value(s)");

        return new TeacherClass.Builder().setTeacherID(teacherID).setRoomID(roomID ).build();
    }

    private static boolean isInvalidParameters(String teacherID, String roomID) {
        return (Helper.isEmptyOrNull(teacherID) || Helper.isEmptyOrNull(roomID));
    }
}
