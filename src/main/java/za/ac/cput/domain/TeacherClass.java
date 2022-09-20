package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

/* Author : Mike Somelezo Tyolani
 *  Student Number: 220187568
 */
@Entity
@IdClass(TeacherClass.TeacherClassID.class) //Team Lead added this annotation because program did not have the inner class for the composite ID
public class TeacherClass implements Serializable{
    @NotNull @Id
    private String roomID, teacherID;

    private TeacherClass(Builder builder) {
        this.roomID = builder.roomID;
        this.teacherID = builder.teacherID;
    }

    protected TeacherClass() {

    }

    public String getTeacherID() {
        return roomID;
    }

    public String getRoomID() {
        return teacherID;
    }

    public static class Builder {
        private String roomID, teacherID;

        public Builder setTeacherID(String teacherID) {
            this.teacherID = teacherID;
            return this;
        }

        public Builder setRoomID(String roomID) {
            this.roomID = roomID;
            return this;
        }

        public Builder copy(TeacherClass teacherClass) {
            this.teacherID = teacherClass.teacherID;
            this.roomID = teacherClass.roomID;
            return this;
        }

        public TeacherClass build() { return new TeacherClass(this); }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherClass that = (TeacherClass) o;
        return Objects.equals(roomID, that.roomID) && Objects.equals(teacherID, that.teacherID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomID, teacherID);
    }

    public static class TeacherClassID implements Serializable {
        private String roomID, teacherID;

        public TeacherClassID(String roomID, String teacherID) {
            this.roomID = roomID;
            this.teacherID = teacherID;
        }

        protected TeacherClassID() {}

        public String getRoomID() {
            return roomID;
        }

        public String getTeacherID() {
            return teacherID;
        }
    }


}


