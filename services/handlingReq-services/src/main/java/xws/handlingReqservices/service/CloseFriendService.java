package xws.handlingReqservices.service;


import xws.handlingReqservices.dto.CloseFriendDTO;
import xws.handlingReqservices.model.UserCloseFriend;
import xws.handlingReqservices.model.UserFollow;

public interface CloseFriendService {
    UserCloseFriend add(CloseFriendDTO closeFriendDTO);

    UserCloseFriend checkIsCloseFriend(CloseFriendDTO closeFriendDTO);

}
