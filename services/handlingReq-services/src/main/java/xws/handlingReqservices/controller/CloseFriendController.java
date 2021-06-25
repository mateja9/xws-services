package xws.handlingReqservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import xws.handlingReqservices.dto.CloseFriendDTO;
import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.UserCloseFriend;
import xws.handlingReqservices.model.UserFollow;
import xws.handlingReqservices.service.CloseFriendService;

@RestController
public class CloseFriendController {

    @Autowired
    private CloseFriendService closeFriendService;

    @PostMapping(value = "/closeFriend/add")
    public ResponseEntity<UserCloseFriend> add(@RequestBody CloseFriendDTO closeFriendDTO)
    {
        return new ResponseEntity<UserCloseFriend>(closeFriendService.add(closeFriendDTO), HttpStatus.OK);
    }

}
