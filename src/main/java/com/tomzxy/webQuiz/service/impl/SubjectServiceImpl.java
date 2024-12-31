package com.tomzxy.webQuiz.service.impl;

import com.tomzxy.webQuiz.constants.EndPoint;
import com.tomzxy.webQuiz.dto.request.chapter.ChapterRequestDTO;
import com.tomzxy.webQuiz.dto.request.subject.SubjectRequest;
import com.tomzxy.webQuiz.dto.request.subject.SubjectUpdateRequest;
import com.tomzxy.webQuiz.dto.response.Chapter.ChapterResponse;
import com.tomzxy.webQuiz.dto.response.Subject.SubjectResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.mapper.ChapterMapper;
import com.tomzxy.webQuiz.mapper.SubjectMapper;
import com.tomzxy.webQuiz.model.Chapter;
import com.tomzxy.webQuiz.model.Subject;
import com.tomzxy.webQuiz.repository.ChapterRepository;
import com.tomzxy.webQuiz.repository.SubjectRepository;
import com.tomzxy.webQuiz.service.SubjectService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;
    private final ChapterMapper chapterMapper;
    private final ChapterRepository chapterRepository;
    @Override
    public SubjectResponse addSubject(SubjectRequest subjectRequest) {
        Subject subject = subjectMapper.toSubject(subjectRequest);

        return subjectMapper.toSubjectResponse(subjectRepository.save(subject));
    }

    @Override
    public List<SubjectResponse> getAllSubject() {
        return subjectRepository.findAll().stream().map(subjectMapper ::toSubjectResponse).toList();
    }

    @Override
    public SubjectResponse getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Subject not found!!"));
        return subjectMapper.toSubjectResponse(subject);
    }

    @Override
    public SubjectResponse getSubjectByName(String name) {
        Subject subject = subjectRepository.findBySubjectText(name).orElseThrow(()->new ResourceNotFoundException("Subject not found!!"));
        return subjectMapper.toSubjectResponse(subject);

    }

    @Override
    public void deleteSubject(Long id) {
        subjectRepository.deleteById(id);
    }

    @Override
    public SubjectResponse updateSubject(Long id, SubjectUpdateRequest request) {
        Subject subject = subjectRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Subject not found!!"));
        subjectMapper.updateSubject(subject,request);

        var chapter = subjectRepository.findAllByName(request.getChapters());
        subject.setChapters(chapter);

        return subjectMapper.toSubjectResponse(subject);
    }

    @Override
    public ChapterResponse addChapter(Long idSubject, ChapterRequestDTO chapterRequestDTO) {
        Subject subject = subjectRepository.findById(idSubject).orElseThrow(()-> new ResourceNotFoundException("Subject not found!!"));
        Chapter chapter = chapterMapper.toChapter(chapterRequestDTO);
        chapter.setSubject(subject);
        return chapterMapper.toChapterResponse(chapterRepository.save(chapter));
    }
}
