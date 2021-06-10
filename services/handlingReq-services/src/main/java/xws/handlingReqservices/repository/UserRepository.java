package xws.handlingReqservices.repository;

import org.springframework.data.repository.CrudRepository;
import xws.handlingReqservices.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
