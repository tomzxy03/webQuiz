package com.tomzxy.webQuiz.dto.response.Question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tomzxy.webQuiz.enums.Level;
import com.tomzxy.webQuiz.model.Answer;
import com.tomzxy.webQuiz.model.Quiz;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class QuestionResponse {
    Long id;
    String questionText;
    Level level;

    Set<Answer> answers;

}
