package za.ac.cput.factory;

import za.ac.cput.domain.ClassRoom;
import za.ac.cput.util.Helper;

public class ClassRoomFactory
{
    private String classroomId;
    private String roomNumber;

    public static ClassRoom build(String roomNumber,String occupancy)
    {

        if(Helper.isEmptyOrNull(roomNumber))
            throw new IllegalArgumentException("Classroom Id is invalid.");
        if(Helper.isEmptyOrNull(occupancy))
            throw new IllegalArgumentException("Occupancy number is invalid");

        return new ClassRoom.Builder()
                .setClassroomId(roomNumber)
                .setOccupancy(occupancy)
                .build();
    }
}
