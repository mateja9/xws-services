package xws.mediaservices.service;

import xws.mediaservices.dto.CommentDTO;
import xws.mediaservices.model.Comment;
import xws.mediaservices.model.User;

import java.util.List;

public interface CommentService {
    Comment findById(Long id);

    Comment createComment(CommentDTO commentDTO, User user);
    List<Comment> getCommentsForPost(Long id);
}
