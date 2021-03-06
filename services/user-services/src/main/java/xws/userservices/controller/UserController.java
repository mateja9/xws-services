package xws.userservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.userservices.model.User;
import xws.userservices.service.UserService;

import java.text.MessageFormat;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;
    Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    @PostMapping( value = "/user/addUser")
    public ResponseEntity<?> addUser(@RequestBody User client) throws Exception {
        User postojeci = userService.findByEmail(client.getEmail());

        if (postojeci != null) {
            return new ResponseEntity<>("Korisnik sa datim email-om vec postoji", HttpStatus.METHOD_NOT_ALLOWED);
        } else {

            User newUser = userService.addUser(client);
            if (newUser != null) {
                LOGGER.info(MessageFormat.format("CLIENT -ID:{0}-created, CLIENT-EMAIL:{1}", newUser.getId(), newUser.getEmail()));
            } else {
                LOGGER.error(MessageFormat.format("CLIENT-ID:{0}-not created, CLIENT-EMAIL:{1}", newUser.getId(), newUser.getEmail()));
            }


            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }

    @GetMapping(value = "/getAllUsers")
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




}
