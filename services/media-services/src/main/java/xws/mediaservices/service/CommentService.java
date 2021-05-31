package xws.mediaservices.service;

import xws.mediaservices.model.Comment;

public interface CommentService {
    Comment findById(Long id);
}
