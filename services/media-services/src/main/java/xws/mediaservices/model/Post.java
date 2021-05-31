package xws.mediaservices.model;

import javax.persistence.*;

@Entity
public class Post{

    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PathOfContent", nullable = false)
    private String pathOfContent;

    @Column(name = "Tag", nullable = false)
    private String tag;

    @Column(name = "Description", nullable = false)
    private String description;

    @Column(name = "Location", nullable = false)
    private String location;

    @Column(name = "NumberOfLikes", nullable = false)
    private int numberOfLikes;

    @Column(name = "NumberOfDislikes", nullable = false)
    private int numberOfDislikes;

    //proveriti da li treba staviti neku anotaciju
    // private Set<Post> favouriteContent;
    // private Set<Post> likedContent;
    // private Set<Post> dislikedContent;


    public Post(String pathOfContent, String tag, String description, String location, int numberOfLikes, int numberOfDislikes) {
        this.pathOfContent = pathOfContent;
        this.tag = tag;
        this.description = description;
        this.location = location;
        this.numberOfLikes = numberOfLikes;
        this.numberOfDislikes = numberOfDislikes;
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
}

