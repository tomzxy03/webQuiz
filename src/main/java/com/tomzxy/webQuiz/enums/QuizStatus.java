package com.tomzxy.webQuiz.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum QuizStatus {
    @JsonProperty("open")
    OPEN,
    @JsonProperty("locked")
    LOCKED,
    @JsonProperty("none")
    NONE
}
