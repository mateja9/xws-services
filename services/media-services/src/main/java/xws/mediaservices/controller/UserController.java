package xws.mediaservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.*;
import xws.mediaservices.dto.PasswordDto;
import xws.mediaservices.dto.SearchPost;
import xws.mediaservices.dto.SearchUser;
import xws.mediaservices.model.ConfirmationToken;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.ConfirmationTokenRepository;
import xws.mediaservices.repository.UserRepository;
import xws.mediaservices.service.EmailService;
import xws.mediaservices.service.PostService;
import xws.mediaservices.service.SecurityService;
import xws.mediaservices.service.UserService;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.core.Context;
import java.text.MessageFormat;
import java.util.*;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SecurityService securityService;

    Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @PostMapping( value = "/user/addUser")
    public ResponseEntity<?> addUser(@RequestBody User client) throws Exception {


            User newUser = userService.addUser(client);
            newUser.setPrviPutLogovan(true);
            if (newUser != null) {
                LOGGER.info(MessageFormat.format("CLIENT -ID:{0}-created, CLIENT-EMAIL:{1}", newUser.getId(), newUser.getEmail()));


                Properties props = new Properties();
                props.put("mail.mime.address.strict", "false");
                props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
                Session session = Session.getDefaultInstance(props);
                ConfirmationToken confirmationToken = new ConfirmationToken(newUser);

                confirmationTokenRepository.save(confirmationToken);

                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(newUser.getEmail());
                mailMessage.setSubject("Complete Registration!");
                mailMessage.setFrom("BSEP.tim26@gmail.com");
                mailMessage.setText("To confirm your account, please click here : "
                        +"http://localhost:8080/confirm-account?token="+confirmationToken.getConfirmationToken());

                emailService.sendEmail(mailMessage);

                return new ResponseEntity<>(newUser, HttpStatus.CREATED);

            } else {
                return new ResponseEntity<String>("User with email is already registered", HttpStatus.METHOD_NOT_ALLOWED);

            }


        }



    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity confirmUserAccount(@RequestParam("token")String confirmationToken, @Context HttpServletRequest request)
    {
        ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            User user = userRepository.findByEmail(token.getUserEntity().getEmail());
            user.setPrviPutLogovan(false);
            userRepository.save(user);
            request.getSession().setAttribute("client", user);

            return new ResponseEntity<String>("Registration successful", HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<String>("Registration not successful", HttpStatus.METHOD_NOT_ALLOWED);
        }

    }


    @GetMapping(value = "/user/getAllUsers")
    public ResponseEntity<List<User>> getAllUsers() throws Exception {

        List<User> users=userService.getAll();

        if(users!=null) {
            LOGGER.info("CLIENT - returned all");
        } else {
            LOGGER.error("CLIENT - not returned all");
        }

        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @GetMapping("/email/{id}")
    String getMailKorisnika(@PathVariable("id") Long id){
        return userService.findById(id).getEmail();
    }

    @PutMapping(value = "/user/edit")
    public ResponseEntity updateUser(@RequestBody User updateDermatologist, @Context HttpServletRequest request) {
        if(authorize(request) == null ) {
            return new ResponseEntity<>( HttpStatus.UNAUTHORIZED);
        }
        User dermatologist= userService.updateUser(updateDermatologist);
        request.getSession().setAttribute("client", dermatologist);
        return new ResponseEntity<User>(dermatologist, HttpStatus.CREATED);
    }

    private User authorize(HttpServletRequest request){
        HttpSession session = request.getSession();
        User d = (User) session.getAttribute("client");
        return d;
    }

    @PostMapping(value = "/user/searchPost")
    public Object searchPharmacy(@RequestBody SearchPost searchParameters) {

        return postService.search(searchParameters);
    }

    @PostMapping(value = "/user/searchUser")
    public Object searchUser(@RequestBody SearchUser searchParameters, @Context HttpServletRequest request) {
      
        return userService.searchUser(searchParameters);
    }

    @PostMapping(value = "/searchU/{username}")
    public Object searchUser(@PathVariable("username") String username,
                             @RequestBody String name, @Context HttpServletRequest request) {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("client");

        return userService.searchU(username, name, user.getId());
    }


    //get by id
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) throws Exception {

        User user = userService.getById(id);

        if(user!=null) {
            LOGGER.info("USER-ID:{0}-returned, USER-ID:{1}", user.getId(), user.getName());
        } else {
            LOGGER.error("USER-ID:{0}-not returned, USER-ID:{1}", user.getId(), user.getName());
        }

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }



    @PostMapping("/user/resetPassword")
    public ResponseEntity<?> resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) {
        User user = userService.findByEmail(userEmail);

        if (user == null) {
            return new ResponseEntity<>("User with this email doesn't exist", HttpStatus.METHOD_NOT_ALLOWED);
        }

        Properties props = new Properties();
        props.put("mail.mime.address.strict", "false");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getDefaultInstance(props);


        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(userEmail);

        mailMessage.setSubject("Reset password!");
        mailMessage.setFrom("BSEP.tim26@gmail.com");
        mailMessage.setText("If you want to reset your password, please click here : "
                +"http://localhost:8080/api/user/changePassword?token="+token);


        emailService.sendEmail(mailMessage);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    @GetMapping("/user/changePassword")
    public String showChangePasswordPage(@RequestParam("token") String token) {

        String result = securityService.validatePasswordResetToken(token);
        if(result != null) {

            //return "redirect:" + redirectUrl;

            return "redirect:/login.html?lang=";
        } else {
            return "redirect:/updatePassword.html?lang=";
        }
    }


    @PostMapping("/user/savePassword")
    public ResponseEntity savePassword(@Valid PasswordDto passwordDto) {

        String result = securityService.validatePasswordResetToken(passwordDto.getToken());

        if(result != null) {
            return new ResponseEntity<>("Token is invalidate or expired", HttpStatus.NOT_ACCEPTABLE);
        }

        User user = userService.getUserByPasswordResetToken(passwordDto.getToken());
            securityService.changeUserPassword(user, passwordDto.getNewPassword());
            return new ResponseEntity<>("Password changed", HttpStatus.ACCEPTED);
        }
}
