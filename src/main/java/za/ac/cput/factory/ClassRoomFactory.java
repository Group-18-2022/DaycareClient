package za.ac.cput.factory;

import za.ac.cput.domain.ClassRoom;
import za.ac.cput.util.Helper;

public class ClassRoomFactory
{
    private String classroomId;
    private String roomNumber;

    public static ClassRoom build(String roomNumber)
    {
        String identifier = Helper.generateID();

        if(Helper.isEmptyOrNull(identifier))
            throw new IllegalArgumentException("Classroom Id is invalid.");
        if(Helper.isEmptyOrNull(roomNumber))
            throw new IllegalArgumentException("Room Number is invalid");

        return new ClassRoom.Builder()
                .setClassroomId(identifier)
                .setRoomNumber(roomNumber)
                .build();
    }
}
