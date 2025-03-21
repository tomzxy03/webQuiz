package com.tomzxy.webQuiz.dto.response.Chapter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tomzxy.webQuiz.dto.response.Question.QuestionResponse;
import com.tomzxy.webQuiz.model.Question;
import com.tomzxy.webQuiz.model.Subject;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ChapterResponse {
    Long id;
    String chapterName;

    List<Question> questions;
}
