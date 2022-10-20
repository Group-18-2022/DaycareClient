package za.ac.cput.domain;

/* Mponeng Ratego
 * 216178991
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class VehicleRegDetails {

    @NotNull
    @Id
    private String vehicleId;
    @NotNull
    private String brand;
    @NotNull
    private String model;
    @NotNull
    private String year;
    @NotNull
    private String regDate;

    protected  VehicleRegDetails(){}
    private VehicleRegDetails(Builder build){
        this.vehicleId = build.vehicleId;
        this.brand = build.brand;
        this.model = build.model;
        this.year = build.year;
        this.regDate = build.regDate;

    }


    public String getVehicleId(){return vehicleId;}
    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getYear() {
        return year;
    }

    public String getRegDate() {
        return regDate;
    }



    @Override
    public String toString() {
        return "VehicleRegDetails{" +
                "vehicleId:" + vehicleId + '\'' +
                "brand: '" + brand + '\'' +
                ", model: '" + model + '\'' +
                ", year: '" + year + '\'' +
                ", regDate: '" + regDate +
                '}';
    }


    public static class Builder{

        private String vehicleId;
        private String brand;
        private String model;
        private String year;
        private String regDate;

        public Builder setVehicleId(String brand) {
            this.vehicleId = vehicleId;
            return this;
        }
        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(String year) {
            this.year = year;
            return this;
        }

        public Builder setRegDate(String regDate) {
            this.regDate = regDate;

            return this;
        }


        public Builder copy(VehicleRegDetails vehicleRegDetails){
            this.vehicleId = vehicleRegDetails.vehicleId;
            this.brand = vehicleRegDetails.brand;
            this.model = vehicleRegDetails.model;
            this.year = vehicleRegDetails.year;
            this.regDate = vehicleRegDetails.regDate;

            return this;
        }
        public VehicleRegDetails build(){
            return new VehicleRegDetails(this);
        }

    }

}
