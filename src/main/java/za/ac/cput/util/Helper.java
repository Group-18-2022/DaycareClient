package za.ac.cput.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import java.util.UUID;

public class Helper {
    public static String generateID() {
        return UUID.randomUUID().toString();
    }

    public static boolean isEmptyOrNull(String s) {
        return StringUtils.isEmpty(s);
    }

    public static void isNull(String paramName, Object o)
    {
        if(o == null) throw new IllegalArgumentException(
                String.format("Invalid value for params: %s", paramName)
        );
    }
    public static void checkStringParam(String paramName, String paramValue)
    {
        if(isEmptyOrNull(paramValue))
            throw new IllegalArgumentException(
                    String.format("Invalid value for params: %s", paramName)
            );
    }

    public static boolean isValidEmail(String paramName, String emailAddress){
        EmailValidator validateEmail = EmailValidator.getInstance();
        if (!validateEmail.isValid(emailAddress))
            throw new IllegalArgumentException(
                    String.format("Invalid value for params: %s", paramName)
            );
        return validateEmail.isValid(emailAddress);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if(phoneNumber.length() == 10 && !StringUtils.isNumeric(phoneNumber))
            throw new IllegalArgumentException(
                    String.format("Invalid Phone Number: %s", phoneNumber)
            );

        return true;
    }
}
