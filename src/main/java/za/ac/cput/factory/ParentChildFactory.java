package za.ac.cput.factory;

import za.ac.cput.domain.ParentChild;
import za.ac.cput.util.Helper;

/* Author : Karl Haupt
 *  Student Number: 220236585
 */

public class ParentChildFactory {

    public static ParentChild buildParentChild(String parentID, String childID) {
        if(isInvalidParameters(parentID, childID))
            throw new IllegalArgumentException("Error: Invalid value(s)");

        return new ParentChild.Builder().setParentID(parentID).setChildID(childID).build();
    }

    private static boolean isInvalidParameters(String parentID, String childID) {
        return (Helper.isEmptyOrNull(parentID) || Helper.isEmptyOrNull(childID));
    }
}
