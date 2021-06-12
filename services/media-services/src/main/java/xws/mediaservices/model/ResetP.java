package xws.mediaservices.model;

import javax.persistence.Column;

public class ResetP {

    private  String email;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public ResetP(String email) {
        this.email = email;

    }

    public ResetP() {
    }
}
