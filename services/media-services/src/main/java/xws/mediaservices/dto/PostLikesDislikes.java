package xws.mediaservices.dto;

public class PostLikesDislikes {
    private int numberOfLikes;
    private int numberOfDislikes;

    public PostLikesDislikes(int numberOfLikes, int numberOfDislikes) {
        this.numberOfLikes = numberOfLikes;
        this.numberOfDislikes = numberOfDislikes;
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
