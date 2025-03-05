package com.tomzxy.webQuiz.service.impl;

import com.tomzxy.webQuiz.dto.request.quiz.basic.QuizBasicCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.quiz.QuizUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.response.AppResponse.PageResponse;
import com.tomzxy.webQuiz.dto.response.Quiz.QuizResponse;
import com.tomzxy.webQuiz.exception.ResourceNotFoundException;
import com.tomzxy.webQuiz.mapper.QuizMapper;
import com.tomzxy.webQuiz.model.Chapter;
import com.tomzxy.webQuiz.model.Quiz;
import com.tomzxy.webQuiz.repository.ChapterRepository;
import com.tomzxy.webQuiz.repository.QuizRepository;
import com.tomzxy.webQuiz.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{


    private final QuizRepository quizRepository;
    private final QuizMapper quizMapper;
    private final ChapterRepository chapterRepository;

    @Override
    public QuizResponse addQuiz(Long chapterId, QuizBasicCreateRequestDTO requestDTO) {
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow(()-> new ResourceNotFoundException("Chapter not found"));
        Quiz quiz = quizMapper.toQuiz(requestDTO);
        quiz.setChapter(chapter);
        return quizMapper.toQuizResponse(quizRepository.save(quiz));
    }

    @Override
    public PageResponse<?> getAllQuizWithPage(int page,int size) {
        Pageable pageable = PageRequest.of(page,size);
        Page<Quiz> quizzes = quizRepository.findAll(pageable);
        return convertPageResponse(quizzes,pageable);
    }

    @Override
    public QuizResponse getQuizById(Long QuizId) {
        Quiz quiz = quizRepository.findById(QuizId).orElseThrow(()-> new ResourceNotFoundException("Quiz not found"));
        return quizMapper.toQuizResponse(quiz);
    }

    @Override
    public QuizResponse updateQuiz(Long QuizId, QuizUpdateRequestDTO QuizUpdateRequestDTO) {
        return null;
    }

    @Override
    public void deleteQuiz(Long QuizId) {

    }

    @Override
    public QuizResponse changeTaken(Long QuizId, String isTaken) {
        return null;
    }

    @Override
    public QuizResponse changeStatus(Long QuizId, String status) {
        return null;
    }

    public PageResponse<?> convertPageResponse(Page<Quiz> quizPage, Pageable pageable){
        List<QuizResponse> quizResponses = quizPage.getContent().stream()
                .map(quizMapper::toQuizResponse).toList();

        return PageResponse.builder()
                .page(pageable.getPageNumber())
                .size(pageable.getPageSize())
                .total(quizPage.getTotalElements())
                .items(quizResponses)
                .build();
    }


}
