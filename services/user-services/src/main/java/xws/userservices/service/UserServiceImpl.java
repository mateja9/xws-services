package xws.userservices.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.userservices.model.User;
import xws.userservices.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User addUser(User user) {

        User newUser=new User(user.getName(), user.getLastname(),user.getPhoneNumber(),user.getEmail(),user.getUsername(),user.getPassword(),user.getRola(),user.getGender(), user.getWebsite(),user.getBio(),user.getDateofb());

        newUser=userRepository.save(newUser);
        return newUser;
    }
    @Override
    public List<User> getAll() {
        List<User> users=new ArrayList<>();
        for(User c : userRepository.findAll()){
            users.add(c);
        }
        return users;
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
