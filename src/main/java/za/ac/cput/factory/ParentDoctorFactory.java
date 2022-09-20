package za.ac.cput.factory;

import za.ac.cput.domain.ParentDoctor;
import za.ac.cput.util.Helper;

/* Author : Karl Haupt
 *  Student Number: 220236585
 */

public class ParentDoctorFactory {
    public static ParentDoctor buildParentDoctor(String doctorID, String parentID) {
       if(isInvalidParameters(doctorID, parentID)) throw new IllegalArgumentException("Error: Invalid value(s)");

       return new ParentDoctor.Builder().setDoctorID(doctorID).setParentID(parentID).build();
    }

    private static boolean isInvalidParameters(String doctorID, String parentID) {
        return (Helper.isEmptyOrNull(doctorID) || Helper.isEmptyOrNull(parentID));
    }

}
