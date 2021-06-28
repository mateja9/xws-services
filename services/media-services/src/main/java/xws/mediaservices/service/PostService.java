package xws.mediaservices.service;

import org.springframework.web.multipart.MultipartFile;
import xws.mediaservices.dto.PostDTO;
import xws.mediaservices.dto.SearchPost;
import xws.mediaservices.model.Post;
import xws.mediaservices.model.User;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public interface PostService {
    Post findById(Long id);
    public ArrayList<Post> search(SearchPost searchParameters);
    public Post createPost(InputStream file, PostDTO postDTO, String ex, User user) throws IOException;
}
