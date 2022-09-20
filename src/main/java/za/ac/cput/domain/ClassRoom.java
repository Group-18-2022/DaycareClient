package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class ClassRoom
{
    @Id
    private String classroomId;
    @NotNull
    private String roomNumber;


    private ClassRoom(Builder builder)
    {
        this.classroomId = builder.classroomId;
        this.roomNumber = builder.roomNumber;
    }

    protected ClassRoom(){}

    public String getRoomNumber() {
        return roomNumber;
    }

    @Override
    public String toString() {
        return "ClassRoom{" +
                "classroomId='" + classroomId + '\'' +
                ", roomNumber='" + roomNumber + '\'' +
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
            this.classroomId = crm.classroomId;
            this.roomNumber = crm.roomNumber;
            return  this;

        }

        public ClassRoom build()
        {
            return new ClassRoom(this);
        }
    }
}
