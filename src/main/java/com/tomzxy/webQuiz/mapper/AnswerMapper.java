package com.tomzxy.webQuiz.mapper;

import com.tomzxy.webQuiz.dto.request.answer.AnswerRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.response.Answer.AnswerResponse;
import com.tomzxy.webQuiz.dto.response.Question.QuestionResponse;
import com.tomzxy.webQuiz.model.Answer;
import com.tomzxy.webQuiz.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    AnswerResponse toAnswerResponse(Answer answer);
    Answer toAnswer(AnswerRequestDTO answerRequestDTO);
    void updateAnswer(@MappingTarget Answer answer, AnswerRequestDTO answerRequestDTO);
}
