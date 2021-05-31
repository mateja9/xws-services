package xws.mediaservices.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Story{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PathOfContent", nullable = false)
    private String pathOfContent;

    @Column(name = "Tag", nullable = false)
    private String tag;

    @Column(name = "IsHighlited", nullable = false)
    private boolean isHighlited;

    @Column(name = "StartTime", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "EndTime", nullable = false)
    private LocalDateTime endTime;



    public Story(String pathOfContent, String tag, boolean isHighlited, LocalDateTime startTime, LocalDateTime endTime) {
        this.pathOfContent = pathOfContent;
        this.tag = tag;
        this.isHighlited = isHighlited;
        this.startTime = startTime;
        this.endTime = endTime;
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
}
