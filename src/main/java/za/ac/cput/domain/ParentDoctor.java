package za.ac.cput.domain;
/* Author : Karl Haupt
 * Student Number: 220236585
 */

import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;

@Entity
@IdClass(ParentDoctor.ParentDoctorID.class)
public class ParentDoctor implements Serializable{
    @NotNull @Id
    private String doctorID, parentID;

    private ParentDoctor(Builder builder) {
        this.doctorID = builder.doctorID;
        this.parentID = builder.parentID;
    }

    protected ParentDoctor() {}

    public String getDoctorID() {
        return doctorID;
    }

    public String getParentID() {
        return parentID;
    }

    public static class Builder {
        private String doctorID, parentID;

        public Builder setDoctorID(String doctorID) {
            this.doctorID = doctorID;
            return this;
        }

        public Builder setParentID(String parentID) {
            this.parentID = parentID;
            return this;
        }

        public Builder copy(ParentDoctor parentDoctor) {
            this.doctorID = parentDoctor.doctorID;
            this.parentID = parentDoctor.parentID;
            return this;
        }

        public ParentDoctor build() { return new ParentDoctor(this); }
    }

    public static class ParentDoctorID implements Serializable {
        private String doctorID, parentID;

        public ParentDoctorID(String doctorID, String parentID) {
            this.doctorID = doctorID;
            this.parentID = parentID;
        }

        protected ParentDoctorID() {}

        public String getDoctorID() {
            return doctorID;
        }

        public String getParentID() {
            return parentID;
        }
    }
}
