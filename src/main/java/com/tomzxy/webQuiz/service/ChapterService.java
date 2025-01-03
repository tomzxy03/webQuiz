package com.tomzxy.webQuiz.service;

import com.tomzxy.webQuiz.dto.request.chapter.ChapterRequestDTO;
import com.tomzxy.webQuiz.dto.request.subject.SubjectUpdateRequest;
import com.tomzxy.webQuiz.dto.response.Chapter.ChapterResponse;
import com.tomzxy.webQuiz.dto.response.Subject.SubjectResponse;

import java.util.List;

public interface ChapterService {

    List<ChapterResponse> getAllChapter();
    ChapterResponse getChapterById(Long id);
    ChapterResponse getChapterByName(String name);
    void deleteChapter(Long id);
    ChapterResponse updateChapter(Long id, ChapterRequestDTO request);
}
