package com.tomzxy.webQuiz.dto.request.subject;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectUpdateRequest {
    @NotBlank(message = "Subject name must be not blank")
    String subjectText;

    String description;
    List<String> chapters;
}
