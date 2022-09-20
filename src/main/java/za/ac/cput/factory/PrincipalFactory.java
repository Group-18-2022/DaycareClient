package za.ac.cput.factory;

/* PrincipalFactory.java
   Factory for the Principal
   Author: Joshua Daniel Jonkers(215162668)
   Date: 22/05/2022
 */

import za.ac.cput.domain.Principal;
import za.ac.cput.util.Helper;

public class PrincipalFactory {
    public static Principal createPrincipal(String principalID, String firstName, String lastName, String dob) {
        Helper.checkStringParam("principalID", principalID);
        Helper.checkStringParam("firstName", firstName);
        Helper.checkStringParam("lastName", lastName);
        Helper.checkStringParam("dob", dob);

        return new Principal.Builder()
                            .setPrincipalID(principalID)
                            .setFirstName(firstName)
                            .setLastName(lastName)
                            .setDob(dob)
                            .build();
    }
}
