package xws.mediaservices.controller;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xws.mediaservices.dto.PostDTO;
import xws.mediaservices.model.Post;;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.UserRepository;
import xws.mediaservices.service.PostService;

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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    UserRepository userRepository;
    @Value("${media.storage}")
    private String storageDirectoryPath;

    @PostMapping(value="media/createPost")
    ResponseEntity<String> createPost (@Context HttpServletRequest request) throws Exception {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("client");

        Part partImage = request.getPart("file");
        Part partLocation = request.getPart("location");
        Part partDescription = request.getPart("description");
        Part partTags = request.getPart("tag");

        String location = getPartAsString(partLocation);
        String description = getPartAsString(partDescription);
        String tags = getPartAsString(partTags);
        PostDTO postDTO = new PostDTO(tags, location, description);
        InputStream file = partImage.getInputStream();

        String fileName = partImage.getSubmittedFileName();
        String extension = "";
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = fileName.substring(index + 1);
        }
        System.out.println("USAO10");
        postService.createPost(file,postDTO,extension,user);

        return new ResponseEntity<String>("Accepted", HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/media/file/{filename}", method = RequestMethod.GET)
    public void getImageAsByteArray(@PathVariable("filename") String filename, HttpServletResponse response) throws IOException {

        Path path = Paths.get(storageDirectoryPath, filename);
        InputStream in = Files.newInputStream(path);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        IOUtils.copy(in, response.getOutputStream());
    }

    @GetMapping(value = "/user/{userId}/publicPosts")
    public ResponseEntity<List<Post>> getPublicUserPosts(@PathVariable("userId") String userIdString) throws Exception {
        Long userId;
        try {
            userId = Long.valueOf(userIdString);
        }catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
        }

        User user = userRepository.findById(userId).get();
        System.out.println("USER: " + user.getEmail());
        Set<Post> postsSet = user.getPosts();
        List<Post> posts = new ArrayList<>(postsSet);

        List<Post> publicPosts = posts.stream().collect(Collectors.toList());

        publicPosts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        });

        return new ResponseEntity<>(new ArrayList<>(publicPosts), HttpStatus.OK);
    }

    @GetMapping(value = "/media/posts")
    public ResponseEntity<List<Post>> getUserPosts(@Context HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        User sessionUser = (User) session.getAttribute("client");
        System.out.println("GET POSTS");
        System.out.println("SESSION-USER: " + sessionUser.getEmail());

        User user = userRepository.findByEmail(sessionUser.getEmail());
        System.out.println("USER: " + user.getEmail());
        Set<Post> postsSet = user.getPosts();
        List<Post> posts = new ArrayList<>(postsSet);
        posts.sort(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o2.getStartTime().compareTo(o1.getStartTime());
            }
        });

        System.out.println("POSTS: " + posts);
        return new ResponseEntity<>(new ArrayList<>(posts), HttpStatus.OK);
    }


    private String getPartAsString(Part part) throws IOException {
        String result;
        InputStream is = part.getInputStream();

        String text = new BufferedReader(
                new InputStreamReader(is, StandardCharsets.UTF_8)).lines()
                .collect(Collectors.joining("\n"));

        return text.trim();
    }

}