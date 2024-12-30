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
public class QuizCreateRequestDTO {
    @NotBlank(message = "Title must be not blank")
    String title;

    String description;
    @NotNull(message = "Time quiz must be not null")
    private String timeQuiz;  // Thời lượng làm bài (tính bằng phút) -- String time:time:time covert sang datetime
    @NotNull(message = "Total marks must be not null")
    private int totalMarks;  // Tổng điểm tối đa của bài quiz

    private boolean status;  // Trạng thái của bài quiz (đang hoạt động hay không)

    private boolean isTimed;  // Bài quiz có giới hạn thời gian hay không, bổ sung cho timeQuiz

    private int totalQuestion;

    Set<Question> questions;

    Chapter chapter;
}
