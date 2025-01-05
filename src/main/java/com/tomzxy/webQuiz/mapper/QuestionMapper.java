package com.tomzxy.webQuiz.mapper;

import com.tomzxy.webQuiz.dto.request.question.QuestionCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.question.QuestionUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.response.Question.QuestionResponse;
import com.tomzxy.webQuiz.model.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    QuestionResponse toQuestionResponse(Question question);

    @Mapping(target = "answers", ignore = true)
    Question toQuestion(QuestionCreateRequestDTO questionCreateRequestDTO);
    @Mapping(target = "answers", ignore = true)
    void updateQuestion(@MappingTarget Question question, QuestionUpdateRequestDTO questionUpdateRequestDTO);
}
