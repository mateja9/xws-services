package xws.userservices.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xws.userservices.model.LoginZahtev;
import xws.userservices.model.User;
import xws.userservices.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.text.MessageFormat;
import java.util.Collection;

import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( produces =  MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private UserService userService;

    Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/user/login")
    public ResponseEntity<?> login(@RequestBody LoginZahtev zahtev, @Context HttpServletRequest request) {


        Collection<User> client = userService.getAll();


        User ak = userService.findByEmail(zahtev.getEmail());

        if (ak != null) {

            if (zahtev.getPassword().equals(ak.getPassword())) {

                //if (zahtev.getPassword().equals(ak.getPassword())) {
                HttpSession session = request.getSession();
                session.setAttribute("user", ak);
                LOGGER.info(MessageFormat.format("USER SESSION: USER-ID:{0}-session created, USER-EMAIL:{1}", ak.getId(), ak.getEmail()));

                return new ResponseEntity<User>(ak, HttpStatus.CREATED);
            }

        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping(value = "/user/login")
    public Object vratiUlogovanog(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();

        User korisnik = (User) session.getAttribute("User");

        if (korisnik != null) {

            if (korisnik != null) {
                LOGGER.info(MessageFormat.format("USER SESSION: USER-ID:{0}-logged in, USER-EMAIL:{1}", korisnik.getId(), korisnik.getEmail()));
            } else {
                LOGGER.error(MessageFormat.format("USER SESSION: USER-ID:{0}- not logged in, USER-EMAIL:{1}", korisnik.getId(), korisnik.getEmail()));
            }


        }

        return korisnik;
    }

    @GetMapping(value = "/user/returnId")
    public Long vratiIdUlogovanog(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();

        User korisnik = (User) session.getAttribute("client");

        if (korisnik != null) {
            return korisnik.getId();
        }

        return null;
    }

    @RequestMapping(method = PUT, value = "/user/logOut")
    public ResponseEntity logOut(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        LOGGER.info("SESSION: user logged out");
        return ResponseEntity.status(200).build();
    }


}