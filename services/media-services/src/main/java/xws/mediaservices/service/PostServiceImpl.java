package xws.mediaservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.mediaservices.dto.SearchPost;
import xws.mediaservices.model.Post;
import xws.mediaservices.repository.PostRepository;
import xws.mediaservices.repository.UserRepository;

import java.util.ArrayList;

@Service
public class PostServiceImpl implements PostService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Override
    public Post findById(Long id) {
        return null;
    }

    public ArrayList<Post> search(SearchPost searchParameters){
        ArrayList<Post> ret = new ArrayList<Post>();
        // getting all pharmacies
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
                if (!p.getTag().toLowerCase().contains(searchParameters.getTag().toLowerCase())) {
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
}
