package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * This is the Child Builder
 * @author Charles Moses Lemmert (220498385)
 *
 * **/
@Entity
public class Child {
    @NotNull @Id
    private String childID;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String Address;
    @NotNull
    private String DOB;
    @NotNull
    private String Gender;

    protected Child(){
    }
    private Child(childBuilder build){
        this.childID = build.childID;
        this.firstName = build.firstName;
        this.lastName = build.lastName;
        this.Address = build.Address;
        this.DOB = build.DOB;
        this.Gender = build.Gender;
    }

    public String getChildID() {
        return childID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return Address;
    }

    public String getDOB() {
        return DOB;
    }

    public String getGender() {
        return Gender;
    }

    @Override
    public String toString() {
        return "Child{" +
                "childID: '" + childID + '\'' +
                ", firstName: '" + firstName + '\'' +
                ", lastName: '" + lastName + '\'' +
                ", Address: '" + Address + '\'' +
                ", DOB: '" + DOB + '\'' +
                ", Gender: '" + Gender + '\'' +
                '}';
    }
    public static class childBuilder{
        private String childID;
        private String firstName;
        private String lastName;
        private String Address;
        private String DOB;
        private String Gender;

        public childBuilder setChildID(String childID) {
            this.childID = childID;
            return this;
        }

        public childBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public childBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public childBuilder setAddress(String address) {
            Address = address;
            return this;
        }

        public childBuilder setDOB(String DOB) {
            this.DOB = DOB;
            return this;
        }

        public childBuilder setGender(String gender) {
            Gender = gender;
            return this;
        }

        public childBuilder copy(Child child){
            this.childID = child.childID;
            this.firstName = child.firstName;
            this.lastName = child.lastName;
            this.Address = child.Address;
            this.DOB = child.DOB;
            this.Gender = child.Gender;
            return this;
        }
        public Child build(){
            return new Child(this);
        }

    }
}
