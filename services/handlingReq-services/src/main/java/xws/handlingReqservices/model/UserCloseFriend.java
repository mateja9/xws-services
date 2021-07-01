package xws.handlingReqservices.model;

import javax.persistence.*;

@Entity
public class UserCloseFriend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long user;

    @Column
    private Long closeFriend;

    @Column(name="isActive", nullable = false)
    private boolean isActive;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    public Long getCloseFriend() {
        return closeFriend;
    }

    public void setCloseFriend(Long closeFriend) {
        this.closeFriend = closeFriend;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
