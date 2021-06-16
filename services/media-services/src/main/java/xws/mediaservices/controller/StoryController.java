package xws.mediaservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xws.mediaservices.model.Story;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.UserRepository;
import xws.mediaservices.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class StoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    Logger LOGGER = LoggerFactory.getLogger(StoryController.class);

    @GetMapping(value = "/media/stories")
    public ResponseEntity<List<Story>> getUserStories(@Context HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User d = (User) session.getAttribute("client");
        System.out.println("GET STORIES");
        System.out.println("USER: " + d.getEmail());
        System.out.println("STORIES: " + d.getStories());

        return new ResponseEntity<>(new ArrayList<>(d.getStories()), HttpStatus.OK);
    }
}
