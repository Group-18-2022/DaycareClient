package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ClassRoom
{
    @Id
    private String classroomNumber;
    @NotNull
    private String occupancy;


    private ClassRoom(Builder builder)
    {
        this.classroomNumber = builder.classroomId;
        this.occupancy = builder.roomNumber;
    }

    protected ClassRoom(){}

    public String getOccupancy() {
        return occupancy;
    }

    public String getClassroomNumber() {
        return classroomNumber;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "classroomId='" + classroomNumber + '\'' +
                ", roomNumber='" + occupancy + '\'' +
                '}';
    }



    public static class Builder
    {
        private String classroomId;
        private String roomNumber;

        public Builder setClassroomId(String classroomId) {
            this.classroomId = classroomId;
            return  this;
        }

        public Builder setRoomNumber(String roomNumber) {
            this.roomNumber = roomNumber;
            return this;
        }

        public Builder copy(ClassRoom crm)
        {
            this.classroomId = crm.classroomNumber;
            this.roomNumber = crm.occupancy;
            return  this;

        }

        public ClassRoom build()
        {
            return new ClassRoom(this);
        }
    }
}
