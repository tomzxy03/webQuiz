package com.tomzxy.webQuiz.dto.request.quiz.basic;

import com.tomzxy.webQuiz.enums.QuizType;
import com.tomzxy.webQuiz.model.Chapter;
import com.tomzxy.webQuiz.model.Question;
import com.tomzxy.webQuiz.validator.EnumPattern;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizBasicCreateRequestDTO implements Serializable {
    @NotBlank(message = "Title must be not blank")
    String title;

    String description;

    @NotNull(message = "Time quiz must be not null")
    private Integer timeQuiz;  // Thời lượng làm bài (tính bằng phút)


    private boolean isTimed;  // Bài quiz có giới hạn thời gian hay không, bổ sung cho timeQuiz

    @EnumPattern(name = "type", regexp = "PUBLIC|GROUP")
    QuizType type;

    Set<Question> questions;

    Chapter chapter;
}
