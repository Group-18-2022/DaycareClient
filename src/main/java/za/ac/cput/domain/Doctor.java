package za.ac.cput.domain;

/*  Author : Karl Haupt
 *  Student Number: 220236585
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Doctor {
    @NotNull @Id
    private String doctorID;
    @NotNull
    private String practiceName, firstName, lastName, phoneNumber;

    private Doctor(Builder builder) {
        this.doctorID = builder.doctorID;
        this.practiceName = builder.practiceName;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.phoneNumber = builder.phoneNumber;
    }

    protected Doctor() {}

    public String getDoctorID() {
        return doctorID;
    }

    public String getPracticeName() {
        return practiceName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class Builder {
        private String doctorID, practiceName, firstName, lastName, phoneNumber;

        public Builder setDoctorID(String doctorID) {
            this.doctorID = doctorID;
            return this;
        }

        public Builder setPracticeName(String practiceName) {
            this.practiceName = practiceName;
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

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder copy(Doctor doctor) {
          this.doctorID = doctor.doctorID;
          this.practiceName = doctor.practiceName;
          this.firstName = doctor.firstName;
          this.lastName = doctor.lastName;
          this.phoneNumber = doctor.phoneNumber;
          return this;
        }

        public Doctor build() { return new Doctor(this); }
    }
}
