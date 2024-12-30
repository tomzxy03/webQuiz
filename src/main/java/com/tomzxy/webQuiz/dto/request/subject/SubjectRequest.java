package com.tomzxy.webQuiz.dto.request.subject;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubjectRequest {
    @NotBlank(message = "Subject name must be not blank")
    String name;
    String description;
}
