package com.tomzxy.webQuiz.dto.request.quiz_result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tomzxy.webQuiz.model.Quiz;
import com.tomzxy.webQuiz.model.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuizResultCreateRequestDTO {

    Double score;

    Date submit_at;

    Integer total_of_correct;

    Integer total_of_failed;

    Integer total_of_skip;

    Integer page_turns;

    Date start_quiz;

    Date end_quiz;

    User user;

    Quiz quiz;
}
