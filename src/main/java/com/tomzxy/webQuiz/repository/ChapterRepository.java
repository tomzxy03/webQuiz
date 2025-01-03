package com.tomzxy.webQuiz.repository;


import com.tomzxy.webQuiz.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Optional<Chapter> findByChapterName(String text);
}
