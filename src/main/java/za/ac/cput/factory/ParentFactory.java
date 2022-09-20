package za.ac.cput.factory;

import za.ac.cput.domain.Parent;
import za.ac.cput.util.Helper;

/* Author : Karl Haupt
 * Student Number: 220236585
 */

public class ParentFactory {

    public static Parent buildParent(String parentID, String firstName, String lastName, String address, String phoneNumber) {
        Helper.checkStringParam("parentID", parentID);
        Helper.checkStringParam("firstName", firstName);
        Helper.checkStringParam("lastName", lastName);
        Helper.checkStringParam("address", address);
        Helper.checkStringParam("phoneNumber", phoneNumber);

        Helper.isValidPhoneNumber(phoneNumber);

        return new Parent.Builder()
                .setParentID(parentID)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(address)
                .setPhoneNumber(phoneNumber)
                .build();

    }
}
