package com.tomzxy.webQuiz.dto.request.chapter;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChapterRequestDTO {
    @NotBlank(message = "Chapter name must be not blank")
    String chapterName;
}
