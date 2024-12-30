package com.tomzxy.webQuiz.repository;


import com.tomzxy.webQuiz.model.Chapter;
import com.tomzxy.webQuiz.model.Role;
import com.tomzxy.webQuiz.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Optional<Subject> findBySubjectText(String subject_text);
    @Query("SELECT r FROM Chapter r WHERE r.chapterName IN :chapterName")
    List<Chapter> findAllByName(@Param("chapterName") List<String> chapters);
}
