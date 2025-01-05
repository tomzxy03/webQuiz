package com.tomzxy.webQuiz.repository;

import com.tomzxy.webQuiz.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>, JpaSpecificationExecutor<AnswerRepository> {
    Optional<Answer> findByAnswerText(String name);

    @Query("select a from Answer a where a.answerText IN :answers")
    List<Answer> findAllByAnswerText(@Param("answers") List<String> answers);
}
