package xws.mediaservices.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import xws.mediaservices.model.Post;
import xws.mediaservices.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
    User findByUsername (String username);

}