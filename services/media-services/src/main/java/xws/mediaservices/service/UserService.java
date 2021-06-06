package xws.mediaservices.service;

import xws.mediaservices.dto.SearchUser;
import xws.mediaservices.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    User addUser(User client);
    List<User> getAll();
    User findById(Long id);
    User findByEmail(String email);
    public User updateUser(User updateUser);
    public User getByEmail(String email);
    public ArrayList<User> searchUser(SearchUser searchParameters);
    public ArrayList<User> searchU(String username, String name, Long userId);
    public List<User> getAllUsers(Long userId);
    public User getById(Long id);


}
