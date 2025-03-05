package com.tomzxy.webQuiz.dto.response.Quiz;


import com.tomzxy.webQuiz.model.Chapter;
import lombok.*;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizResponse {
    Long id;
    String title;
    String timeQuiz;
    int totalQuestion;
    String chapter;
}
