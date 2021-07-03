package xws.mediaservices.service;

import xws.mediaservices.model.Story;
import xws.mediaservices.model.User;

import java.io.InputStream;

public interface StoryService {
    Story findById(Long id);

    Story createStory(InputStream file, String ext, boolean closeFriends, Boolean highlighted,String tags, User user);
}
