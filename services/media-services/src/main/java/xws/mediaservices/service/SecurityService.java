package xws.mediaservices.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xws.mediaservices.model.PasswordResetToken;
import xws.mediaservices.model.User;
import xws.mediaservices.repository.PasswordTokenRepository;
import xws.mediaservices.repository.PostRepository;
import xws.mediaservices.repository.UserRepository;

import java.util.Calendar;

@Service
public class SecurityService {


    @Autowired
    private PasswordTokenRepository passwordTokenRepository;
    @Autowired
    private UserRepository userRepository;

    public String validatePasswordResetToken(String token) {
        final PasswordResetToken passToken = passwordTokenRepository.findByToken(token);

        return !isTokenFound(passToken) ? "invalidToken"
                : isTokenExpired(passToken) ? "expired"
                : null;
    }

    private boolean isTokenFound(PasswordResetToken passToken) {
        return passToken != null;
    }

    private boolean isTokenExpired(PasswordResetToken passToken) {
        final Calendar cal = Calendar.getInstance();
        return passToken.getExpiryDate().before(cal.getTime());
    }

    public void changeUserPassword(User user, String password) {
        user.setPassword(password);
        userRepository.save(user);
    }

}
