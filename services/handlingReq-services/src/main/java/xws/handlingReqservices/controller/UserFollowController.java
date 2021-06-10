package xws.handlingReqservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.UserFollow;
import xws.handlingReqservices.service.UserFollowService;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class UserFollowController {

    @Autowired
    private UserFollowService userFollowService;

    @PostMapping(value = "/userFollow/createUserFollow")
    public ResponseEntity<UserFollow> createUserFollow(@RequestBody UserFollowDTO followRequestDTO)
    {
        return new ResponseEntity<UserFollow>(userFollowService.createUserFollow(followRequestDTO), HttpStatus.OK);
    }

}
