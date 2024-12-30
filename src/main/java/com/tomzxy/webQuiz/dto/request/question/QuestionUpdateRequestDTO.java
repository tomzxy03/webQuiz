package com.tomzxy.webQuiz.dto.request.question;

import com.tomzxy.webQuiz.enums.Level;
import com.tomzxy.webQuiz.model.Answer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionUpdateRequestDTO {
    @NotBlank(message = "Question text must be not blank")
    String questionText;

    @NotNull(message = "Question level must be not null")
    Level level;



}
