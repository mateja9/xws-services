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
    public User updateUser(User updateDermatologist);
    public User getByEmail(String email);
    public ArrayList<User> searchUser(SearchUser searchParameters);

}
