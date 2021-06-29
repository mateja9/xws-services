package xws.mediaservices.service;

import xws.mediaservices.dto.CommentDTO;
import xws.mediaservices.model.Comment;

import java.util.List;

public interface CommentService {
    Comment findById(Long id);

    Comment createComment(CommentDTO commentDTO);
    List<Comment> getComentsForPost(Long id);
}
