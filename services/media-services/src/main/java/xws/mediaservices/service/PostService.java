package xws.mediaservices.service;

import org.springframework.web.multipart.MultipartFile;
import xws.mediaservices.dto.PostDTO;
import xws.mediaservices.dto.SearchPost;
import xws.mediaservices.model.Post;

import java.io.IOException;
import java.util.ArrayList;

public interface PostService {
    Post findById(Long id);
    public ArrayList<Post> search(SearchPost searchParameters);
    public Post createPost(MultipartFile file, PostDTO postDTO, String username) throws IOException;
}
