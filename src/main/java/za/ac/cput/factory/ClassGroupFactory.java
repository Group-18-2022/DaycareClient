package za.ac.cput.factory;

import za.ac.cput.domain.ClassGroup;

import za.ac.cput.util.Helper;

/**
 *
 * This is the ClassGroup Factory
 * @author Charles Moses Lemmert (220498385)
 *
 * **/
public class ClassGroupFactory {

    public static ClassGroup createClassGroup(String classID, int numOfRegStudent, boolean isJunior){


        Helper.checkStringParam("Class ID", classID);
        Helper.isNull("Is a Junoir",isJunior);
        if(numOfRegStudent <= 0)
            throw new IllegalArgumentException("Error: There cannot be a negative number of students.");

        return new ClassGroup.classGroupBuilder()
                .setClassID(classID)
                .setNumOfRegStudent(numOfRegStudent)
                .setJunior(isJunior)
                .build();
    }
}
