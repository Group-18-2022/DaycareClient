package za.ac.cput.factory;

import za.ac.cput.domain.Child;
import za.ac.cput.util.Helper;

/**
 *
 * This is the Child Factory
 * @author Charles Moses Lemmert (220498385)
 *
 * **/
public class ChildFactory {

    public static Child createChild(String contactID,String firstName, String lastName,
    String Address, String DOB, String Gender){


        Helper.checkStringParam("Contact ID", contactID);
        Helper.checkStringParam("First Name", firstName);
        Helper.checkStringParam("Last Name", lastName);
        Helper.checkStringParam("Address", Address);
        Helper.checkStringParam("Date of Birth", DOB);
        Helper.checkStringParam("Gender", Gender);

        return new Child.childBuilder()
                .setChildID(contactID)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setAddress(Address)
                .setDOB(DOB)
                .setGender(Gender)
                .build();

    }
}
