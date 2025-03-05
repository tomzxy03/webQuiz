package com.tomzxy.webQuiz.mapper;

import com.tomzxy.webQuiz.dto.request.quiz.basic.QuizBasicCreateRequestDTO;
import com.tomzxy.webQuiz.dto.request.quiz.QuizUpdateRequestDTO;
import com.tomzxy.webQuiz.dto.response.Quiz.QuizResponse;
import com.tomzxy.webQuiz.model.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface QuizMapper {

    @Mapping(target = "chapter", ignore = true)
    Quiz toQuiz(QuizResponse quizResponse);

    Quiz toQuiz(QuizBasicCreateRequestDTO quizBasicCreateRequestDTO);

    @Mapping(target = "chapter", ignore = true)
    QuizResponse toQuizResponse(Quiz quiz);
    @Mapping(target = "chapter", ignore = true)
    void updateQuiz(@MappingTarget Quiz quiz, QuizUpdateRequestDTO quizUpdateRequestDTO);
}
