package xws.handlingReqservices.repository;

import org.springframework.data.repository.CrudRepository;
import xws.handlingReqservices.model.UserFollow;

public interface UserFollowRepository extends CrudRepository<UserFollow, Long> {
}
