package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/* Author : Mike Somelezo Tyolani
 *  Student Number:  
 */
@Entity
public class Teacher {
    @NotNull
    @Id
    private String teacherID;
    @NotNull
    private String classNumber, firstName, lastName, dateOfBirth;

    private Teacher(Builder builder) {
        this.teacherID = builder.teacherID;
        this.classNumber = builder.classNumber;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.dateOfBirth = builder.dateOfBirth;
    }

    protected Teacher() {}

    public String getTeacherID() {return teacherID;}

    public String getClassNumber() {
        return classNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }


    public static class Builder {
        private String teacherID, classNumber, firstName, lastName, dateOfBirth;

        public Builder setTeacherID (String teacherID) {
            this.teacherID = teacherID;
            return this;
        }

        public Builder setClassNumber (String classNumber) {
            this.classNumber = classNumber;
            return this;
        }

        public Builder setFirstName (String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName (String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setDateOfBirth (String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }


        public Builder copy (Teacher teacher){
           // teacherID, classNumber, firstName, lastName, dateOfBirth
            this.teacherID = teacher.teacherID;
            this.classNumber = teacher.classNumber;
            this.firstName = teacher.firstName;
            this.lastName = teacher.lastName;
            this.dateOfBirth = teacher.dateOfBirth;
            return this;

        }

        public Teacher build() { return new Teacher(this);}
    }
}
