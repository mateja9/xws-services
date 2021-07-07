package xws.mediaservices.dto;

import xws.mediaservices.model.Comment;
import xws.mediaservices.model.Post;

import java.util.List;

public class PostComment {
    private Post post;
    private List<Comment> comments;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public PostComment(Post post, List<Comment> comments) {
        this.post = post;
        this.comments = comments;
    }

    public PostComment() {
    }
}
