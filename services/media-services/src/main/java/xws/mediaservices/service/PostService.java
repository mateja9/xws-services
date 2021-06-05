package xws.mediaservices.service;

import xws.mediaservices.dto.SearchPost;
import xws.mediaservices.model.Post;

import java.util.ArrayList;

public interface PostService {
    Post findById(Long id);
    public ArrayList<Post> search(SearchPost searchParameters);
}
