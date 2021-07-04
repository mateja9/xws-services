package xws.mediaservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import xws.mediaservices.dto.PostDTO;
import xws.mediaservices.dto.SearchPost;
import xws.mediaservices.model.Comment;
import xws.mediaservices.model.Post;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.PostRepository;
import xws.mediaservices.repository.UserRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostServiceImpl implements PostService{

    @Value("${media.storage}")
    private String storageDirectoryPath;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public Post findById(Long id) {
        return null;
    }

    @Override
    public Post createPost (InputStream file, PostDTO postDTO, String ext, User user) {

        String filename = saveFile(file, ext);

        Post post = new Post();
        post.setStartTime(LocalDateTime.now());
        post.setPathOfContent(filename);
        post.setUser(user);
        post.setTags(postDTO.getTags());
        post.setDescription(postDTO.getDescription());
        post.setLocation(postDTO.getLocation());
        post.setNumberOfDislikes(0);
        post.setNumberOfLikes(0);
        Set<Comment> comments = new HashSet<>();
        post.setComments(comments);
        postRepository.save(post);

        return post;
    }



    private String saveFile(InputStream file, String ext) {
        String filename = UUID.randomUUID().toString() + "." + ext;

        Path storageDirectory = Paths.get(storageDirectoryPath);
        Path dest = Paths.get(storageDirectory + File.separator + filename);
        try {
            Files.copy(file, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }


    public ArrayList<Post> search(SearchPost searchParameters){
        ArrayList<Post> ret = new ArrayList<Post>();
        // getting all posts
        for (Post m : postRepository.findAll()) {
            ret.add(m);
        }

        for (Post p : postRepository.findAll()) {


            if (!searchParameters.getLocation().equals("all")) {
                if (!p.getLocation().toLowerCase().contains(searchParameters.getLocation().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }


            if (!searchParameters.getTag().equals("all")) {
                if (!p.getTags().toLowerCase().contains(searchParameters.getTag().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }
        }

        System.out.println("RET : " + ret);

        return ret;
    }



    @Override
    public void addLike(long postId, int like) {
        Post post = postRepository.findById(postId).orElse(null);
        post.setNumberOfLikes(like);
        postRepository.save(post);
    }

    @Override
    public void addDislike (long postId, int dislike) {
        Post post = postRepository.findById(postId).orElse(null);
        post.setNumberOfDislikes(dislike);
        postRepository.save(post);
    }

    @Override
    public void addToFavourite(long userId, long postId) {

        User user = userRepository.findById(userId).orElse(null);
        Post newPost = postRepository.findById(postId).orElse(null);
        Set<Post> posts = user.getFavouritePosts();
        posts.add(newPost);
        user.setFavouritePosts(posts);
    }


        /*
    @Override
    public List<Integer> getLikesAndDislikes(long idPost) {

        List<Integer> ret = new ArrayList<>();
        Post post = postRepository.findById(idPost).orElse(null);
        ret.add(post.getNumberOfLikes());
        ret.add(post.getNumberOfDislikes());

        return ret;
    }

   @Override
    public void addLikesAndDislikes(long postId, int likes, int dislikes) {
        Post post = postRepository.findById(postId).orElse(null);
        post.setNumberOfLikes(likes);
        post.setNumberOfDislikes(dislikes);
        postRepository.save(post);
    }*/



/*
    @Override
    public Post createPost(MultipartFile file, PostDTO postDTO, User user) throws IOException {

        String fileName = saveFile(file, storageDirectoryPath);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("media/content/")
                .path(fileName)
                .toUriString();

        Post post = new Post();
        post.setUser(user);
        //post.setUsername(username);
        Set<String> paths = new HashSet<String>();
        paths.add(fileDownloadUri);
        post.setPath(paths);

        //Set<String> tags = new HashSet<>(postDTO.getTags());
        post.setTags(postDTO.getTags());
        post.setDescription(postDTO.getDescription());
        post.setLocation(postDTO.getLocation());
        post.setStartTime(LocalDateTime.now());
        post.setNumberOfLikes(0);
        post.setNumberOfDislikes(0);

        Set<Comment> comments = new HashSet<>();
        post.setComments(comments);

        postRepository.save(post);
        return post;
    }


    private String saveFile(MultipartFile file, String storagePath) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String extension = getFileExtension(fileName);

        String newFileName = UUID.randomUUID().toString() + "." + extension;

        Path storage = Paths.get(storagePath);
        if(!Files.exists(storage)){
            Files.createDirectories(storage);
        }

        Path dest = Paths.get(storage.toString() + "\\" + newFileName);
        Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

        return dest.toString();
    }


    private String getFileExtension(String file) throws IOException {

        String fileName = file.toString();
        String extension = "";

        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            extension = fileName.substring(index + 1);
        }

        return extension;
    }

*/


}
