package com.tomzxy.webQuiz.dto.request.quiz_result;

import com.tomzxy.webQuiz.model.Quiz;
import com.tomzxy.webQuiz.model.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizResultUpdateRequestDTO {

    Double score;

    Date submit_at;

    Integer total_of_correct;

    Integer total_of_failed;

    Integer total_of_skip;

    Integer page_turns;

    Date end_quiz;
}