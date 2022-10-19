package za.ac.cput.domain;
/* Author : Mike Somelezo Tyolani
 *  Student Number: 220187568
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Incidents implements Serializable {
    @NotNull @Id
    private String incidentID;
    @NotNull
    private String date, location, injuryDescription, teacherID, childID;

    private Incidents(Builder builder) {
        this.incidentID = builder.incidentID;
        this.teacherID = builder.teacherID;
        this.childID = builder.childID;
        this.date = builder.date;
        this.location = builder.location;
        this.injuryDescription = builder.injuryDescription;

    }

    protected Incidents () {}

    public String getIncidentID() {
        return incidentID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getChildID() {
        return childID;
    }

    public String getDate() {
        return date;
    }

    public String getLocation() {
        return location;
    }

    public String getInjuryDescription() {
        return injuryDescription;
    }

    public static class Builder {
        private String incidentID, teacherID, childID, date, location, injuryDescription;

        public Builder setIncidentID(String incidentID) {
            this.incidentID = incidentID;
            return this;
        }

        public Builder setTeacherID(String teacherID) {
            this.teacherID = teacherID;
            return this;
        }

        public Builder setChildID(String childID) {
            this.childID = childID;
            return this;
        }

        public Builder setDate(String date) {
            this.date = date;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setInjuryDescription(String injuryDescription) {
            this.injuryDescription = injuryDescription;
            return this;
        }



        public Builder copy(Incidents incidents) {
            this.incidentID = incidents.incidentID;
            this.teacherID = incidents.teacherID;
            this.childID = incidents.childID;
            this.date = incidents.date;
            this.location = incidents.location;
            this.injuryDescription = incidents.injuryDescription;
            return this;
        }

        public Incidents build() { return new Incidents(this); }


    }

}
