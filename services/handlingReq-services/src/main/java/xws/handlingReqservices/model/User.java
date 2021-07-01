package xws.handlingReqservices.model;

import javax.persistence.*;

/*@Entity*/
public class User {

    private Long id;

    private String name;

    private String lastname;

    private String phoneNumber;

    private String email;

    private String username;

    private String password;


    private String rola;

    private String gender;

    private String website;

    private String bio;

    private String dateofb;

    private boolean isPrivate = false;

    public User() {
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getDateofb() {
        return dateofb;
    }

    public void setDateofb(String dateofb) {
        this.dateofb = dateofb;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public User(String name, String lastname, String phoneNumber, String email, String username, String password, String rola, String gender,String website,String bio, String dateofb ){
        this.name=name;
        this.lastname=lastname;
        this.phoneNumber=phoneNumber;
        this.email=email;
        this.username=username;
        this.password=password;
        this.rola=rola;
        this.gender=gender;
        this.website=website;
        this.bio=bio;
        this.dateofb=dateofb;
    }
}
