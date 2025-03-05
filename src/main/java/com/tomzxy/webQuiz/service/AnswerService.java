package com.tomzxy.webQuiz.service;

import com.tomzxy.webQuiz.dto.request.answer.AnswerRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.response.Answer.AnswerResponse;
import com.tomzxy.webQuiz.dto.response.Question.QuestionResponse;

import java.util.List;

public interface AnswerService {

    List<AnswerResponse> getAllAnswer();
    AnswerResponse getAnswerById(Long answerId);
    AnswerResponse updateAnswer(Long answerId, AnswerRequestDTO answerRequestDTO);
    void deleteAnswer(Long answerId);

}
