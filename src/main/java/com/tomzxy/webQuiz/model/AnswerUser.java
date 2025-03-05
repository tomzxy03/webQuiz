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
    private boolean is_correct;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_result_id", nullable = false)
    @JsonIgnore
    QuizResult quizResult;


    @ManyToOne
    @JoinColumn(name = "selected_option")
    private Answer selectedOption;

    @ManyToOne
    @JoinColumn(name = "question", nullable = false)
    private Question question;

}
