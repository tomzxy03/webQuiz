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
@Table(name = "answers")
public class Answer extends BaseEntity{

    @Column(name = "answer_text", nullable = false)
    String answerText;

    @Column(name = "correct_answer")
    Boolean correct_answer;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "question_id", nullable = false)
    Question question;
}
