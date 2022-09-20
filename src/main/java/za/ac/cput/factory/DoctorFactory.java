package za.ac.cput.factory;

import za.ac.cput.domain.Doctor;
import za.ac.cput.util.Helper;

/* Author : Karl Haupt
 * Student Number: 220236585
 */

public class DoctorFactory {

    public static Doctor buildDoctor(String doctorID, String practiceName, String firstName, String lastName, String phoneNumber) {
        Helper.checkStringParam("doctorID", doctorID);
        Helper.checkStringParam("practiceName", practiceName);
        Helper.checkStringParam("firstName", firstName);
        Helper.checkStringParam("lastName", lastName);
        Helper.checkStringParam("phoneNumber", phoneNumber);

        Helper.isValidPhoneNumber(phoneNumber);

        return new Doctor.Builder()
                                .setDoctorID(doctorID)
                                .setPracticeName(practiceName)
                                .setFirstName(firstName)
                                .setLastName(lastName)
                                .setPhoneNumber(phoneNumber)
                                .build();
    }

}
