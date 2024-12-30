package com.tomzxy.webQuiz.model;


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
@Table(name = "subjects")
public class Subject extends BaseEntity{

    @Column(name = "subject_text", nullable = false,unique = true)
    String subjectText;

    @Column( name = "description",length = 1000)
    String description;

    @OneToMany(mappedBy = "subject", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    List<Chapter> chapters;
}
