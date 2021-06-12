package xws.mediaservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xws.mediaservices.model.PasswordResetToken;

public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {
    PasswordResetToken findByToken(String token);
}
