package za.ac.cput.factory;

import za.ac.cput.domain.GroupRoom;
import za.ac.cput.util.Helper;

public class GroupRoomFactory
{
    private String classRoomId, classGroupId;

    public static GroupRoom build(String classRoomId, String classGroupId)
    {
        if (Helper.isEmptyOrNull(classGroupId))
            throw new IllegalArgumentException("Classroom ID is invalid.");
        if (Helper.isEmptyOrNull(classGroupId))
            throw new IllegalArgumentException("Class Group ID is invalid.");

        return new  GroupRoom.Builder()
                .setClassRoomId(classRoomId)
                .setClassGroupId(classGroupId)
                .build();
    }
}
