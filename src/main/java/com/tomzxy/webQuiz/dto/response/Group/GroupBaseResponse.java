package com.tomzxy.webQuiz.dto.response.Group;

import com.tomzxy.webQuiz.model.Quiz;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GroupBaseResponse {
    String title;

    int total_user;

    String code_invite;

    String creator_userName;

    List<Quiz> quizzes;

}
