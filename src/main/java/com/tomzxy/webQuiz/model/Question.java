package com.tomzxy.webQuiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tomzxy.webQuiz.enums.Level;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "questions")
public class Question extends BaseEntity{


    @Column(name = "question_text")
    String questionText;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.ENUM)
    @Column(name = "level")
    Level level;

    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
            // orphanRemoval will delete answers during question is deleted
    Set<Answer> answers;


    public void saveAnswers(Answer answer){
        if(answer != null){
            if(answers == null){
                answers = new HashSet<>();
            }
            answers.add(answer);
            answer.setQuestion(this);
        }
    }
    @JsonIgnore
    public Set<Answer> getAnswers(){
        return answers;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    @JsonIgnore
    Chapter chapter;

    @ManyToMany(mappedBy = "questions")
    @JsonIgnore
    Set<Quiz> quizzes;

}
