package xws.mediaservices.repository;

import org.springframework.data.repository.CrudRepository;
import xws.mediaservices.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
}