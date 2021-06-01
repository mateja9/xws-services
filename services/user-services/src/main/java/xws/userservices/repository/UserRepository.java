package xws.userservices.repository;

import org.springframework.data.repository.CrudRepository;
import xws.userservices.model.User;

public interface UserRepository extends CrudRepository<User,Long> {
    User findByEmail(String email);
}