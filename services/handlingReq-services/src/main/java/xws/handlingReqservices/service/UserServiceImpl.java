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

        UserFollow userFollow =  userFollowRepository.getUserFollowByUsers(userFollowDTO.getUserFrom(), userFollowDTO.getUserTo());

        if(userFollow != null)
            return null;

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
    public String accept(Long id, String username) {
        User user =  restTemplate.exchange("/getByUsername/" + username, HttpMethod.GET,
                null, User.class).getBody();

        //username - userFrom
        //id - ulogovani korisnik - mi - userTo

        UserFollow follow = userFollowRepository.getUserFollowByUsers(user.getId(), id);
        //getFollowersRequests

        if(follow == null) {
            return null;
        }

        follow.setActive(false);
        follow.setStatus(StatusFollowing.accepted);

        userFollowRepository.save(follow);

        return "Success";  //true-catch bolje nego samo vracanje stringa ali nebitno sad
    }

    @Override
    public String reject(Long id, String username) {
        User user =  restTemplate.exchange("/getByUsername/" + username, HttpMethod.GET,
                null, User.class).getBody();

        //username - userFrom
        //id - ulogovani korisnik - mi - userTo

        UserFollow follow = userFollowRepository.getUserFollowByUsers(user.getId(), id);

        if(follow == null) {
            return null;
        }

        follow.setActive(false);
        follow.setStatus(StatusFollowing.rejected);

        userFollowRepository.save(follow);

        return "Success";  //true-catch bolje nego samo vracanje stringa ali nebitno sad
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
        System.out.println("Followers : " + countFollowers);
        System.out.println("Following : " + countFollowings);
        List<Integer> retVal = new ArrayList<>();
        retVal.add(countFollowers);
        retVal.add(countFollowings);

        return retVal;
    }

    @Override
    public List<String> getFollowersRequests(Long userId) {

        List<UserFollow> ret = userFollowRepository.getFollowersRequests(userId);
        List<String> retVal = new ArrayList<>();

        for (UserFollow u : ret) {
            retVal.add(restTemplate.exchange("/user/" + u.getUserFrom(), HttpMethod.GET,
                    null, User.class).getBody().getUsername());
        }

        return retVal;
    }

    @Override
    public List<Long> getMyFollowersList(Long userId) {

        List <Long> retVal = userFollowRepository.getMyFollowersList(userId);
        return retVal;
    }


}
