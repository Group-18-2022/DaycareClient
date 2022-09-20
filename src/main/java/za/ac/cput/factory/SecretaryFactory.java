package za.ac.cput.factory;

/* SecretaryFactory.java
   Factory for the Secretary
   Author: Joshua Daniel Jonkers(215162668)
   Date: 22/05/2022
 */

import za.ac.cput.domain.Secretary;
import za.ac.cput.util.Helper;

public class SecretaryFactory {
    public static Secretary createSecretary(String secretaryID, String firstName, String lastName, String dob) {
        Helper.checkStringParam("secretaryID", secretaryID);
        Helper.checkStringParam("firstName", firstName);
        Helper.checkStringParam("lastName", lastName);
        Helper.checkStringParam("dob", dob);

        return new Secretary.Builder()
                            .setSecretaryID(secretaryID)
                            .setFirstName(firstName)
                            .setLastName(lastName)
                            .setDob(dob)
                            .build();
    }
}
