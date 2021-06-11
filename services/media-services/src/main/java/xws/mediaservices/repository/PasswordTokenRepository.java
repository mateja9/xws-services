package xws.mediaservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.mediaservices.model.PasswordResetToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, String> {
    PasswordResetToken findByToken(String token);
}
