package com.tomzxy.webQuiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "answers_user")
public class AnswerUser extends BaseEntity{
    @Column(name = "is_correct")
    Boolean is_correct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_result_id", nullable = false)
    @JsonIgnore
    QuizResult quizResult;


    @Column(name = "answer_Text") // answerText is unique so don't need add object answer
    String answer_Text;

}
