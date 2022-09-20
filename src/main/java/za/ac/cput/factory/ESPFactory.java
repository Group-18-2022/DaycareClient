package za.ac.cput.factory;

/* ESPFactory.java
   Factory for the EmergencyServiceProvider
   Author: Joshua Daniel Jonkers(215162668)
   Date: 22/05/2022
 */

import za.ac.cput.domain.EmergencyServiceProvider;
import za.ac.cput.util.Helper;

public class ESPFactory {
    public static EmergencyServiceProvider createESP(String serviceID, String serviceName, String type, String phoneNum) {
        Helper.checkStringParam("serviceID", serviceID);
        Helper.checkStringParam("serviceName", serviceName);
        Helper.checkStringParam("type", type);
        Helper.checkStringParam("phoneNum", phoneNum);

        return new EmergencyServiceProvider.Builder()
                                            .setServiceID(serviceID)
                                            .setServiceName(serviceName)
                                            .setType(type)
                                            .setPhoneNum(phoneNum)
                                            .build();
    }
}
