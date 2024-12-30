package com.tomzxy.webQuiz.repository;

import com.tomzxy.webQuiz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
//    Optional<User> findByGoogleAccountId(String googleAccountId);
//    Optional<User> findByFacebookAccountId(String facebookAccountId);
//    Optional<User> findByResetToken(String resetToken);
}
