package za.ac.cput.domain;
/* Author : Karl Haupt
 *  Student Number: 220236585
 */

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Parent {
    @NotNull @Id
    private String parentID;
    @NotNull
    private String firstName, lastName, address, phoneNumber;

    private Parent(Builder builder) {
        this.parentID = builder.parentID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.address = builder.address;
        this.phoneNumber = builder.phoneNumber;
    }

    protected Parent() {}

    public String getParentID() {
        return parentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public static class Builder {
        private String parentID, firstName, lastName, address, phoneNumber;

        public Builder setParentID(String parentID) {
            this.parentID = parentID;
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

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder copy(Parent parent) {
            this.parentID = parent.parentID;
            this.firstName = parent.firstName;
            this.lastName = parent.lastName;
            this.address = parent.address;
            this.phoneNumber = parent.phoneNumber;
            return this;
        }

        public Parent build() { return new Parent(this); }
    }
}
