package za.ac.cput.factory;
/* Author : Mike Somelezo Tyolani
 *  Student Number: 220187568
 */
import za.ac.cput.domain.Incidents;
import za.ac.cput.util.Helper;

public class IncidentsFactory {
        public static Incidents build(String incidentID, String teacherID, String childID, String date, String location, String injuryDescription ) {
            if (isInvalidParameters(incidentID, teacherID, childID, date, location,injuryDescription ))
                throw new IllegalArgumentException("Error: Invalid value(s)");

            return new Incidents.Builder()
                    .setIncidentID(incidentID)
                    .setTeacherID(teacherID)
                    .setChildID(childID)
                    .setDate(date)
                    .setLocation(location)
                    .setInjuryDescription(injuryDescription)
                    .build();

        }

    private static boolean isInvalidParameters(String incidentID, String teacherID, String childID, String date, String location, String injuryDescription ) {
        return (
                Helper.isEmptyOrNull(incidentID) ||
                        Helper.isEmptyOrNull(teacherID) ||
                        Helper.isEmptyOrNull(childID) ||
                        Helper.isEmptyOrNull(date) ||
                        Helper.isEmptyOrNull(location) ||
                        Helper.isEmptyOrNull(injuryDescription)
        );
    }
}
