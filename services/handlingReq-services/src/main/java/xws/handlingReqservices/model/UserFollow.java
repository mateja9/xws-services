package xws.handlingReqservices.model;

import javax.persistence.*;

@Entity
public class UserFollow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userFromId;

    @Column
    private Long userToId;

    @Column(name="isActive", nullable = false)
    private boolean isActive;

    @Column(name="status", nullable = false)
    private StatusFollowing status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserFrom() {
        return userFromId;
    }

    public void setUserFrom(Long usernameFrom) {
        this.userFromId = usernameFrom;
    }

    public Long getUserTo() {
        return userToId;
    }

    public void setUserTo(Long userTo) {
        this.userToId = userTo;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public StatusFollowing getStatus() {
        return status;
    }

    public void setStatus(StatusFollowing status) {
        this.status = status;
    }
}
