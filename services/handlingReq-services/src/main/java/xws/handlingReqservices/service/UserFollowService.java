package xws.handlingReqservices.service;

import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.UserFollow;

import java.util.List;

public interface UserFollowService {

    UserFollow createUserFollow(UserFollowDTO userFollowDTO);

    UserFollow accept(Long id);

    UserFollow unfollow(Long id);

    UserFollow checkIsFollow(UserFollowDTO followRequestDTO);

    List<Integer> getFollowersAndFollowing(Long userId);
}
