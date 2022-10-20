package za.ac.cput.domain;


/* Mponeng Ratego
 * 216178991
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Driver {

    @Id
    @NotNull
    private String idNumber;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String driverCode;

    private Driver(Builder build){
        this.idNumber = build.idNumber;
        this.firstName = build.firstName;
        this.lastName = build.lastName;
        this.driverCode = build.driverCode;

    }

    protected Driver() {}
    public String getIdNumber() {
        return idNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDriverCode() {
        return driverCode;
    }



    @Override
    public String toString() {
        return "Driver{" +
                "idNumber: '" + idNumber + '\'' +
                ", firstName: '" + firstName + '\'' +
                ", lastName: '" + lastName + '\'' +
                ", driverCode: '" + driverCode +
                '}';
    }

    public Object idNumber() {
        return idNumber;
    }


    public static class Builder{
        private String idNumber;
        private String firstName;
        private String lastName;
        private String driverCode;

        public Builder setIdNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setDriverCode(String driverCode) {
            this.driverCode = driverCode;

            return this;
        }


        public Builder copy(Driver driver){
            this.idNumber = driver.idNumber;
            this.firstName = driver.firstName;
            this.lastName = driver.lastName;
            this.driverCode = driver.driverCode;

            return this;
        }
        public Driver build(){
            return new Driver(this);
        }

    }

    @Override
    public int hashCode() {
        return Objects.hash(idNumber, firstName, lastName, driverCode);
    }

}
