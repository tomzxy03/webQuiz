package com.tomzxy.webQuiz.service.impl;

import com.tomzxy.webQuiz.dto.request.chapter.ChapterRequestDTO;
import com.tomzxy.webQuiz.dto.response.Chapter.ChapterResponse;
import com.tomzxy.webQuiz.dto.response.Subject.SubjectResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.mapper.ChapterMapper;
import com.tomzxy.webQuiz.model.Chapter;
import com.tomzxy.webQuiz.repository.ChapterRepository;
import com.tomzxy.webQuiz.service.ChapterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChapterServiceImpl implements ChapterService {

    private final ChapterMapper chapterMapper;
    private final ChapterRepository chapterRepository;

    @Override
    public List<ChapterResponse> getAllChapter() {
        return chapterRepository.findAll().stream().map(chapterMapper::toChapterResponse).toList();
    }

    @Override
    public ChapterResponse getChapterById(Long id) {
        Chapter chapter = chapterRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Chapter not found!!"));        return chapterMapper.toChapterResponse(chapter);
    }

    @Override
    public ChapterResponse getChapterByName(String name) {
        Chapter chapter = chapterRepository.findByChapterName(name).orElseThrow(()-> new ResourceNotFoundException("Chapter not found!!"));
        return chapterMapper.toChapterResponse(chapter);
    }

    @Override
    public void deleteChapter(Long id) {
        chapterRepository.deleteById(id);
    }

    @Override
    public ChapterResponse updateChapter(Long id, ChapterRequestDTO request) {
        Chapter chapter = chapterRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Chapter not found!!"));
        chapterMapper.updateChapter(chapter, request);
        return chapterMapper.toChapterResponse(chapter);
    }
}
