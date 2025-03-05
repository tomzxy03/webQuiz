package com.tomzxy.webQuiz.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QuizType {
    @JsonProperty(value = "group")
    GROUP,
    @JsonProperty(value = "public")
    PUBLIC
}
