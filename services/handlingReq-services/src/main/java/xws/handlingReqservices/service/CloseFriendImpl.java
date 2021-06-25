package xws.handlingReqservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.handlingReqservices.dto.CloseFriendDTO;
import xws.handlingReqservices.model.User;
import xws.handlingReqservices.model.UserCloseFriend;
import xws.handlingReqservices.repository.CloseFriendRepository;
import xws.handlingReqservices.repository.UserRepository;

@Service
public class CloseFriendImpl implements  CloseFriendService{

    @Autowired
    private CloseFriendRepository closeFriendRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserCloseFriend add(CloseFriendDTO closeFriendDTO) {
        UserCloseFriend newUserCloseFriend = new UserCloseFriend();

        User closeFriend = userRepository.findById(closeFriendDTO.getCloseFriend()).get();

        newUserCloseFriend.setUser(userRepository.findById(closeFriendDTO.getUser()).get());
        newUserCloseFriend.setCloseFriend(closeFriend);

        newUserCloseFriend.setActive(true);

        return newUserCloseFriend;

    }
}
