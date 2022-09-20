package za.ac.cput.factory;

import za.ac.cput.domain.DayCareVenue;
import za.ac.cput.util.Helper;

public class DayCareVenueFactory
{
    private String dayCareName;
    private String address;
    private String phone;
    private String principalId;

    public static DayCareVenue build(String dayCareName, String address, String phone, String principalId)
    {

        if (Helper.isEmptyOrNull(dayCareName))
            throw new IllegalArgumentException("Daycare Name is invalid.");
        if (Helper.isEmptyOrNull(address))
            throw new IllegalArgumentException("Address is invalid");
        if (Helper.isEmptyOrNull(phone))
            throw new IllegalArgumentException("Phone number is invalid");
        if (Helper.isEmptyOrNull(principalId))
            throw new IllegalArgumentException("Principal Id is invalid");

        return new DayCareVenue.Builder()
                .setDayCareName(dayCareName)
                .setAddress(address)
                .setPhone(phone)
                .setPrincipalId(principalId)
                .build();
    }
}
