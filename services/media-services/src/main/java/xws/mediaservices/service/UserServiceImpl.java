package xws.mediaservices.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import xws.mediaservices.dto.SearchUser;
import xws.mediaservices.model.PasswordResetToken;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.PasswordTokenRepository;
import xws.mediaservices.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordTokenRepository passwordTokenRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {

        String password = passwordEncoder.encode(user.getPassword());

        User newUser=new User(user.getName(), user.getLastname(),user.getPhoneNumber(),user.getEmail(),
                user.getUsername(),password,user.getRola(),user.getGender(),
                user.getWebsite(),user.getBio(),user.getDateofb(), user.isPrviPutLogovan());

        newUser.setRola("CLIENT");
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
    public User updateUser(User updateUser) {


        User forChange = getByEmail(updateUser.getEmail());

        forChange.setUsername(updateUser.getUsername());
        forChange.setName(updateUser.getName());
        forChange.setLastname(updateUser.getLastname());
        forChange.setEmail(updateUser.getEmail());
        forChange.setGender(updateUser.getGender());
        forChange.setWebsite(updateUser.getWebsite());
        forChange.setPassword(updateUser.getPassword());
        forChange.setPhoneNumber(updateUser.getPhoneNumber());
        forChange.setDateofb(updateUser.getDateofb());
        userRepository.save(forChange);

        return forChange;

    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken myToken = new PasswordResetToken(token, user);
        passwordTokenRepository.save(myToken);
    }

    @Override
    public User getUserByPasswordResetToken (String token) {

        PasswordResetToken passToken = passwordTokenRepository.findByToken(token);
        User user  = passToken.getUser();
        return user;
    }


    //NIJE ODRADJENA LOGIKA GDE SE PROVERAVA DA LI JE PROFIL JAVAN
    @Override
    public ArrayList<User> searchUser(SearchUser searchParameters){
        ArrayList<User> ret = new ArrayList<User>();


        for (User p : userRepository.findAll())  {
            ret.add(p);
        }

        for (User p : userRepository.findAll()) {


            if (!searchParameters.getEmail().equals("all")) {
                if (!p.getEmail().toLowerCase().contains(searchParameters.getEmail().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }


            if (!searchParameters.getName().equals("all")) {
                if (!p.getName().toLowerCase().contains(searchParameters.getName().toLowerCase())) {
                    // and it is in the ret list
                    if (ret.contains(p)) {
                        // remove it from the ret list
                        ret.remove(p);
                    }
                }
            }

            if (!searchParameters.getLastname().equals("all")) {
                if (!p.getLastname().toLowerCase().contains(searchParameters.getLastname().toLowerCase())) {
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

    @Override
    public ArrayList<User> searchU(String username, String name, Long userId){

        ArrayList<User> ret = new ArrayList<User>();

        for (User u : getAllUsers(userId)){
            ret.add(u);
        }


        for (User u : getAllUsers(userId)){

            if (!u.getUsername().toLowerCase().contains(username.toLowerCase()) ||
                    !u.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase())) {
                ret.remove(u);
            }
        }

        System.out.println("RET : " + ret);
        return ret;
    }
    @Override
    public List<User> getAllUsers(Long userId) {

        ArrayList<User> ret = new ArrayList<User>();

        List<User> users = userService.getAll();

        for (User i: users) {

            User user = userRepository.findById(i.getId()).orElseGet(null);

            if(!ret.contains(user)) {
                ret.add(user);
            }

        }

        return ret;
    }

    @Override
    public User getById(Long id){
        return userRepository.findById(id).orElseGet(null);
    }


}








