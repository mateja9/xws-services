package xws.mediaservices.repository;

import org.springframework.data.repository.CrudRepository;
import xws.mediaservices.model.Comment;

public interface CommentRepository extends CrudRepository<Comment,Long> {
}

