package com.tomzxy.webQuiz.service;

import com.tomzxy.webQuiz.dto.request.answer.AnswerRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.response.Question.QuestionResponse;

import java.util.List;

public interface QuestionService {
    QuestionResponse addQuestion(Long chapterId,QuestionCreateRequestDTO requestDTO);
    List<QuestionResponse> getAllQuestion();
    QuestionResponse getQuestionById(Long questionId);
    QuestionResponse updateQuestion(Long questionId, QuestionUpdateRequestDTO questionUpdateRequestDTO);
    void deleteQuestion(Long questionId);

    QuestionResponse addAnswer(Long questionId, AnswerRequestDTO answerRequestDTO);

    QuestionResponse changeLevel(Long questionId, String level);


}
