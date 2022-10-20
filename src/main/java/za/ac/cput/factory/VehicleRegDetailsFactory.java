package za.ac.cput.factory;

import za.ac.cput.domain.VehicleRegDetails;
import za.ac.cput.util.Helper;

/*Mponeng Ratego
 216178991
 */

public class VehicleRegDetailsFactory {

    public static VehicleRegDetails createVehicleRegDetails(String vehicleId, String brand, String model, String year, String regDate) {

        Helper.checkStringParam("vehicleId", vehicleId);
        Helper.checkStringParam("brand", brand);
        Helper.checkStringParam("model", model);
        Helper.checkStringParam("year", year);
        Helper.checkStringParam("regDate", regDate);

        return new VehicleRegDetails.Builder()
                .setVehicleId(vehicleId)
                .setBrand(brand)
                .setModel(model)
                .setYear(year)
                .setRegDate(regDate)
                .build();

    }

}
