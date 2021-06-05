package xws.mediaservices.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

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

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User updateUser(User updateDermatologist) {


        User forChange = getByEmail(updateDermatologist.getEmail());

        forChange.setUsername(updateDermatologist.getUsername());
        forChange.setName(updateDermatologist.getName());
        forChange.setLastname(updateDermatologist.getLastname());
        forChange.setEmail(updateDermatologist.getEmail());
        forChange.setGender(updateDermatologist.getGender());
        forChange.setWebsite(updateDermatologist.getWebsite());
        forChange.setPassword(updateDermatologist.getPassword());
        forChange.setPhoneNumber(updateDermatologist.getPhoneNumber());
        forChange.setDateofb(updateDermatologist.getDateofb());
        userRepository.save(forChange);

        return forChange;

    }



}
