package com.tomzxy.webQuiz.dto.request.question;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.tomzxy.webQuiz.dto.request.answer.AnswerRequestDTO;
import com.tomzxy.webQuiz.enums.Level;
import com.tomzxy.webQuiz.model.Answer;
import com.tomzxy.webQuiz.validator.EnumPattern;
import com.tomzxy.webQuiz.validator.EnumValue;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionCreateRequestDTO implements Serializable {
    @NotBlank(message = "Question text must be not blank")
    @JsonProperty("questionText")
    String questionText;

    @NotNull(message = "Question level must be not null")
    @EnumValue(name = "level", enumClass = Level.class)
    String level;

    Set<AnswerRequestDTO> answers;


}
