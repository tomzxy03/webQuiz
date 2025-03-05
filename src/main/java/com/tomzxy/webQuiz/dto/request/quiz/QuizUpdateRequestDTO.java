package com.tomzxy.webQuiz.dto.request.quiz;

import com.tomzxy.webQuiz.model.Chapter;
import com.tomzxy.webQuiz.model.Question;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizUpdateRequestDTO {
    @NotBlank(message = "Title must be not blank")
    String title;

    String description;
    @NotNull(message = "Time quiz must be not null")
    private Integer timeQuiz;  // Thời lượng làm bài (tính bằng phút) -- String time:time:time covert sang datetime

    private boolean status;  // Trạng thái của bài quiz (đang hoạt động hay không)

    Set<Question> questions;

    Chapter chapter;
}
