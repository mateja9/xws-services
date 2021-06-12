package xws.mediaservices.util;

import org.apache.commons.lang.StringUtils;

public class Validator {

    public static void validateInputs(String name, String lastName, String email, String password) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name");
        }
        if(StringUtils.isEmpty(lastName)) {
            throw new IllegalArgumentException("lastName");
        }
        if(StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("password");
        }
        if(StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("email");
        }
    }

}

