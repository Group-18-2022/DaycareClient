package za.ac.cput.factory;

import za.ac.cput.domain.Driver;
import za.ac.cput.util.Helper;

/*Mponeng Ratego
 216178991
 */


public class DriverFactory {

    public static Driver createDriver(String idNumber,String firstName, String lastName, String driverCode) {
        Helper.checkStringParam("idNumber", idNumber);
        Helper.checkStringParam("firstName", firstName);
        Helper.checkStringParam("lastName", lastName);
        Helper.checkStringParam("driverCode", driverCode);
        

        return new Driver.Builder()
                .setIdNumber(idNumber)
                .setDriverCode(driverCode)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();

    }

}

