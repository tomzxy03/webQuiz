package com.tomzxy.webQuiz.dto.request.answer;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerRequestDTO {
    @NotBlank  (message = "Answer text must be not blank")
    @JsonProperty("answer_text")
    String answer_text;
    @NotNull(message = "Correct answer must be not null")
    Boolean correct_answer;

}
