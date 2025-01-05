package com.tomzxy.webQuiz.repository;

import com.tomzxy.webQuiz.model.Question;
import com.tomzxy.webQuiz.enums.Level;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long>, JpaSpecificationExecutor<Question> {

    Optional<Question> findByQuestionText(String question_text);
    List<Question> findAllByLevel (Level level);
    Long countQuestionByLevel (Level level);

}
