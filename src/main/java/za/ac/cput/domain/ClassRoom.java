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
        this.classroomNumber = builder.roomNumber;
        this.occupancy = builder.occupancy;
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
        private String roomNumber;
        private String occupancy;

        public Builder setClassroomId(String classroomId) {
            this.roomNumber = classroomId;
            return  this;
        }

        public Builder setOccupancy(String occupancy) {
            this.occupancy = occupancy;
            return this;
        }

        public Builder copy(ClassRoom crm)
        {
            this.occupancy = crm.classroomNumber;
            this.occupancy = crm.occupancy;
            return  this;

        }

        public ClassRoom build()
        {
            return new ClassRoom(this);
        }
    }
}
