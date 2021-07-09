package xws.mediaservices.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import xws.mediaservices.model.Post;
import xws.mediaservices.model.Story;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.StoryRepository;
import xws.mediaservices.repository.UserRepository;
import xws.mediaservices.service.StoryService;
import xws.mediaservices.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.ws.rs.core.Context;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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

    @Autowired
    private StoryRepository storyRepository;

    @Value("${media.storage}")
    private String storageDirectoryPath;

    @Autowired
    private RestTemplate restTemplate;

    Logger LOGGER = LoggerFactory.getLogger(StoryController.class);

    @RequestMapping(value = "/media/file/{filename}", method = RequestMethod.GET)
    public void getImageAsByteArray(@PathVariable("filename") String filename, HttpServletResponse response) throws IOException {

        Path path = Paths.get(storageDirectoryPath, filename);
        InputStream in = Files.newInputStream(path);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping(value = "/user/{userId}/publicStories")
    public ResponseEntity<List<Story>> getPublicUserStories(@PathVariable("userId") String userIdString) throws Exception {
        Long userId;
        try {
            userId = Long.valueOf(userIdString);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        User user = userRepository.findById(userId).get();
        System.out.println("USER: " + user.getEmail());
        Set<Story> storiesSet = user.getStories();
        List<Story> stories = new ArrayList<>(storiesSet);

        List<Story> publicStories = stories.stream()
                .filter(story -> story.isPubliclyVisible())
                .collect(Collectors.toList());

        publicStories.sort(new Comparator<Story>() {
            @Override
            public int compare(Story o1, Story o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        });

        return new ResponseEntity<>(new ArrayList<>(publicStories), HttpStatus.OK);
    }

    @GetMapping(value = "/user/{userId}/highlightStories")
    public ResponseEntity<List<Story>> getHighlightUserStories(@PathVariable("userId") String userIdString) throws Exception {
        Long userId;
        try {
            userId = Long.valueOf(userIdString);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        User user = userRepository.findById(userId).get();
        System.out.println("USER: " + user.getEmail());
        Set<Story> storiesSet = user.getStories();
        List<Story> stories = new ArrayList<>(storiesSet);

        List<Story> highlightStories = stories.stream()
                .filter(story -> story.isHighlited())
                .collect(Collectors.toList());

        highlightStories.sort(new Comparator<Story>() {
            @Override
            public int compare(Story o1, Story o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        });

        return new ResponseEntity<>(new ArrayList<>(highlightStories), HttpStatus.OK);
    }

    @GetMapping(value = "/media/stories")
    public ResponseEntity<List<Story>> getUserStories(@Context HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("client");
        System.out.println("GET STORIES");
        System.out.println("SESSION-USER: " + sessionUser.getEmail());

        User user = userRepository.findByEmail(sessionUser.getEmail());
        System.out.println("USER: " + user.getEmail());
        Set<Story> storiesSet = user.getStories();
        List<Story> stories = new ArrayList<>(storiesSet);
        stories.sort(new Comparator<Story>() {
            @Override
            public int compare(Story o1, Story o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        });

        System.out.println("STORIES: " + stories);
        return new ResponseEntity<>(new ArrayList<>(stories), HttpStatus.OK);
    }

    @PostMapping(value = "/media/stories", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createStory(@Context HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("client");

        Part partImage = request.getPart("file");
        Part partCloseFriends = request.getPart("onlyCloseFriends");
        Part partHighlighted = request.getPart("highlighted");
        Part partTag = request.getPart("tag");

        String onlyCloseFriendsString = getPartAsString(partCloseFriends);
        Boolean onlyCloseFriends = onlyCloseFriendsString.equals("yes");

        String highlightedString = getPartAsString(partHighlighted);
        Boolean highlighted = highlightedString.equals("yes");

        String tags = getPartAsString(partHighlighted);

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
        Story story = storyService.createStory(inputStream, extension, onlyCloseFriends, highlighted, tags, user);

        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    private String getPartAsString(Part part) throws IOException {
        String result;
        InputStream is = part.getInputStream();

        String text = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));

        return text.trim();
    }


    @GetMapping(value = "/media/getStoriesForFeed")
    public ResponseEntity<List<Story>> getStoriesForFeed(@Context HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("client");
        System.out.println("GET STORIES");
        System.out.println("SESSION-USER: " + sessionUser.getEmail());

        User user = userRepository.findByEmail(sessionUser.getEmail());



        Long[] response =
                restTemplate.getForEntity(
                        "/userFollow/getMyFollowersList/"+user.getId(),
                        Long[].class).getBody();

        Set<Story> storySet = new HashSet<>();
        for (Long r: response){
            Set<Story> stories = userService.findById(r).getStories();
            storySet.addAll(stories);
        }


        System.out.println("USER: " + user.getEmail());
        Set<Story> storiesSet = user.getStories();
        List<Story> stories = new ArrayList<>(storiesSet);
        stories.sort(new Comparator<Story>() {
            @Override
            public int compare(Story o1, Story o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        });

        System.out.println("STORIES: " + stories);
        return new ResponseEntity<>(new ArrayList<>(stories), HttpStatus.OK);
    }

}
