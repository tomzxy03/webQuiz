package com.tomzxy.webQuiz.dto.response.Answer;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnswerResponse implements Serializable {
    String answerText;
    Boolean correct_answer;
}
