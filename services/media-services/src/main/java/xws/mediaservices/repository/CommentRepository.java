package xws.mediaservices.repository;

import org.springframework.data.repository.CrudRepository;
import xws.mediaservices.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment,Long> {

    List<Comment> findAllByPostId(Long id);
}

