package xws.handlingReqservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.StatusFollowing;
import xws.handlingReqservices.model.User;
import xws.handlingReqservices.model.UserFollow;
import xws.handlingReqservices.repository.UserFollowRepository;
import xws.handlingReqservices.repository.UserRepository;
@Service
public class UserServiceImpl implements UserFollowService {

    @Autowired
    private UserFollowRepository userFollowRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserFollow createUserFollow(UserFollowDTO userFollowDTO) {
        UserFollow newUserFollow = new UserFollow();

        User userTo = userRepository.findById(userFollowDTO.getUserTo()).get();

        newUserFollow.setUserFrom(userRepository.findById(userFollowDTO.getUserFrom()).get());
        newUserFollow.setUserTo(userTo);

        newUserFollow.setActive(true);
        newUserFollow.setStatus(StatusFollowing.accepted);

        newUserFollow = userFollowRepository.save(newUserFollow);

        return newUserFollow;
    }
}
