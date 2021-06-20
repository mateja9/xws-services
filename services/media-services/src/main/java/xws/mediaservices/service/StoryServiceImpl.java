package xws.mediaservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xws.mediaservices.model.Story;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.StoryRepository;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StoryServiceImpl implements StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Override
    public Story findById(Long id) {
        return null;
    }

    @Value("${media.storage}")
    private String storageDirectoryPath;

    @Override
    public Story createStory(InputStream file, String ext, boolean onlyCloseFriends, Boolean highlighted, User user) {
        System.out.println("SERVICE CREATE STORY");

        String filename = saveFile(file, ext);

        Story story = new Story();
        story.setStartTime(LocalDateTime.now());
        story.setEndTime(LocalDateTime.now().plusDays(1));
        story.setPathOfContent(filename);
        story.setOnlyCloseFriends(onlyCloseFriends);
        story.setHighlited(highlighted);
        story.setUser(user);

        storyRepository.save(story);

        return story;
    }

    private String saveFile(InputStream file, String ext) {
        String filename = UUID.randomUUID().toString() + "." + ext;

        Path storageDirectory = Paths.get(storageDirectoryPath);
        System.out.println("PATH: " + storageDirectory.toAbsolutePath());
        System.out.println("FILE: " + filename);
        Path dest = Paths.get(storageDirectory + File.separator + filename);
        try {
            Files.copy(file, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}
