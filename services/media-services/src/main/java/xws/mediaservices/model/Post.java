package xws.mediaservices.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "paths")
    @ElementCollection(targetClass=String.class, fetch = FetchType.LAZY)
    private Set<String> path = new HashSet<String>();

    @Column(name = "PathOfContent", nullable = false)
    private String pathOfContent;

    @Column(name = "tags")
    private String tags;

    @Column(name = "Description")
    private String description;

    @Column(name = "Location")
    private String location;

    @Column(name = "NumberOfLikes", nullable = false)
    private int numberOfLikes;

    @Column(name = "NumberOfDislikes", nullable = false)
    private int numberOfDislikes;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Comment> comments = new HashSet<Comment>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    private Set<User> users;

    @Column(name = "StartTime", nullable = false)
    private LocalDateTime startTime;

    public String getPathOfContent() {
        return pathOfContent;
    }

    public void setPathOfContent(String pathOfContent) {
        this.pathOfContent = pathOfContent;
    }

    public Post() {
    }

    public Post(String username, Set<String> path, String tags, String description, String location,
                int numberOfLikes, int numberOfDislikes, Set<Comment> comments) {
        this.username = username;
        this.path = path;
        this.tags = tags;
        this.description = description;
        this.location = location;
        this.numberOfLikes = numberOfLikes;
        this.numberOfDislikes = numberOfDislikes;
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<String> getPath() {
        return path;
    }

    public void setPath(Set<String> path) {
        this.path = path;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public int getNumberOfDislikes() {
        return numberOfDislikes;
    }

    public void setNumberOfDislikes(int numberOfDislikes) {
        this.numberOfDislikes = numberOfDislikes;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }


}

