package xws.handlingReqservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.StatusFollowing;
import xws.handlingReqservices.model.User;
import xws.handlingReqservices.model.UserFollow;
import xws.handlingReqservices.repository.UserFollowRepository;
import xws.handlingReqservices.repository.UserRepository;

import java.util.Optional;

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

        if(userTo.isPrivate())
            newUserFollow.setStatus(StatusFollowing.onWait);
        else
            newUserFollow.setStatus(StatusFollowing.accepted);

        newUserFollow.setActive(true);

        newUserFollow = userFollowRepository.save(newUserFollow);

        return newUserFollow;
    }

    @Override
    public UserFollow unfollow(Long id) {
        Optional<UserFollow> follow = userFollowRepository.findById(id);

        if(!follow.isPresent()) {
            return null;
        }

        UserFollow userFollow = follow.get();
        userFollow.setActive(false);
        userFollow.setStatus(StatusFollowing.rejected);

        userFollowRepository.save(userFollow);


        return userFollow;
    }
}
