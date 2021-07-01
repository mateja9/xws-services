package xws.handlingReqservices.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import xws.handlingReqservices.model.UserFollow;

public interface UserFollowRepository extends CrudRepository<UserFollow, Long> {

    @Query("SELECT count(u) FROM UserFollow u WHERE u.userToId = :userId AND u.isActive = true AND u.status = 'accepted'")
    Integer countFollowers(@Param("userId") Long userId);

    @Query("SELECT count(u) FROM UserFollow u WHERE u.userFromId = :userId AND u.isActive = true AND u.status = 'accepted'")
    Integer countFollowings(@Param("userId") Long userId);
}

