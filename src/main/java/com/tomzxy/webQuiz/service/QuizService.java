package com.tomzxy.webQuiz.service;

import com.tomzxy.webQuiz.dto.request.quiz.basic.QuizBasicCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.quiz.QuizUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.response.AppResponse.PageResponse;
import com.tomzxy.webQuiz.dto.response.Quiz.QuizResponse;

public interface QuizService {

    QuizResponse addQuiz(Long chapterId, QuizBasicCreateRequestDTO requestDTO);
    PageResponse<?> getAllQuizWithPage(int page, int size);
    QuizResponse getQuizById(Long QuizId);
    QuizResponse updateQuiz(Long QuizId, QuizUpdateRequestDTO QuizUpdateRequestDTO);
    void deleteQuiz(Long QuizId);

    QuizResponse changeTaken(Long QuizId, String isTaken);
    QuizResponse changeStatus(Long QuizId, String status);
}
