package xws.handlingReqservices.service;

import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.UserFollow;

import java.util.List;

public interface UserFollowService {

    UserFollow createUserFollow(UserFollowDTO userFollowDTO);

    String accept(Long id, String username);

    String reject(Long id, String username);

    UserFollow unfollow(Long id);

    UserFollow checkIsFollow(UserFollowDTO followRequestDTO);

    List<Integer> getFollowersAndFollowing(Long userId);

    List<String> getFollowersRequests(Long userId);

    List<Long> getMyFollowersList(Long userId);


}
