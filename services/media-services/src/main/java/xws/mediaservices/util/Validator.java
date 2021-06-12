package xws.mediaservices.util;


import org.springframework.util.StringUtils;

public class Validator {

    public static void validateInputs(String name, String lastName, String phoneNumber,
                                      String email, String username, String password,
                                       String gender, String website, String bio,
                                      String dateofb) {
        if(StringUtils.isEmpty(name)) {
            throw new IllegalArgumentException("name");
        }
        if(StringUtils.isEmpty(lastName)) {
            throw new IllegalArgumentException("lastName");
        }
        if(StringUtils.isEmpty(phoneNumber)) {
            throw new IllegalArgumentException("phoneNumber");
        }
        if(StringUtils.isEmpty(email)) {
            throw new IllegalArgumentException("email");
        }
        if(StringUtils.isEmpty(username)) {
            throw new IllegalArgumentException("username");
        }
        if(StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("password");
        }
        if(StringUtils.isEmpty(gender)) {
            throw new IllegalArgumentException("gender");
        }
        if(StringUtils.isEmpty(website)) {
            throw new IllegalArgumentException("website");
        }
        if(StringUtils.isEmpty(bio)) {
            throw new IllegalArgumentException("bio");
        }
        if(StringUtils.isEmpty(dateofb)) {
            throw new IllegalArgumentException("dateofb");
        }


    }

}

