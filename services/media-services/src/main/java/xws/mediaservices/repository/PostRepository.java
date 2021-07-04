package xws.mediaservices.repository;

import xws.mediaservices.model.Post;
import org.springframework.data.repository.CrudRepository;
import xws.mediaservices.model.User;


public interface PostRepository extends CrudRepository<Post,Long> {
}
