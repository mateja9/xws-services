package xws.handlingReqservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.handlingReqservices.dto.CloseFriendDTO;
import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.UserCloseFriend;
import xws.handlingReqservices.model.UserFollow;
import xws.handlingReqservices.service.CloseFriendService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class CloseFriendController {

    @Autowired
    private CloseFriendService closeFriendService;

    @PostMapping(value = "/closeFriend/add")
    public ResponseEntity<UserCloseFriend> add(@RequestBody CloseFriendDTO closeFriendDTO)
    {
        return new ResponseEntity<UserCloseFriend>(closeFriendService.add(closeFriendDTO), HttpStatus.OK);
    }

    @PostMapping(value = "/closeFriend/checkIsCloseFriend")
    public ResponseEntity<UserCloseFriend> checkIsCloseFriend(@RequestBody CloseFriendDTO closeFriendDTO)
    {
        return new ResponseEntity<UserCloseFriend>(closeFriendService.checkIsCloseFriend(closeFriendDTO), HttpStatus.OK);
    }

}
