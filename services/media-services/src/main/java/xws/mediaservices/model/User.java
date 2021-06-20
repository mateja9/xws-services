package xws.mediaservices.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mandatory")
    @Column(name = "Name", nullable = false)
    private String name;

    @NotBlank(message = "Last name is mandatory")
    @Column(name = "Last_name", nullable = false)
    private String lastname;

    @NotBlank(message = "Phone number is mandatory")
    @Column(name = "Phone_number", nullable = false)
    private String phoneNumber;

    @NotBlank(message = "Email is mandatory")
    @Column(name = "Email", nullable = false)
    private String email;

    @NotBlank(message = "Username is mandatory")
    @Column(name = "Username", nullable = false)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Role", nullable = false)
    private String rola;

    @NotBlank(message = "Gender is mandatory")
    @Column(name = "Gender", nullable = false)
    private String gender;

    @NotBlank(message = "Website is mandatory")
    @Column(name = "Website", nullable = false)
    private String website;

    @NotBlank(message = "Bio is mandatory")
    @Column(name = "Bio", nullable = false)
    private String bio;

    @NotBlank(message = "DateofB is mandatory")
    @Column(name = "DateofB", nullable = false)
    private String dateofb;

    @Column(name = "PrviPutLogovan", nullable = false)
    private boolean prviPutLogovan;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @JsonIgnore
    private Set<Story> stories = new HashSet<Story>();

    public User() {
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

    public boolean isPrviPutLogovan() {
        return prviPutLogovan;
    }

    public void setPrviPutLogovan(boolean prviPutLogovan) {
        this.prviPutLogovan = prviPutLogovan;
    }

    public Set<Story> getStories() {
        return stories;
    }

    public void setStories(Set<Story> stories) {
        this.stories = stories;
    }

    //    public User(String name, String lastname, String phoneNumber, String email, String username, String password, String rola, String gender ){
//        this.name=name;
//        this.lastname=lastname;
//        this.phoneNumber=phoneNumber;
//        this.email=email;
//        this.username=username;
//        this.password=password;
//        this.rola=rola;
//        this.gender=gender;
//    }


    public User(String name, String lastname, String phoneNumber, String email, String username, String password, String rola, String gender, String website, String bio, String dateofb, boolean prviPutLogovan) {
        this.name = name;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.username = username;
        this.password = password;
        this.rola = rola;
        this.gender = gender;
        this.website = website;
        this.bio = bio;
        this.dateofb = dateofb;
        this.prviPutLogovan = prviPutLogovan;
    }
}
