package com.tomzxy.webQuiz.dto.request.answer_user;

import com.tomzxy.webQuiz.model.QuizResult;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAnswerUpdateDTO {
    Boolean is_correct;

    String answer_Text;
}
