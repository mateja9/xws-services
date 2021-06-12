package xws.mediaservices.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import xws.mediaservices.model.LoginZahtev;
import xws.mediaservices.model.User;
import xws.mediaservices.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.text.MessageFormat;
import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping( produces =  MediaType.APPLICATION_JSON_VALUE)
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/user/login")
    public ResponseEntity<?> login(@RequestBody LoginZahtev zahtev, @Context HttpServletRequest request) {


        Collection<User> client = userService.getAll();


        User ak = userService.findByEmail(zahtev.getEmail());

        if (ak != null) {

            boolean isPasswordMatch = passwordEncoder.matches(zahtev.getPassword(), ak.getPassword());

            if (isPasswordMatch) {
                if ( !ak.isPrviPutLogovan()){

                HttpSession session = request.getSession();
                session.setAttribute("client", ak);
                LOGGER.info(MessageFormat.format("USER SESSION: USER-ID:{0}-session created, USER-EMAIL:{1}", ak.getId(), ak.getEmail()));

            }
                return new ResponseEntity<User>(ak, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);


    }

    @GetMapping(value = "/user/login")
    public Object vratiUlogovanog(@Context HttpServletRequest request) {

        HttpSession session = request.getSession();

        User korisnik = (User) session.getAttribute("client");

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


    @PostMapping(value = "/user/logOut")
    public ResponseEntity logOut(@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        LOGGER.info("SESSION: user logged out");
        return ResponseEntity.status(200).build();
    }

}