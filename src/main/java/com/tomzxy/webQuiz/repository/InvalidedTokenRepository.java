package com.tomzxy.webQuiz.repository;

import com.tomzxy.webQuiz.model.InvalidToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidedTokenRepository extends JpaRepository<InvalidToken, String> {
}
