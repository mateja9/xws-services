package xws.handlingReqservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import xws.handlingReqservices.dto.CloseFriendDTO;
import xws.handlingReqservices.model.UserCloseFriend;
import xws.handlingReqservices.model.UserFollow;
import xws.handlingReqservices.repository.CloseFriendRepository;

@Service
public class CloseFriendImpl implements  CloseFriendService{

    @Autowired
    private CloseFriendRepository closeFriendRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserCloseFriend add(CloseFriendDTO closeFriendDTO) {
        UserCloseFriend newUserCloseFriend = new UserCloseFriend();

        newUserCloseFriend.setUser(closeFriendDTO.getUser());
        newUserCloseFriend.setCloseFriend(closeFriendDTO.getCloseFriend());

        newUserCloseFriend.setActive(true);

        return closeFriendRepository.save(newUserCloseFriend);
    }

    @Override
    public UserCloseFriend checkIsCloseFriend(CloseFriendDTO closeFriendDTO) {
        for(UserCloseFriend uf : closeFriendRepository.findAll()) {
            if(uf.getUser() == closeFriendDTO.getUser() && uf.getCloseFriend() == closeFriendDTO.getCloseFriend()) {
                return uf;
            }
        }
        return  null;
    }
}
