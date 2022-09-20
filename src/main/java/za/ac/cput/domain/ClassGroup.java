package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 *
 * This is the ClassGroup Builder
 * @author Charles Moses Lemmert (220498385)
 *
 * **/
@Entity
public class ClassGroup {

    @NotNull @Id
    private String classID;
    @NotNull
    private int numOfRegStudent;
    @NotNull
    private boolean isJunior;

    protected  ClassGroup(){}
    private ClassGroup( classGroupBuilder builder){
        this.classID = builder.classID;
        this.numOfRegStudent = builder.numOfRegStudent;
        this.isJunior = builder.isJunior;
    }

    public String getClassID() {
        return classID;
    }

    public int getNumOfRegStudent() {
        return numOfRegStudent;
    }

    public boolean isJunior() {
        return isJunior;
    }

    @Override
    public String toString() {
        return "ClassGroup{" +
                "classID: '" + classID + '\'' +
                ", numOfRegStudent: " + numOfRegStudent +
                ", isJunior: " + isJunior +
                '}';
    }

    public static class classGroupBuilder{
        private String classID;
        private int numOfRegStudent;
        private boolean isJunior;

        public  classGroupBuilder setClassID(String classID) {
            this.classID = classID;
            return this;
        }

        public  classGroupBuilder setNumOfRegStudent(int numOfRegStudent) {
            this.numOfRegStudent = numOfRegStudent;
            return this;
        }

        public  classGroupBuilder setJunior(boolean junior) {
            isJunior = junior;
            return this;
        }
        public  classGroupBuilder copy(ClassGroup classGroup){
            this.classID = classGroup.classID;
            this.numOfRegStudent = classGroup.numOfRegStudent;
            this.isJunior = classGroup.isJunior;
            return this;
        }

        public ClassGroup build(){
            return new ClassGroup(this);
        }
    }
}
