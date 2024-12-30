package com.tomzxy.webQuiz.dto.response.Subject;

import com.tomzxy.webQuiz.model.Chapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class SubjectResponse {
    Long id;

    String subjectText;

    String description;

    List<String> chapters;
}
