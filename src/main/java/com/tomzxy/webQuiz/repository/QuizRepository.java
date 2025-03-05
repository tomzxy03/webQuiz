package com.tomzxy.webQuiz.repository;


import com.tomzxy.webQuiz.model.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
//    Page<Quiz> getA(Pageable pageable);

    Page<Quiz> findQuizzesByChapterId(Long chapterId, Pageable pageable);
}
