package za.ac.cput.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;

@Entity
@IdClass(GroupRoom.GroupRoomID.class)
public class GroupRoom implements Serializable
{
    @Id
    private String classRoomId, classGroupId;

    public GroupRoom(Builder builder)
    {
        this.classRoomId = builder.classRoomId;
        this.classGroupId = builder.classGroupId;
    }

    protected GroupRoom(){}

    public String getClassRoomId() {
        return classRoomId;
    }
    public String getClassGroupId() {
        return classGroupId;
    }

    @Override
    public String toString() {
        return "GroupRoom{" +
                "classRoomId='" + classRoomId + '\'' +
                ", classGroupId='" + classGroupId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupRoom groupRoom = (GroupRoom) o;
        return classRoomId.equals(groupRoom.classRoomId) && classGroupId.equals(groupRoom.classGroupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(classRoomId, classGroupId);
    }

    public static class Builder
    {
        private String classRoomId, classGroupId;

        public Builder setClassRoomId(String classRoomId)
        {
            this.classRoomId = classRoomId;
            return this;
        }

        public Builder setClassGroupId(String classGroupId)
        {
            this.classGroupId = classGroupId;
            return this;
        }

        public GroupRoom build()
        {
            return new GroupRoom(this);
        }
    }



    public static class GroupRoomID implements Serializable {
        private String classRoomId, classGroupId;

        public GroupRoomID(String classRoomId, String classGroupId) {
            this.classGroupId = classRoomId;
            this.classRoomId = classGroupId;
        }

        protected GroupRoomID() {
        }

        public String getClassRoomId() {
            return classRoomId;
        }

        public String getClassGroupId() {
            return classGroupId;
        }
    }
}
