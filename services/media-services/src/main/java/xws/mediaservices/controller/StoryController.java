package xws.mediaservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xws.mediaservices.model.Story;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.UserRepository;
import xws.mediaservices.service.StoryService;
import xws.mediaservices.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.core.Context;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class StoryController {

    @Autowired
    private UserService userService;

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserRepository userRepository;

    Logger LOGGER = LoggerFactory.getLogger(StoryController.class);

    @GetMapping(value = "/media/stories")
    public ResponseEntity<List<Story>> getUserStories(@Context HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("client");
        System.out.println("GET STORIES");
        System.out.println("USER: " + user.getEmail());
        System.out.println("STORIES: " + user.getStories());

        return new ResponseEntity<>(new ArrayList<>(user.getStories()), HttpStatus.OK);
    }

    @PostMapping(value = "/media/stories", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createStory(@Context HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("client");

        Part partImage = request.getPart("file");
        Part partCloseFriends = request.getPart("onlyCloseFriends");

        String onlyCloseFriendsString = getPartAsString(partCloseFriends);
        Boolean onlyCloseFriends = onlyCloseFriendsString.equals("yes");

        System.out.println("CREATE STORY");
        System.out.println("USER: " + user.getEmail());
        System.out.println(onlyCloseFriends);

        String fileName = partImage.getSubmittedFileName();
        String extension = "";

        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = fileName.substring(index + 1);
        }

        InputStream inputStream = partImage.getInputStream();
        Story story = storyService.createStory(inputStream, extension, onlyCloseFriends, user);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    private String getPartAsString(Part partCloseFriends) throws IOException {
        String result;
        InputStream is = partCloseFriends.getInputStream();

        String text = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));

        return text.trim();
    }
}
