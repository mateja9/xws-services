package xws.mediaservices.repository;

import xws.mediaservices.model.Post;
import org.springframework.data.repository.CrudRepository;


public interface PostRepository extends CrudRepository<Post,Long> {
}
