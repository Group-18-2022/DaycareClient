package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * This is the ClassRegister Builder
 * @author Charles Moses Lemmert (220498385)
 *
 * **/
@Entity
public class ClassRegister {
    @NotNull
    @Id
    private String rosterID;

    @NotNull
    private String teacherID;

    @NotNull
    private String childID;

    @NotNull
    private String classID;

    @NotNull
    private String date;

    @NotNull
    private int numOfPresStudents;

    protected  ClassRegister(){}

    private ClassRegister(ClassRegisterBuilder builder) {
        this.rosterID = rosterID;
        this.teacherID = teacherID;
        this.childID = childID;
        this.classID = classID;
        this.date = date;
        this.numOfPresStudents = numOfPresStudents;
    }

    public String getRosterID() {
        return rosterID;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getChildID() {
        return childID;
    }

    public String getClassID() {
        return classID;
    }

    public String getDate() {
        return date;
    }

    public int getNumOfPresStudents() {
        return numOfPresStudents;
    }

    @Override
    public String toString() {
        return "ClassRegister{" +
                "rosterID='" + rosterID + '\'' +
                ", teacherID=" + teacherID +
                ", childID=" + childID +
                ", classID=" + classID +
                ", date='" + date + '\'' +
                ", numOfPresStudents='" + numOfPresStudents + '\'' +
                '}';
    }

    public static class ClassRegisterBuilder{
        private String rosterID;
        private String teacherID;
        private String childID;
        private String classID;
        private String date;
        private int numOfPresStudents;

        public ClassRegisterBuilder setRosterID(String rosterID) {
            this.rosterID = rosterID;
            return this;
        }

        public ClassRegisterBuilder setTeacherID(String teacherID) {
            this.teacherID = teacherID;
            return this;
        }

        public ClassRegisterBuilder setChildID(String childID) {
            this.childID = childID;
            return this;
        }

        public ClassRegisterBuilder setClassID(String classID) {
            this.classID = classID;
            return this;
        }

        public ClassRegisterBuilder setDate(String date) {
            this.date = date;
            return this;
        }

        public ClassRegisterBuilder setNumOfPresStudents(int numOfPresStudents) {
            this.numOfPresStudents = numOfPresStudents;
            return this;
        }

        public ClassRegisterBuilder copy(ClassRegister classRegister) {
            this.rosterID = classRegister.rosterID;
            this.teacherID = classRegister.teacherID;
            this.childID = classRegister.childID;
            this.classID = classRegister.classID;
            this.date = classRegister.date;
            this.numOfPresStudents = classRegister.numOfPresStudents;
            return  this;
        }
        public ClassRegister build(){
            return new ClassRegister(this);
        }
    }
}
