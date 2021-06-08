package xws.mediaservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import xws.mediaservices.dto.PostDTO;
import xws.mediaservices.dto.SearchPost;
import xws.mediaservices.model.Comment;
import xws.mediaservices.model.Post;
import xws.mediaservices.repository.PostRepository;
import xws.mediaservices.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService{

    public final String storageDirectoryPath = "..\\storage\\medias";

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

/* //proveriti za tagove, da li ce ici kao slobodan textbox ili kao set

            if (!searchParameters.getTag().equals("all")) {
                if (!p.getTag().toLowerCase().contains(searchParameters.getTag().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }
        */
        }

        System.out.println("RET : " + ret);

        return ret;
    }

    @Override
    public Post createPost(MultipartFile file, PostDTO postDTO, String username) throws IOException {

        String fileName = saveFile(file, storageDirectoryPath);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("media/content/")
                .path(fileName)
                .toUriString();

        Post post = new Post();
        post.setUsername(username);
        Set<String> paths = new HashSet<String>();
        paths.add(fileDownloadUri);
        post.setPath(paths);

        Set<String> tags = new HashSet<>(postDTO.getTags());
        post.setTags(tags);

        post.setDescription(postDTO.getDescription());

        post.setLocation(postDTO.getLocation());

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


    /*

    @Override
  public void init() {
    try {
      Files.createDirectory(root);
    } catch (IOException e) {
      throw new RuntimeException("Could not initialize folder for upload!");
    }
  }

  @Override
  public void save(MultipartFile file) {
    try {
      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
    } catch (Exception e) {
      throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
    }
  }

  @Override
  public Resource load(String filename) {
    try {
      Path file = root.resolve(filename);
      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return resource;
      } else {
        throw new RuntimeException("Could not read the file!");
      }
    } catch (MalformedURLException e) {
      throw new RuntimeException("Error: " + e.getMessage());
    }
  }

  @Override
  public void deleteAll() {
    FileSystemUtils.deleteRecursively(root.toFile());
  }

  @Override
  public Stream<Path> loadAll() {
    try {
      return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
    } catch (IOException e) {
      throw new RuntimeException("Could not load the files!");
    }
  }

    * */


}
