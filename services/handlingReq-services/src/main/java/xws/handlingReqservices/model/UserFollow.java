package xws.handlingReqservices.model;

import javax.persistence.*;

@Entity
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User userFrom;

    @ManyToOne
    private User userTo;

    @Column(name="isActive", nullable = false)
    private boolean isActive;

    @Column(name="status", nullable = false)
    private Enum<StatusFollowing> status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(User usernameFrom) {
        this.userFrom = usernameFrom;
    }

    public User getUserTo() {
        return userTo;
    }

    public void setUserTo(User userTo) {
        this.userTo = userTo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Enum<StatusFollowing> getStatus() {
        return status;
    }

    public void setStatus(Enum<StatusFollowing> status) {
        this.status = status;
    }
}
