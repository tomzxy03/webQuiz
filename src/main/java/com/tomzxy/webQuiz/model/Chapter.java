package com.tomzxy.webQuiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "chapters")
public class Chapter extends BaseEntity{


    @Column(name = "chapter_name", nullable = false,unique = true)
    String chapterName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id", nullable = false)
    @JsonIgnore
    Subject subject;

    @OneToMany(mappedBy = "chapter", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Question> questions;

    @OneToMany(mappedBy = "chapter", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Quiz> quizzes;
}
