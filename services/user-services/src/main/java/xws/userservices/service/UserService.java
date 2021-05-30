package xws.userservices.service;

import xws.userservices.model.User;

import java.util.List;

public interface UserService {

    User addUser(User client);
    List<User> getAll();
    User findById(Long id);
    User findByEmail(String email);
}
