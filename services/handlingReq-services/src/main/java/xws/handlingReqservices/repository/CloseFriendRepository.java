package xws.handlingReqservices.repository;

import org.springframework.data.repository.CrudRepository;
import xws.handlingReqservices.model.UserCloseFriend;
import xws.handlingReqservices.model.UserFollow;

public interface CloseFriendRepository extends CrudRepository<UserCloseFriend, Long> {
}
