package xws.mediaservices.model;

import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
public class Story{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PathOfContent", nullable = false)
    private String pathOfContent;

    @Column(name = "Tag")
    private String tag;

    @Column(name = "IsHighlited", nullable = false)
    private boolean isHighlited;

    @Column(name = "OnlyCloseFriends", nullable = false)
    private boolean onlyCloseFriends;

    @Column(name = "StartTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "EndTime", nullable = false)
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Story() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPathOfContent() {
        return pathOfContent;
    }

    public void setPathOfContent(String pathOfContent) {
        this.pathOfContent = pathOfContent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isHighlited() {
        return isHighlited;
    }

    public void setHighlited(boolean highlited) {
        isHighlited = highlited;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public boolean isOnlyCloseFriends() {
        return onlyCloseFriends;
    }

    public void setOnlyCloseFriends(boolean onlyCloseFriends) {
        this.onlyCloseFriends = onlyCloseFriends;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isPubliclyVisible() {
        Duration dt = Duration.between(startTime, LocalDateTime.now());
        return dt.getSeconds() < 86400;
    }
}
