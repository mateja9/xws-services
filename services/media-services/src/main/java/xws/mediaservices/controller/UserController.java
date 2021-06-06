package xws.mediaservices.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.mediaservices.dto.SearchPost;
import xws.mediaservices.dto.SearchUser;
import xws.mediaservices.model.User;
import xws.mediaservices.service.PostService;
import xws.mediaservices.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping(produces =  MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

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
        request.getSession().setAttribute("dermatologist", dermatologist);
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
        if (authorize(request) == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return userService.searchUser(searchParameters);
    }

}
