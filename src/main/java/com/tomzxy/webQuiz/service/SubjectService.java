package com.tomzxy.webQuiz.service;


import com.tomzxy.webQuiz.dto.request.chapter.ChapterRequestDTO;
import com.tomzxy.webQuiz.dto.request.subject.SubjectRequest;
import com.tomzxy.webQuiz.dto.request.subject.SubjectUpdateRequest;
import com.tomzxy.webQuiz.dto.response.Chapter.ChapterResponse;
import com.tomzxy.webQuiz.dto.response.Subject.SubjectResponse;
import java.util.List;

public interface SubjectService {

    SubjectResponse addSubject(SubjectRequest subjectRequest);

    List<SubjectResponse> getAllSubject();
    SubjectResponse getSubjectById(Long id);
    SubjectResponse getSubjectByName(String name);
    void deleteSubject(Long id);
    SubjectResponse updateSubject(Long id, SubjectUpdateRequest request);
    SubjectResponse addChapter(Long idSubject, ChapterRequestDTO chapterRequestDTO);
}
