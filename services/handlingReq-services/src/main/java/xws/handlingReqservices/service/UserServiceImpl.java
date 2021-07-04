package xws.handlingReqservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.StatusFollowing;
import xws.handlingReqservices.model.User;
import xws.handlingReqservices.model.UserFollow;
import xws.handlingReqservices.repository.UserFollowRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserFollowService {

    @Autowired
    private UserFollowRepository userFollowRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserFollow createUserFollow(UserFollowDTO userFollowDTO) {
        UserFollow newUserFollow = new UserFollow();

        //osoba koju treba da zapratim
        User userTo = restTemplate.exchange("/user/" + userFollowDTO.getUserTo(), HttpMethod.GET,
                null, User.class).getBody();

        newUserFollow.setUserFrom(userFollowDTO.getUserFrom());
        newUserFollow.setUserTo(userFollowDTO.getUserTo());

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

    @Override
    public UserFollow accept(Long id) {
        Optional<UserFollow> follow = userFollowRepository.findById(id);

        if(!follow.isPresent()) {
            return null;
        }

        UserFollow userFollow = follow.get();
        userFollow.setActive(true);
        userFollow.setStatus(StatusFollowing.accepted);

        userFollowRepository.save(userFollow);

        return userFollow;
    }

    @Override
    public UserFollow checkIsFollow(UserFollowDTO followRequestDTO) {
        for(UserFollow uf : userFollowRepository.findAll()) {
            if(uf.getUserFrom() == followRequestDTO.getUserFrom() && uf.getUserTo() == followRequestDTO.getUserTo() && uf.getStatus() == StatusFollowing.accepted && uf.isActive()) {
              return uf;
            }
        }
        return  null;
    }

    @Override
    public List<Integer> getFollowersAndFollowing(Long userId) {
        Integer countFollowers = userFollowRepository.countFollowers(userId);
        Integer countFollowings = userFollowRepository.countFollowings(userId);

        List<Integer> retVal = new ArrayList<>();
        retVal.add(countFollowers);
        retVal.add(countFollowings);

        return retVal;
    }
}
