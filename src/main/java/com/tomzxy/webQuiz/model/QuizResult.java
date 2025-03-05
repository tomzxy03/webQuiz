package com.tomzxy.webQuiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "quiz_result")
public class QuizResult extends BaseEntity{

    @Column(name = "score")
    @Value("${some.key:0.0}")
    Double score;

    @Column(name = "submit_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date submit_at;

    @Column(name= "total_of_correct")
    Integer total_of_correct;

    @Column(name= "total_of_failed")
    Integer total_of_failed;

    @Column(name= "total_of_skip")
    Integer total_of_skip;

    @Column(name= "page_turns")
    Integer page_turns;

    @Column(name = "start_quiz")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    Date start_quiz;


    @Column(name = "end_quiz")
    Date end_quiz;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "quiz_id", nullable = false)
    Quiz quiz;

    @OneToMany(mappedBy = "quizResult", cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    List<AnswerUser> answerUser;
}
