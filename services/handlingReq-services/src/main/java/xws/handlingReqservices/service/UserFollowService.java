package xws.handlingReqservices.service;

import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.UserFollow;

public interface UserFollowService {

    UserFollow createUserFollow(UserFollowDTO userFollowDTO);

    UserFollow unfollow(Long id);
}
