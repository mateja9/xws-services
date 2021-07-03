package xws.handlingReqservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.handlingReqservices.dto.UserFollowDTO;
import xws.handlingReqservices.model.UserFollow;
import xws.handlingReqservices.service.UserFollowService;

import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class UserFollowController {

    @Autowired
    private UserFollowService userFollowService;

    @PostMapping(value = "/userFollow/createUserFollow")
    public ResponseEntity<UserFollow> createUserFollow(@RequestBody UserFollowDTO followRequestDTO)
    {
        return new ResponseEntity<UserFollow>(userFollowService.createUserFollow(followRequestDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/userFollow/unfollow/{id}")
    public ResponseEntity<UserFollow> unfollow(@PathVariable Long id)
    {
        return new ResponseEntity<UserFollow>(userFollowService.unfollow(id), HttpStatus.OK);
    }

    @PutMapping(value = "/userFollow/accept/{id}")
    public ResponseEntity<UserFollow> acceptFollow(@PathVariable Long id)
    {
        return new ResponseEntity<UserFollow>(userFollowService.accept(id), HttpStatus.OK);
    }

    @PostMapping(value = "/userFollow/checkIsFollow")
    public ResponseEntity<UserFollow> checkIsFollow(@RequestBody UserFollowDTO followRequestDTO)
    {
        return new ResponseEntity<UserFollow>(userFollowService.checkIsFollow(followRequestDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/userFollow/getFollowersAndFollowing/{userId}")
    public ResponseEntity<List<Integer>> getFollowersAndFollowing(@PathVariable Long userId)
    {
        return new ResponseEntity<List<Integer>>(userFollowService.getFollowersAndFollowing(userId), HttpStatus.OK);
    }
}
