package xws.handlingReqservices.model;

import javax.persistence.*;

@Entity
public class UserCloseFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private User closeFriend;

    @Column(name="isActive", nullable = false)
    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCloseFriend() {
        return closeFriend;
    }

    public void setCloseFriend(User closeFriend) {
        this.closeFriend = closeFriend;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
