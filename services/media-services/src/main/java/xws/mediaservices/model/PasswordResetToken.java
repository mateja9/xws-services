package xws.mediaservices.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="passwordResetToken")
public class PasswordResetToken {
    private static final long EXPIRATION = 30L * 24L * 60L * 60L * 1000L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "token_id")
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "expiryDate")
    private Date expiryDate;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String token, User user, Date expiryDate) {
        this.token = token;
        this.user = user;
        this.expiryDate = expiryDate;
    }

    public static long getEXPIRATION() {
        return EXPIRATION;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public PasswordResetToken(String token, User user) {
        this.token = token;
        this.user = user;
        expiryDate = new Date();
        long currentTime = System.currentTimeMillis();
        expiryDate.setTime(currentTime+getEXPIRATION());
    }

}

