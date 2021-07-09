package xws.handlingReqservices.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import xws.handlingReqservices.model.UserFollow;

import java.util.List;

public interface UserFollowRepository extends CrudRepository<UserFollow, Long> {

    @Query("SELECT count(u) FROM UserFollow u WHERE u.userToId = :userId AND u.isActive = true AND u.status = 'accepted'")
    Integer countFollowers(@Param("userId") Long userId);

    @Query("SELECT count(u) FROM UserFollow u WHERE u.userFromId = :userId AND u.isActive = true AND u.status = 'accepted'")
    Integer countFollowings(@Param("userId") Long userId);

    @Query("SELECT u FROM UserFollow u WHERE u.userFromId = :userFrom AND u.userToId = :userTo")
    UserFollow getUserFollowByUsers(@Param("userFrom") Long userFrom, @Param("userTo") Long userTo);

    @Query("SELECT u FROM UserFollow u WHERE u.userToId = :userTo  AND u.isActive = true") // status onWait AND u.status = 'onWait'
    List<UserFollow> getFollowersRequests (@Param("userTo") Long userTo);

    @Query("SELECT u.userFromId FROM UserFollow u WHERE u.userToId = :userId and u.status = 'accepted' AND u.isActive = false")
    List<Long> getMyFollowersList(@Param("userId") Long userId);
}

