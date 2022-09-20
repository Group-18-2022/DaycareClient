package za.ac.cput.domain;

/* Principal.java
   Entity for the Principal
   Author: Joshua Daniel Jonkers(215162668)
   Date: 22/05/2022
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Principal {
    @Id
    @NotNull
    private String principalID;
    @NotNull
    private String firstName, lastName, dob;

    protected Principal() {}

    private Principal(Builder builder) {
        this.principalID =builder.principalID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dob = builder.dob;
    }

    public String getPrincipalID() {
        return principalID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDob() {
        return dob;
    }

    @Override
    public String toString() {
        return "Principal{" +
                "principalID='" + principalID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }

    public static class Builder {
        private String principalID;
        private String firstName;
        private String lastName;
        private String dob;

        public Builder setPrincipalID(String principalID) {
            this.principalID = this.principalID;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = this.firstName;
            return  this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = this.lastName;
            return this;
        }

        public Builder setDob(String dob) {
            this.dob = this.dob;
            return this;
        }

        public Builder copy(Principal principal) {
            this.principalID = principal.principalID;
            this.firstName = principal.firstName;
            this.lastName = principal.lastName;
            this.dob = principal.dob;
            return this;
        }

        public Principal build() {
            return new Principal(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Principal)) return false;
        Principal principal = (Principal) o;
        return principalID.equals(principal.principalID) && firstName.equals(principal.firstName) && lastName.equals(principal.lastName) && dob.equals(principal.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(principalID, firstName, lastName, dob);
    }
}
