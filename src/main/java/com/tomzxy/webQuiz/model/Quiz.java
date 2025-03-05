package com.tomzxy.webQuiz.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.tomzxy.webQuiz.enums.QuizType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Quizs")
public class Quiz extends BaseEntity {

    @Column(name = "title", nullable = false)
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "time_quiz", nullable = false)
    private Integer timeQuiz; // Thời lượng làm bài (tính bằng phút)

    @Column(name = "status", nullable = false)
    private boolean status; // Trạng thái của bài quiz (đang hoạt động hay không)

    @Column(name = "is_Time", nullable = false)
    private boolean isTimed; // Bài quiz có giới hạn thời gian hay không, bổ sung cho timeQuiz

    @Column(name = "total_question", nullable = false)
    private int totalQuestion;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuizType type;



    @Column(name = "is_taken", columnDefinition = "boolean default false")
    private boolean isTaken;

    @ManyToMany
    @JoinTable(name = "quiz_detail", joinColumns = @JoinColumn(name = "quiz_id"), inverseJoinColumns = @JoinColumn(name = "question_id"))
    Set<Question> questions;

    @JsonIgnore // stop infinite loop
    public Set<Question> getQuestions() {
        return questions;
    }

    @OneToMany(mappedBy = "quiz", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    List<QuizResult> quizResults;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizzes", nullable = false)
    @JsonIgnore
    Chapter chapter;



    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "lobby_id")

    Lobby lobby;



    public void saveQuestions(Question question) {
        if (question != null) {
            if (questions == null) {
                questions = new HashSet<>(); // if questions are temping, then initialized questions
            }
            questions.add(question);
            question.getQuizzes().add(this); // save quiz_id to quiz_id in table question
        }
    }

}
